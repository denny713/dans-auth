package com.dans.controller;

import com.dans.data.entity.MdUser;
import com.dans.data.model.master.TokenModel;
import com.dans.data.model.master.UserModel;
import com.dans.data.model.request.Login;
import com.dans.data.model.response.Response;
import com.dans.data.model.response.UserLogin;
import com.dans.util.EncryptUtil;
import com.dans.util.ResponseUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@RestController
public class AccountController extends BaseController {

    @GetMapping("/")
    @ResponseBody
    public String homeIndex() {
        return "Application Programming Interface";
    }

    public Response checkLogin(Login login) throws NoSuchAlgorithmException {
        MdUser user = userService.getByUsername(login.getUsername());
        if (user == null) {
            return ResponseUtil.setFailed("Data User " + login.getUsername() + " Tidak Ditemukan");
        }
        String passCrypt = EncryptUtil.encrypt(login.getPassword());
        if (!passCrypt.equals(user.getPassword())) {
            return ResponseUtil.setFailed("Username Atau Password Tidak Sesuai");
        }
        return ResponseUtil.setSuccess("Verifikasi Login Berhasil");
    }

    public UserLogin setUserLogin(Login login) throws HttpRequestMethodNotSupportedException {
        UserLogin userLogin = new UserLogin();
        Map<String, String> param = new HashMap<>();
        param.put("grant_type", "password");
        param.put("username", login.getUsername());
        param.put("password", login.getPassword());
        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
        auth.add(new SimpleGrantedAuthority("ROLE_TRUSTED_CLIENT"));
        User principal = new User("dans-auth", "b@ck0ff1ce", true, true, true, true, auth);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(principal, "b@ck0ff1ce", auth);
        ResponseEntity<OAuth2AccessToken> token = tokenEndpoint.postAccessToken(authToken, param);
        if (token.getBody() == null) {
            userLogin.setToken(null);
        } else {
            TokenModel ctoken = new TokenModel();
            StringBuilder scopes = new StringBuilder();
            Objects.requireNonNull(token.getBody()).getScope().forEach(str -> scopes.append(str).append(" "));
            ctoken.setAccessToken(token.getBody().getValue());
            ctoken.setJti(token.getBody().getAdditionalInformation().get("jti").toString());
            ctoken.setScope(scopes.substring(0, scopes.toString().length() - 1));
            ctoken.setRefreshToken(token.getBody().getRefreshToken().getValue());
            ctoken.setTokenType(token.getBody().getTokenType());
            userLogin.setToken(ctoken);
        }
        MdUser user = userService.getByUsername(login.getUsername());
        if (user == null) {
            userLogin.setUser(null);
        } else {
            UserModel model = new UserModel();
            model.setUsername(login.getUsername());
            model.setName(user.getName());
            model.setAktif(user.getAktif());
            model.setStatus(user.getStatus());
            userLogin.setUser(model);
        }
        userLogin.setStatus("");
        return userLogin;
    }

    @PostMapping("/login")
    @ResponseBody
    public HttpEntity<Map<String, Object>> login(@Valid @RequestBody Login login) {
        Map<String, Object> resp = new HashMap<>();
        HttpStatus stat = HttpStatus.BAD_REQUEST;
        try {
            response = checkLogin(login);
            if (Boolean.TRUE.equals(response.getResult())) {
                UserLogin user = setUserLogin(login);
                if (user.getToken() == null) {
                    response = ResponseUtil.setFailed("Gagal Mendapatkan Token");
                } else {
                    user.setStatus("Success");
                    resp.put("data", user);
                    stat = HttpStatus.OK;
                }
            }
        } catch (Exception e) {
            response = ResponseUtil.setFailed(e.getMessage());
        } finally {
            resp.put("status", response);
        }
        return new ResponseEntity<>(resp, stat);
    }
}
