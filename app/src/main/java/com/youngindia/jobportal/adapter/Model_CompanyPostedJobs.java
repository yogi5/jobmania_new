package com.youngindia.jobportal.adapter;

/**
 * Created by User on 10/6/2016.
 */
public class Model_CompanyPostedJobs {
    private String  jobname,jobdetails,jobqualification,location,salary,exp,status,companyname;

    public Model_CompanyPostedJobs(String jobname, String jobdetails, String jobqualification, String location, String salary, String exp,String status,String companyname) {
        this.jobname = jobname;
        this.jobdetails = jobdetails;
        this.jobqualification = jobqualification;
        this.location = location;
        this.salary = salary;
        this.exp = exp;
        this.status=status;
        this.companyname=companyname;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
}
