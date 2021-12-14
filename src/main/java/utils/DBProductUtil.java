package utils;

import beans.Product;
import conn.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBProductUtil {
    // Get number of Product
    public static int getNumProduct(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("select count(id) from Product");

        if (rs.next())
            return rs.getInt(1);
        return 0;
    }

    // Get a product by id
    public static Product getProduct_byId(Connection conn, int _id) throws SQLException {
        PreparedStatement pstm = conn.prepareCall("select * from Product where id = ?");
        pstm.setInt(1, _id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            int id = rs.getInt(1);
            int idBrand = rs.getInt(2);
            String name = rs.getString(3);
            String image = rs.getString(4);
            String describe = rs.getString(5);
            int quantity = rs.getInt(6);
            double cost = rs.getDouble(7);
            Date saleDate = rs.getDate(8);
            return new Product(id, idBrand, name, image, describe, quantity, cost, saleDate);
        }
        return null;
    }

    // Get all products from db
    public static List<Product> getAllProduct(Connection conn) throws SQLException {
        List<Product> list = new ArrayList<>();
        PreparedStatement pstm = conn.prepareCall("select * from Product");
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            int idBrand = rs.getInt(2);
            String name = rs.getString(3);
            String image = rs.getString(4);
            String describe = rs.getString(5);
            int quantity = rs.getInt(6);
            double cost = rs.getDouble(7);
            Date saleDate = rs.getDate(8);
            Product p = new Product(id, idBrand, name, image, describe, quantity, cost, saleDate);
            list.add(p);
        }
        return list;
    }

    // Get top 8 Nike products from db
    public static List<Product> getAllNikeProduct(Connection conn) throws SQLException {
        List<Product> list = new ArrayList<>();
        PreparedStatement pstm = conn.prepareCall("select * from Product where idBrand = 2");
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            int idBrand = rs.getInt(2);
            String name = rs.getString(3);
            String image = rs.getString(4);
            String describe = rs.getString(5);
            int quantity = rs.getInt(6);
            double cost = rs.getDouble(7);
            Date saleDate = rs.getDate(8);
            Product p = new Product(id, idBrand, name, image, describe, quantity, cost, saleDate);
            list.add(p);
        }
        return list;
    }

    // Get top 8 Adidas products from db
    public static List<Product> getAllAdidasProduct(Connection conn) throws SQLException {
        List<Product> list = new ArrayList<>();
        PreparedStatement pstm = conn.prepareCall("select * from Product where idBrand = 1");
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            int idBrand = rs.getInt(2);
            String name = rs.getString(3);
            String image = rs.getString(4);
            String describe = rs.getString(5);
            int quantity = rs.getInt(6);
            double cost = rs.getDouble(7);
            Date saleDate = rs.getDate(8);
            Product p = new Product(id, idBrand, name, image, describe, quantity, cost, saleDate);
            list.add(p);
        }
        return list;
    }

    // Get selection nike product
    public static Product getSelectionNikeProduct(Connection conn) throws SQLException {
        PreparedStatement pstm = conn.prepareCall("select top 1 * from Product where idBrand = 2");
        ResultSet rs = pstm.executeQuery();
        Product p = null;

        while (rs.next()) {
            int id = rs.getInt(1);
            int idBrand = rs.getInt(2);
            String name = rs.getString(3);
            String image = rs.getString(4);
            String describe = rs.getString(5);
            int quantity = rs.getInt(6);
            double cost = rs.getDouble(7);
            Date saleDate = rs.getDate(8);
            p = new Product(id, idBrand, name, image, describe, quantity, cost, saleDate);
        }
        return p;
    }

    // Get selection adidas product
    public static Product getSelectionAdidasProduct(Connection conn) throws SQLException {
        PreparedStatement pstm = conn.prepareCall("select top 1 * from Product where idBrand = 1");
        ResultSet rs = pstm.executeQuery();
        Product p = null;

        while (rs.next()) {
            int id = rs.getInt(1);
            int idBrand = rs.getInt(2);
            String name = rs.getString(3);
            String image = rs.getString(4);
            String describe = rs.getString(5);
            int quantity = rs.getInt(6);
            double cost = rs.getDouble(7);
            Date saleDate = rs.getDate(8);
            p = new Product(id, idBrand, name, image, describe, quantity, cost, saleDate);
        }
        return p;
    }
/*
    // Get top 9 lastest purchased product
    public static List<Product> getLastestPurchasedProduct(Connection conn) throws SQLException {
        List<Product> list = new ArrayList<>();
        PreparedStatement pstm = conn.prepareCall("select top 9 Product.*\n" +
                "from Product, OrderDetail, [Order]\n" +
                "where Product.id = OrderDetail.idProduct\n" +
                "    and OrderDetail.idOrder = [Order].id\n" +
                "order by [Order].purchaseDate desc");
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            int idBrand = rs.getInt(2);
            String name = rs.getString(3);
            String image = rs.getString(4);
            String describe = rs.getString(5);
            int quantity = rs.getInt(6);
            double cost = rs.getDouble(7);
            Date saleDate = rs.getDate(8);
            Product p = new Product(id, idBrand, name, image, describe, quantity, cost, saleDate);
            list.add(p);
        }
        return list;
    }
 */

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionUtils.getConnection();
        List<Product> listP = getAllProduct(conn);
        for (Product p : listP) {
            System.out.println(p);
        }

        System.out.println(getProduct_byId(conn, 1));
        System.out.println(listP.size()/12);
    }
}
