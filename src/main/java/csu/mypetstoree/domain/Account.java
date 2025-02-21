package csu.mypetstoree.domain;

import java.io.Serializable;

public class Account implements Serializable {

    private static final long serialVersionUID = 8751282105532159742L;

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String status;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String favouriteCategoryId;
    private String languagePreference;
    private boolean listOption;
    private boolean bannerOption;
    private String bannerName;

    //这里需要把购物车表和账户联系在一起
    private String cartId;

    public Account() {}

    public Account(String username,String password){
        this.username = username;
        this.password = password;
    }


    public Account(String username,String password,String email,String firstName,String lastName,
                   String address1,String address2,String city,String state,String zip,String country,
                   String phone){
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.phone = phone;
        this.status = "OK";
        this.favouriteCategoryId = "CATS";
        this.languagePreference = "english";
        this.listOption = false;
        this.bannerOption = false;
    }

    public Account(String username,String password,String email,String firstName,String lastName,
                   String address1,String address2,String city,String state,String zip,String country,
                   String phone,String favouriteCategoryId,String languagePreference,boolean listOption,
                   boolean bannerOption){
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.phone = phone;
        this.status = "OK";
        this.favouriteCategoryId = favouriteCategoryId;
        this.languagePreference = languagePreference;
        this.listOption = listOption;
        this.bannerOption = bannerOption;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStatus(){
        return status;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public String getFavouriteCategoryId() {
        return favouriteCategoryId;
    }

    public String getLanguagePreference() {
        return languagePreference;
    }

    public boolean isListOption() {
        return listOption;
    }

    public boolean isBannerOption() {
        return bannerOption;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStatus(String status){
        this.status=status;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFavouriteCategoryId(String favouriteCategoryId) {
        this.favouriteCategoryId = favouriteCategoryId;
    }

    public void setLanguagePreference(String languagePreference) {
        this.languagePreference = languagePreference;
    }

    public void setListOption(boolean listOption) {
        this.listOption = listOption;
    }

    public void setBannerOption(boolean bannerOption) {
        this.bannerOption = bannerOption;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String toString() {
        return "Account [username=" + username + ", email=" + email + ", firstName=" +firstName;
    }
}

