package utils;

import beans.Cart;
import beans.Order;
import beans.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
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
    //Get orderDetail By idOrder
    public static List<OrderDetail> getorderDetailByidOrder(Connection conn, int idOrder) throws SQLException {
        List<OrderDetail> listB = new ArrayList<>();

        PreparedStatement pstm = conn.prepareCall("Select *\n" +
                "From OrderDetail\n" +
                "Where idOrder = ?");

        pstm.setInt(1, idOrder);
        ResultSet rs = pstm.executeQuery();


        while (rs.next()) {
            int id = rs.getInt(1);
            int idOD = rs.getInt(2);
            int idProduct = rs.getInt(3);
            int quatity = rs.getInt(4);
            Double cost = rs.getDouble(5);


            OrderDetail b = new OrderDetail(id,idOD,quatity,idProduct,cost);
            listB.add(b);
        }
        return listB;
    }
    // Insert to OrderDetail by listCart
    public static void insertOrderDetail_byIdOrder_listCart(Connection conn, int idOrder, List<Cart> listCart) throws SQLException {
        for (Cart c : listCart) {
            insertOrderDetail_byIdOrder_Cart(conn, idOrder, c);
        }
    }
}
