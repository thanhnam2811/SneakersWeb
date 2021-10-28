package utils;

import beans.Cart;
import beans.Product;
import conn.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBCartUtil {
    // get cart by username
    public static List<Cart> getCart_byUsername(Connection conn, String _username) throws SQLException {
        List<Cart> listCart = new ArrayList<>();

        PreparedStatement pstm = conn.prepareCall("select * from Cart where username = ?");
        pstm.setString(1, _username);

        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            String username = rs.getString(2);
            int idProduct = rs.getInt(3);
            Product product = DBProductUtil.getProduct_byId(conn, idProduct);
            int quantity = rs.getInt(4);
            double cost = rs.getDouble(5);
            listCart.add(new Cart(id, username, product, quantity, cost));
        }

        return listCart;
    }

    // Update cart by username and idProduct
    public static void updateCart_byUsername_idProduct(Connection conn, String username, int idProduct, int quantity) throws SQLException {
        PreparedStatement pstm = conn.prepareCall("update Cart set quantity = ? where username = ? and idProduct = ?");
        pstm.setInt(1, quantity);
        pstm.setString(2, username);
        pstm.setInt(3, idProduct);
        pstm.executeUpdate();
    }

    // Insert cart by username and idProduct
    public static void insertCart_byUsername_idProduct(Connection conn, String username, int idProduct, int quantity) throws SQLException {
        PreparedStatement pstm = conn.prepareCall("insert into Cart(username, idProduct, quantity) values (?, ?, ?)");
        pstm.setString(1, username);
        pstm.setInt(2, idProduct);;
        pstm.setInt(3, quantity);
        pstm.executeUpdate();
    }

    // Delete cart by username and idProduct
    public static void deleteCart_byUsername_idProduct(Connection conn, String username, int idProduct) throws SQLException {
        PreparedStatement pstm = conn.prepareCall("delete Cart where username = ? and idProduct = ?");
        pstm.setString(1, username);
        pstm.setInt(2, idProduct);
        pstm.executeUpdate();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionUtils.getConnection();
        insertCart_byUsername_idProduct(conn, "nam", 156, 2);
    }
}
