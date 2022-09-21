package com.dans.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "md_token", uniqueConstraints = {
        @UniqueConstraint(columnNames = "client")
})
public class MdToken extends CreateUpdate implements ClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenId;

    @Column(name = "authorities")
    private String authority;

    @Column(name = "client")
    private String client;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "grant_types")
    private String grantTypes;

    @Column(name = "access_valid")
    private Long accessValid;

    @Column(name = "refresh_valid")
    private Long refreshValid;

    @Column(name = "url")
    private String url;

    @Column(name = "scopes")
    private String scopes;

    @Column(name = "secret")
    private String secretKey;

    @Column(name = "is_scope")
    private Boolean hasScope;

    @Column(name = "is_secret")
    private Boolean hasSecret;

    @Column(name = "resources")
    private String resources;

    @Override
    public String getClientId() {
        return client;
    }

    @Override
    public Set<String> getResourceIds() {
        Set<String> res = new HashSet<>();
        res.add(resources);
        return res;
    }

    @Override
    public boolean isSecretRequired() {
        return hasSecret;
    }

    @Override
    public String getClientSecret() {
        return secretKey;
    }

    @Override
    public boolean isScoped() {
        return hasScope;
    }

    @Override
    public Set<String> getScope() {
        return new HashSet<>(Arrays.asList(scopes.split(",")));
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return new HashSet<>(Arrays.asList(grantTypes.split(",")));
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        Set<String> uris = new HashSet<>();
        if (url != null) {
            return new HashSet<>(Arrays.asList(url.split(",")));
        }
        return uris;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grants = new HashSet<>();
        String[] strs = authority.split(",");
        for (String atr : strs) {
            grants.add(new SimpleGrantedAuthority(atr));
        }
        return grants;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessValid.intValue();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshValid.intValue();
    }

    @Override
    public boolean isAutoApprove(String s) {
        return approved;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
