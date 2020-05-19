package com.example.walkingschoolbus.model;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.walkingschoolbus.R;
import com.example.walkingschoolbus.WelcomeScreen;
import com.example.walkingschoolbus.proxy.ProxyBuilder;
import com.example.walkingschoolbus.proxy.WGServerProxy;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * User class to store the data the server expects and returns.
 * (Incomplete: Needs support for monitoring and groups).
 */

// All model classes coming from server must have this next line.
// It ensures deserialization does not fail if server sends you some fields you are not expecting.
// This is needed for the server to be able to change without breaking your app!
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String token;

    // Data fields for the user.
    // -------------------------------------------------------------------------------------------
    // NOTE: Make numbers Long/Integer, not long/int because only the former will
    //       deserialize if the value is null from the server.

    private Long id;

    private Boolean hasFullData;


    // ---------------------------------------
    //    Fields
    // ---------------------------------------
    private String name;
    private String email;
    private Integer birthYear;
    private Integer birthMonth;
    private String address;
    private String cellPhone;
    private String homePhone;
    private String grade;
    private String teacherName;
    private String emergencyContactInfo;
    private String password;

    // Monitoring
    // - - - - - - - - - - - - - - - - - - - - - - - - - -
    private List<User> monitoredByUsers = new ArrayList<>();
    private List<User> monitorsUsers = new ArrayList<>();

    // Group Membership / Leading
    // - - - - - - - - - - - - - - - - - - - - - - - - - -
    private List<Group> memberOfGroups = new ArrayList<>();
    private List<Group> leadsGroups = new ArrayList<>();

    // GPS Location
    // - - - - - - - - - - - - - - - - - - - - - - - - - -
    private GpsLocation lastGpsLocation;

    // Messages
    // - - - - - - - - - - - - - - - - - - - - - - - - - -
    private List<Message> messages;

    // Gamification Support
    // - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private Integer currentPoints;
    private Integer totalPointsEarned;
    // rewards will be serialized to be the customJson
    private EarnedRewards rewards;


    // Permissions
    // - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private List<PermissionRequest> pendingPermissionRequests;


    private String href;

    //Constructors
    public User() { }
