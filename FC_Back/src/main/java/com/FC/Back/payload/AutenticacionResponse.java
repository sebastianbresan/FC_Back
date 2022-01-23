package com.FC.Back.payload;

import java.io.Serial;
import java.io.Serializable;

public class AutenticacionResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private  String token;
    private  String email;

    public AutenticacionResponse(String token, String email){
        this.token = token;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
