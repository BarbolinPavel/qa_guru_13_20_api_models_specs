package tests.models.pojo;

public class LoginBodyPojoModel {
//            String body = "{ \"email\": \"eve.holt@reqres.in\", " +
//                "\"password\": \"proverka13\" }";

    private String email;
    private String password;

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
