package beans;

import utils.DBBrandUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Product {
    private int id, idBrand, quantity;
    private double cost;
    private Date saleDate;
    private String name, image, describe;

    public Product() {
    }

    public Product(int id, int idBrand, String name, String image, String describe,
                   int quantity, double cost, Date saleDate) {
        this.id = id;
        this.idBrand = idBrand;
        this.name = name;
        this.image = image;
        this.describe = describe;
        this.quantity = quantity;
        this.cost = cost;
        this.saleDate = saleDate;
    }

    public String getBrandName(Connection conn) throws SQLException {
        List<Brand> listB = DBBrandUtil.getAllBrand(conn);
        for (Brand b: listB ) {
            if (b.getId() == idBrand)
                return b.getName();
        }
        return "Unknown";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", idBrand=" + idBrand +
                ", quantity=" + quantity +
                ", cost=" + cost +
                ", saleDate=" + saleDate +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
