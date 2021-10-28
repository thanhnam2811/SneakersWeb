package utils;

import beans.Account;
import conn.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBOrderUtil {
    // Insert new Order
    public static int insertOrder(Connection conn, Account user) throws SQLException {
        PreparedStatement pstm = conn.prepareCall("insert into [Order](username, address, phoneNumber, fullname) " +
                "output inserted.id values (?, ?, ?, ?)");
        pstm.setString(1, user.getUsername());
        pstm.setString(2, user.getAddress());
        pstm.setString(3, user.getPhoneNumber());
        pstm.setString(4, user.getFullname());

        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionUtils.getConnection();
        Account user = DBAccountUitl.findAccount(conn, "nam");
        int idOrder_inserted = insertOrder(conn, user);
        System.out.println(idOrder_inserted);
    }
}
