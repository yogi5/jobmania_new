package com.youngindia.jobportal.adapter;

/**
 * Created by User on 9/8/2016.
 */
public class Model_companyappliedcandidate {
    private String Jobname, Qualification,Location;

    public Model_companyappliedcandidate(String jobname, String qualification, String location) {
        Jobname = jobname;
        Qualification = qualification;
        Location = location;
    }

    public String getJobname() {
        return Jobname;
    }

    public void setJobname(String jobname) {
        Jobname = jobname;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
