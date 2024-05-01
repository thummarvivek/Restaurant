
package com.vivekgroup.restaurant.Alljson;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Registration {

    @SerializedName("User_id")
    @Expose
    private String userId;
    @SerializedName("User_name")
    @Expose
    private String userName;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Phoneno")
    @Expose
    private Object phoneno;
    @SerializedName("Bio")
    @Expose
    private Object bio;
    @SerializedName("Address")
    @Expose
    private Object address;
    @SerializedName("lat")
    @Expose
    private Object lat;
    @SerializedName("log")
    @Expose
    private Object log;
    @SerializedName("Profile_picture")
    @Expose
    private Object profilePicture;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("dob")
    @Expose
    private Object dob;
    @SerializedName("Password")
    @Expose
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(Object phoneno) {
        this.phoneno = phoneno;
    }

    public Object getBio() {
        return bio;
    }

    public void setBio(Object bio) {
        this.bio = bio;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Object getLog() {
        return log;
    }

    public void setLog(Object log) {
        this.log = log;
    }

    public Object getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Object profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getDob() {
        return dob;
    }

    public void setDob(Object dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
