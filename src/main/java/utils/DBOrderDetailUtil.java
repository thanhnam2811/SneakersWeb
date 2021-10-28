package utils;

import beans.Cart;
import beans.Product;
import conn.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DBOrderDetailUtil {
    // Insert to OrderDetail by a cart detail
    public static void insertOrderDetail_byIdOrder_Cart(Connection conn, int idOrder, Cart cart) throws SQLException {
        PreparedStatement pstm = conn.prepareCall("insert into OrderDetail(idOrder, idProduct, quantity) values(?, ?, ?)");
        pstm.setInt(1, idOrder);
        pstm.setInt(2, cart.getProduct().getId());
        pstm.setInt(3, cart.getQuantity());
        pstm.executeUpdate();
    }

    // Insert to OrderDetail by listCart
    public static void insertOrderDetail_byIdOrder_listCart(Connection conn, int idOrder, List<Cart> listCart) throws SQLException {
        for (Cart c : listCart) {
            insertOrderDetail_byIdOrder_Cart(conn, idOrder, c);
        }
    }
}
