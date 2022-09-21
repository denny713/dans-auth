package com.dans.data.model.response;

import com.dans.data.model.master.TokenModel;
import com.dans.data.model.master.UserModel;
import lombok.Data;

@Data
public class UserLogin {

    private String status;
    private UserModel user;
    private TokenModel token;
}
