package com.dans.data.model.master;

import lombok.Data;

@Data
public class TokenModel {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private String scope;
    private String jti;
}
