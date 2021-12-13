package beans;

import utils.DBBrandUtil;
import utils.DBProductUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Comment {
    private int id, idProduct;
    private String username, comment;

    public Comment() {
    }

    public Comment(int id, int idProduct, String username, String comment) {
        this.id = id;
        this.idProduct = idProduct;
        this.username = username;
        this.comment = comment;
    }
    public String getImageProduct(List<Product> listProduct) throws SQLException {
        for (Product p: listProduct ) {
            if (p.getId() == idProduct)
                return p.getImage();
        }
        return "Unknown";
    }
    public int getId() {
        return id;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", idProduct=" + idProduct +
                ", username='" + username + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
