package beans;

public class Cart {
    private int id, quantity;
    private Product product;
    private String username;
    private double cost;

    public Cart() {
    }

    public Cart(int id, String username, Product product, int quantity, double cost) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.username = username;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUsername() {
        return username;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", Product=" + product +
                ", quantity=" + quantity +
                ", username='" + username + '\'' +
                ", cost=" + cost +
                '}';
    }
}
