package com.dans.controller;

import com.dans.data.model.response.DansPosition;
import com.dans.util.ResponseUtil;
import com.dans.util.WebUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.*;

@RestController
@RequestMapping("/position")
public class PositionController {

    private String url = "http://dev3.dansmultipro.co.id/api/recruitment/positions";
    private String token = "";
    Map<String, Object> resp = new HashMap<>();

    @GetMapping("/all")
    @ResponseBody
    public HttpEntity<Map<String, Object>> getAllPosition(HttpServletRequest request) {
        token = WebUtil.getHeaderValue(request, "Authorization");
        if (!token.equals("") || token != null) {
            resp.put("data",getDataFromApi(""));
            resp.put("status", ResponseUtil.setSuccess("Success"));
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        resp.put("status", ResponseUtil.setFailed("No Authentication"));
        return new ResponseEntity<>(resp, HttpStatus.FORBIDDEN);
    }

    @GetMapping("/detail/{id}")
    @ResponseBody
    public HttpEntity<Map<String, Object>> getDetailPosition(@PathVariable("id") String id, HttpServletRequest request) {
        token = WebUtil.getHeaderValue(request, "Authorization");
        if (!token.equals("") || token != null) {
            resp.put("data",getDataFromApi(id));
            resp.put("status", ResponseUtil.setSuccess("Success"));
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        resp.put("status", ResponseUtil.setFailed("No Authentication"));
        return new ResponseEntity<>(resp, HttpStatus.FORBIDDEN);
    }

    public List<Object> getDataFromApi(String key) {
        List<Object> response = new ArrayList<>();
        RestTemplate rest = new RestTemplate();
        if (key.equals("")) {
            response = Arrays.asList(rest.getForObject(url + ".json", Object[].class));
        } else {
            response.add(rest.getForObject(url + "/" + key, Object.class));
        }
        return response;
    }
}
