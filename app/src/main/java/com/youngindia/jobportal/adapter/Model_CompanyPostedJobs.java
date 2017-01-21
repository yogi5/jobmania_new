package com.youngindia.jobportal.adapter;

/**
 * Created by User on 10/6/2016.
 */
public class Model_CompanyPostedJobs {
    private String  jobname,jobdetails,jobqualification,location,salary,exp;

    public Model_CompanyPostedJobs(String jobname, String jobdetails, String jobqualification, String location, String salary, String exp) {
        this.jobname = jobname;
        this.jobdetails = jobdetails;
        this.jobqualification = jobqualification;
        this.location = location;
        this.salary = salary;
        this.exp = exp;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getJobdetails() {
        return jobdetails;
    }

    public void setJobdetails(String jobdetails) {
        this.jobdetails = jobdetails;
    }

    public String getJobqualification() {
        return jobqualification;
    }

    public void setJobqualification(String jobqualification) {
        this.jobqualification = jobqualification;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
}
