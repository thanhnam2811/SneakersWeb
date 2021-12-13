package utils;

import beans.Account;
import beans.Comment;
import conn.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBAccountUtil {
    // findAccount
    public static Account findAccount(Connection conn, String username, String password) throws SQLException {
        PreparedStatement pstm = conn.prepareCall("{call findAccount(?, ?)}");
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String fullname = rs.getString("fullname");
            username = rs.getString("username");
            password = rs.getString("password");
            String avatar = rs.getString("avatar");
            String phoneNumber = rs.getString("phoneNumber");
            String address = rs.getString("address");
            String sex = rs.getString("sex");
            Date dateOfBirth = rs.getDate("dateOfBirth");
            boolean isAdmin = rs.getBoolean("isAdmin");
            return new Account(id, username, password, fullname, avatar, phoneNumber, address, sex, dateOfBirth, isAdmin);
        }
        return null;
    }

    public static Account findAccount(Connection conn, String username) throws SQLException {
        PreparedStatement pstm = conn.prepareCall("select * from Account where username = ?");
        pstm.setString(1, username);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            username = rs.getString("username");
            String password = rs.getString("password");
            String fullname = rs.getString("fullname");
            String avatar = rs.getString("avatar");
            String phoneNumber = rs.getString("phoneNumber");
            String address = rs.getString("address");
            String sex = rs.getString("sex");
            Date dateOfBirth = rs.getDate("dateOfBirth");
            boolean isAdmin = rs.getBoolean("isAdmin");
            return new Account(id, username, password, fullname, avatar, phoneNumber, address, sex, dateOfBirth, isAdmin);
        }
        return null;
    }

    // Create new account
    public static void createAccount(Connection conn, String username, String password,
                                     String fullname, String avatar, String phoneNumber,
                                     String address, String sex, Date dateOfBirth) throws SQLException {
        PreparedStatement pstm = conn.prepareCall("insert into Account(username, password, fullname, avatar, phoneNumber, address, sex, dateOfBirth) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?);");
        pstm.setString(1, username);
        pstm.setString(2, password);
        pstm.setString(3, fullname);
        pstm.setString(4, avatar);
        pstm.setString(5, phoneNumber);
        pstm.setString(6, address);
        pstm.setString(7, sex);
        pstm.setDate(8, dateOfBirth);
        pstm.executeUpdate();
    }

    // Edit Account
    public static void editAccount(Connection conn, String username,
                                   String fullname, String avatar, String phoneNumber,
                                   String address, String sex, Date dateOfBirth) throws SQLException {
        PreparedStatement pstm = conn.prepareCall("update Account " +
                "set fullname = ?, avatar = ?, phoneNumber = ?, address = ?, sex = ?, dateOfBirth = ? " +
                "where username = ?");
        pstm.setString(1, fullname);
        pstm.setString(2, avatar);
        pstm.setString(3, phoneNumber);
        pstm.setString(4, address);
        pstm.setString(5, sex);
        pstm.setDate(6, dateOfBirth);
        pstm.setString(7, username);
        pstm.executeUpdate();
    }

    // Change Password
    public static boolean changePassword(Connection conn, String username, String oldpassword, String newpassword) throws SQLException {
        System.out.println("u: " + username + ", op: " + oldpassword + ", np: " + newpassword);
        PreparedStatement pstm = conn.prepareCall("{call changePassword(?, ?, ?)}");
        pstm.setString(1, username);
        pstm.setString(2, oldpassword);
        pstm.setString(3, newpassword);
        if (pstm.executeUpdate() == 0)
            return false;
        return true;
    }
    // Get all account
    public static List<Account> getAllAccount(Connection conn) throws SQLException {
        List<Account> listC = new ArrayList<Account>();
        PreparedStatement pstm = conn.prepareCall("select * from Account");

        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            String username = rs.getString(2);
            String password = rs.getString(3);
            String fullname = rs.getString(4);
            String avatar = rs.getString(5);
            String phoneNumber = rs.getString(6);
            String address = rs.getString(7);
            String sex  = rs.getString(8);
            Date dateOfBirth = rs.getDate(9);
            boolean isAdmin = rs.getBoolean(10);
            Account c = new Account(id,username,password,fullname,avatar,phoneNumber,address,sex,dateOfBirth,isAdmin);
            listC.add(c);
        }

        return listC;
    }
    //Delete Account by ID
    public static void DeleteAccountByID(Connection conn,int IDAccount) throws SQLException {
        String sql = "DELETE FROM Account Where id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, IDAccount);
        pstm.executeUpdate();
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionUtils.getConnection();
//        boolean a = changePassword(conn, "thanhnam1324", "namnamnam", "thanhnam1324");
//        System.out.println(a);
        System.out.println(findAccount(conn, "nam", "namnamnam"));
    }

}