//private User(){}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public List<PermissionRequest> getPendingPermissionRequests() {
        return pendingPermissionRequests;
    }

    public void setPendingPermissionRequests(List<PermissionRequest> pendingPermissionRequests) {
        this.pendingPermissionRequests = pendingPermissionRequests;
    }

    public User(Long id){
        this.id = id;
    }

    public User( Long id, String name, String email, String password) {
        //to do
        this.id = id;

        this.hasFullData = true;
        this.name = name;
        this.email = email;
        this.password = password;
        //this.monitoredByUsers = monitoredByUsers;
        //this.monitorsUsers = monitorsUsers;
        //this.memberOfGroups = memberOfGroups;
        //this.leadsGroups = leadsGroups;
        //this.href = href;
    }

    // Check if full data
    // -------------------------------------------------------------------------------------------
    // Server often replies with stub objects instead of full data.
    // If server sends back just an ID then it's a stub; otherwise you have full data about
    // *this* object. Objects it refers to, such as other users or groups, may not be filled in
    // (and hence those will have hasFullData set to false for them).
    public User(User oldUser){
        this.id = oldUser.getId();
        this.hasFullData = oldUser.getHasFullData();
        this.name = oldUser.getName();
        this.email = oldUser.getEmail();
        this.password = oldUser.getPassword();
        this.monitoredByUsers =oldUser.getMonitoredByUsers();
        this.monitorsUsers = oldUser.getMonitorsUsers();
        this.memberOfGroups = oldUser.getMemberOfGroups();
        this.leadsGroups = oldUser.getLeadsGroups();
        this.href=oldUser.getHref();
        this.currentPoints =oldUser.getCurrentPoints();
        this.totalPointsEarned = oldUser.getTotalPointsEarned();
        this.rewards = oldUser.getRewards();
    }

    public void makeCopyOf(User oldUser){
        this.id = oldUser.getId();
        this.hasFullData = oldUser.getHasFullData();
        this.name = oldUser.getName();
        this.email = oldUser.getEmail();
        this.password = oldUser.getPassword();
        this.monitoredByUsers =oldUser.getMonitoredByUsers();
        this.monitorsUsers = oldUser.getMonitorsUsers();
        this.memberOfGroups = oldUser.getMemberOfGroups();
        this.leadsGroups = oldUser.getLeadsGroups();
        this.href=oldUser.getHref();
        this.currentPoints =oldUser.getCurrentPoints();
        this.totalPointsEarned = oldUser.getTotalPointsEarned();
        this.rewards = oldUser.getRewards();
    }

    public Boolean getHasFullData() {
        return hasFullData;
    }

    public void setHasFullData(Boolean hasFullData) {
        this.hasFullData = hasFullData;
    }

    // Basic User Data
    // -------------------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    // Once a user's ID is set on the server, client-side cannot change it.
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getEmergencyContactInfo() {
        return emergencyContactInfo;
    }

    public void setEmergencyContactInfo(String emergencyContactInfo) {
        this.emergencyContactInfo = emergencyContactInfo;
    }

    public GpsLocation getLastGpsLocation() {
        return lastGpsLocation;
    }

    public void setLastGpsLocation(GpsLocation lastGpsLocation) {
        this.lastGpsLocation = lastGpsLocation;
    }

    // Note: Password never returned by the server; only used to send password to server.
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Monitoring
    // -------------------------------------------------------------------------------------------
    public List<User> getMonitoredByUsers() {
        return monitoredByUsers;
    }

    public void setMonitoredByUsers(List<User> monitoredByUsers) {
        this.monitoredByUsers = monitoredByUsers;
    }

    public List<User> getMonitorsUsers() {
        return monitorsUsers;
    }

    public void setMonitorsUsers(List<User> monitorsUsers) {
        this.monitorsUsers = monitorsUsers;
    }

    // Groups
    // -------------------------------------------------------------------------------------------
    public List<Group> getMemberOfGroups() {
        return memberOfGroups;
    }

    public void setMemberOfGroups(List<Group> memberOfGroups) {
        this.memberOfGroups = memberOfGroups;
    }

    public List<Group> getLeadsGroups() {
        return leadsGroups;
    }

    public void setLeadsGroups(List<Group> leadsGroups) {
        this.leadsGroups = leadsGroups;
    }

    // Link (unneeded, but send by server...)
    // -------------------------------------------------------------------------------------------
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    // Rewards (custom JSON data)
    // -------------------------------------------------------------------------------------------
    public Integer getCurrentPoints() {
        return currentPoints;
    }
    public void setCurrentPoints(Integer currentPoints) {
        this.currentPoints = currentPoints;
    }
    public Integer getTotalPointsEarned() {
        return totalPointsEarned;
    }
    public void setTotalPointsEarned(Integer totalPointsEarned) {
        this.totalPointsEarned = totalPointsEarned;
    }

    // Setter will be called when deserializing User's JSON object; we'll automatically
    // expand it into the custom object.
    public void setCustomJson(String jsonString) {
        if (jsonString == null || jsonString.length() == 0) {
            rewards = null;
            Log.w("USER", "De-serializing string is null for User's custom Json rewards; ignoring.");
        } else {
            Log.w("USER", "De-serializing string: " + jsonString);
            try {
                rewards = new ObjectMapper().readValue(
                        jsonString,
                        EarnedRewards.class);
                Log.w("USER", "De-serialized embedded rewards object: " + rewards);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // Having a getter will make this function be called to set the value of the
    // customJson field of the JSON data being sent to server.
    public String getCustomJson() {
        // Convert custom object to a JSON string:
        String customAsJson = null;
        try {
            customAsJson = new ObjectMapper().writeValueAsString(rewards);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return customAsJson;
    }

    public EarnedRewards getRewards() {
        return rewards;
    }
    public void setRewards(EarnedRewards rewards) {
        this.rewards = rewards;
    }

    // Utility Functions
    // -------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", currentPoints=" + currentPoints +
                ", totalPointsEarned=" + totalPointsEarned +
                ", monitoredByUsers=" + monitoredByUsers +
                ", monitorsUsers=" + monitorsUsers +
                ", memberOfGroups=" + memberOfGroups +
                ", leadsGroups=" + leadsGroups +
                ", rewards=" + rewards +
                ", hasFullData=" + hasFullData +
                ", href='" + href + '\'' +
                '}';
    }
}



