package com.example.sakshyamaryal_21422013;

public class UserDetails {
    //    private String id;
    private String username;
    private String lname;
    private String password;
    private String email;
    private String dateUpdated;
    private String dateRegistered;

    //class to store the data with constructor and setter and getter
    @Override
    public String toString() {
        return "UserDetails{" +
                "username='" + username + '\'' +
                ", lname='" + lname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dateUpdated='" + dateUpdated + '\'' +
                ", dateRegistered='" + dateRegistered + '\'' +
                '}';
    }

    public UserDetails(String username, String lname, String password, String email, String dateUpdated, String dateRegistered) {
        this.username = username;
        this.lname = lname;
        this.password = password;
        this.email = email;
        this.dateUpdated = dateUpdated;
        this.dateRegistered = dateRegistered;
    }
    public UserDetails(){

    }
    public String getFname() {
        return username;
    }

    public void setFname(String fname) {
        this.username = fname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }
}


