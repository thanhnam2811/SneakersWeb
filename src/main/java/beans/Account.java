package beans;

import java.sql.Date;

public class Account {
    int id;
    String username, password, fullname, avatar, phoneNumber, address, sex;
    Date dateOfBirth;
    boolean isAdmin;

    public Account(){}

    public Account(int id, String username, String password, String fullname, String avatar, String phoneNumber, String address, String sex, Date dateOfBirth, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.avatar = avatar;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", datOfBirth=" + dateOfBirth +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
