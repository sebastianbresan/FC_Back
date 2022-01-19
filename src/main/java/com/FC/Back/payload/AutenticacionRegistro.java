package com.FC.Back.payload;

import java.io.Serial;
import java.io.Serializable;

// Ver si sirve o uso el de usuarios
public class AutenticacionRegistro implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;

    public AutenticacionRegistro(){}

    public AutenticacionRegistro(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
