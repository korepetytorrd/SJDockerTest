package org.example.models;

import java.util.Date;

public abstract class User {

    private int id;
    private int privilege;
    private String firstName;
    private String lastName;
    private String province;
    private String town;
    private String postCode;
    private String street;
    private String houseNumber;
    private String flatNumber;
    private String phone;
    private String pesel;
    private String email;
    private Boolean email_ver;
    private String password;
    private Date created_at;
    private Date updated_at;

    //Getery i Seter
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getEmail_ver() {
        return email_ver;
    }

    public void setEmail_ver(Boolean email_ver) {
        this.email_ver = email_ver;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //konstruktory
    public User(int id, int privilege, String firstName, String email, String flatNumber, String lastName, String pesel, String houseNumber, String province, String phone) {
        this.id = id;
        this.privilege = privilege;
        this.firstName = firstName;
        this.email = email;
        this.flatNumber = flatNumber;
        this.lastName = lastName;
        this.pesel = pesel;
        this.houseNumber = houseNumber;
        this.province = province;
        this.phone = phone;
    }

    public User(int privilege, String firstName, String lastName, String province, String town, String postCode, String street, String houseNumber, String flatNumber, String phone, String pesel, String email, Boolean email_ver, String password, Date created_at) {
        this.privilege = privilege;
        this.firstName = firstName;
        this.lastName = lastName;
        this.province = province;
        this.town = town;
        this.postCode = postCode;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
        this.phone = phone;
        this.pesel = pesel;
        this.email = email;
        this.email_ver = email_ver;
        this.password = password;
        this.created_at = created_at;
    }

    public User(Date created_at, String email, Boolean email_ver, String firstName, String flatNumber, String houseNumber, int id, String lastName, String password, String pesel, String phone, String postCode, int privilege, String province, String street, String town) {
        this.created_at = created_at;
        this.email = email;
        this.email_ver = email_ver;
        this.firstName = firstName;
        this.flatNumber = flatNumber;
        this.houseNumber = houseNumber;
        this.id = id;
        this.lastName = lastName;
        this.password = password;
        this.pesel = pesel;
        this.phone = phone;
        this.postCode = postCode;
        this.privilege = privilege;
        this.province = province;
        this.street = street;
        this.town = town;
    }

    public User() {
    }

    //inne metody ..
    public String wyswietl() {
        return "Dane uzytkownika " + firstName + " uprawnienia: " + privilege + "Województwo: " + province + "Miasto:" + town;
    }

    public String toCSV() {
        return id + ";" + privilege + ";" + firstName + ";" + email + ";" + flatNumber + ";" + lastName + ";" + pesel + ";" + houseNumber + ";" + province + ";" + phone + ";";
    }
}
