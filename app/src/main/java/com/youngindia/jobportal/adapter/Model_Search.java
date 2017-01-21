package com.youngindia.jobportal.adapter;

import java.util.ArrayList;


public class Model_Search {
    private String name;
    private String mobile;
    private String email;
    private String highereducation;
    private String locationprf;
    private String status;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    private String Username;

    public Model_Search(String name, String mobile, String email, String highereducation, String locationprf, String status,String username) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.highereducation = highereducation;
        this.locationprf = locationprf;
        this.status = status;
        this.Username=username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHighereducation() {
        return highereducation;
    }

    public void setHighereducation(String highereducation) {
        this.highereducation = highereducation;
    }

    public String getLocationprf() {
        return locationprf;
    }

    public void setLocationprf(String locationprf) {
        this.locationprf = locationprf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
