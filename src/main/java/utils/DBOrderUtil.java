package utils;

import beans.Account;
import beans.Brand;
import beans.Order;
import conn.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBOrderUtil {
    // Get annual total revenue
    public static double getTotalRevenueAnnual(Connection conn) throws SQLException {
        Statement stm = conn.createStatement();

        ResultSet result = stm.executeQuery("select sum(cost) from [Order] " +
                "where year(purchaseDate) = year(getdate())");

        double totalRevenue = 0;

        if(result.next()) {
            totalRevenue = result.getDouble(1);
        }

        return totalRevenue;
    }

    // Get monthly total revenue
    public static double getTotalRevenueMonthly(Connection conn) throws SQLException {
        Statement stm = conn.createStatement();

        ResultSet result = stm.executeQuery("select sum(cost) from [Order] " +
                "where year(purchaseDate) = year(getdate()) and month(purchaseDate) = month(getdate())");

        double totalRevenue = 0;

        if(result.next()) {
            totalRevenue = result.getDouble(1);
        }

        return totalRevenue;
    }

    // Get monthly revenue
    public static double[] getMonthlyRevenue(Connection conn) throws SQLException {
        Statement stm = conn.createStatement();

        ResultSet result = stm.executeQuery("select month(purchaseDate), sum(cost) from [Order] " +
                "where year(purchaseDate) = year(getdate()) " +
                "group by month(purchaseDate) ");

        double[] monthlyRevenue = new double[12];
        for (int i=0; i<12; i++)
            monthlyRevenue[i] = 0;

        while(result.next()) {
            monthlyRevenue[result.getInt(1)-1] = result.getDouble(2);
        }

        return monthlyRevenue;
    }

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
    //Get order By username
    public static List<Order> getOrderByUsername(Connection conn, String user) throws SQLException {
        List<Order> listB = new ArrayList<>();

        PreparedStatement pstm = conn.prepareCall("Select * \n" +
                "From [Order]\n" +
                "Where username = ? \n" +
                "Order by id DESC");

        pstm.setString(1, user);
        ResultSet rs = pstm.executeQuery();


        while (rs.next()) {
            int id = rs.getInt(1);
            String username = rs.getString(2);
            Double cost = rs.getDouble(3);
            Date purchaseDate = rs.getDate(4);
            String address = rs.getString(5);
            String phone = rs.getString(6);
            String fullname = rs.getString(7);

            Order b = new Order(id, username,cost,purchaseDate,address,phone,fullname);
            listB.add(b);
        }
        return listB;
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionUtils.getConnection();
        Account user = DBAccountUtil.findAccount(conn, "nam");
        int idOrder_inserted = insertOrder(conn, user);
        System.out.println(idOrder_inserted);
    }
}
