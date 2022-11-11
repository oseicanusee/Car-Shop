package com.carshop.CarShop.payload;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class SignInDTO {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;

    public SignInDTO(){}

    public SignInDTO(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SignInDTO)) return false;
        SignInDTO signInDTO = (SignInDTO) o;
        return userName.equals(signInDTO.userName) && password.equals(signInDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
