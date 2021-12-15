package beans;

import utils.DBBrandUtil;
import utils.DBProductUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDetail {
    private int id, idOrder, quantity;
    private int idProduct;
    private double cost;

    public OrderDetail() {
    }

    public OrderDetail(int id, int idOrder, int quantity, int idProduct, double cost) {
        this.id = id;
        this.idOrder = idOrder;
        this.quantity = quantity;
        this.idProduct = idProduct;
        this.cost = cost;
    }
    public String getNameProduct(Connection conn) throws SQLException {
        List<Product> listP = DBProductUtil.getAllProduct(conn);
        for (Product p: listP ) {
            if (p.getId() == idProduct)
                return p.getName();
        }
        return "Unknown";
    }
    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", idOrder=" + idOrder +
                ", quantity=" + quantity +
                ", product=" + idProduct +
                ", cost=" + cost +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
