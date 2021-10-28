package beans;

public class OrderDetail {
    private int id, idOrder, quantity;
    private Product product;
    private double cost;

    public OrderDetail() {
    }

    public OrderDetail(int id, int idOrder, int quantity, Product product, double cost) {
        this.id = id;
        this.idOrder = idOrder;
        this.quantity = quantity;
        this.product = product;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", idOrder=" + idOrder +
                ", quantity=" + quantity +
                ", product=" + product +
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
