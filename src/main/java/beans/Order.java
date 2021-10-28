package beans;

import java.sql.Date;

public class Order {
    private String username, address, fullName, phoneNumber;
    private int id;
    private double cost;
    private Date purchaseDate;

    public Order() {
    }

    public Order(String username, String address, String fullName, String phoneNumber, int id, double cost, Date purchaseDate) {
        this.username = username;
        this.address = address;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.cost = cost;
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", id=" + id +
                ", cost=" + cost +
                ", purchaseDate=" + purchaseDate +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
