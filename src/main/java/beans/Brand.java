package beans;

import java.util.List;

public class Brand {
    private int id;
    private String name, email, logo;

    public Brand() {
    }

    public Brand( String name, String email, String logo) {
        this.name = name;
        this.email = email;
        this.logo = logo;
    }
    public Brand(int id, String name, String email, String logo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.logo = logo;
    }

    public static String getNameBrand_ById(List<Brand> listB, int id) {
        for (Brand b: listB             ) {
            if (b.id == id)
                return b.name;
        }
        return "All Products";
    }
    public int getNumProduct(List<Product> listP) {
        int count = 0;
        for (Product p: listP)
            if (p.getIdBrand() == id)
                count++;
        return count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
