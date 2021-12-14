package utils;

import beans.Brand;
import conn.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBBrandUtil {

    // Get number of Brand
    public static int getNumBrand(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("select count(id) from Brand");

        if (rs.next())
            return rs.getInt(1);
        return 0;
    }

    // get all brand
    public static List<Brand> getAllBrand(Connection conn) throws SQLException {
        List<Brand> listB = new ArrayList<>();

        PreparedStatement pstm = conn.prepareCall("select * from Brand");
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String email = rs.getString(3);
            String logo = rs.getString(4);

            Brand b = new Brand(id, name, email, logo);
            listB.add(b);
        }
        return listB;
    }

    public static void insertBrand(Connection conn, Brand brand) throws SQLException {
        PreparedStatement pstm = conn.prepareCall("insert into Brand(name, email, logo) " +
                "values(?, ?, ?)");
        pstm.setString(1, brand.getName());
        pstm.setString(2, brand.getEmail());
        pstm.setString(3, brand.getLogo());
        pstm.execute();

    }
    //Delete Brand by ID
    public static void DeleteBrandByID(Connection conn,int IDBrand) throws SQLException {
        String sql = "DELETE FROM Brand Where id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, IDBrand);
        pstm.executeUpdate();
    }
    //Update Brand by ID
    public static void UpdateBrandByID(Connection conn,Brand brand,int IDBrand) throws SQLException {
        String sql = "UPDATE  Brand " +
                "SET name = ? ,email = ?, logo =?" +
                "  Where id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, brand.getName());
        pstm.setString(2, brand.getEmail());
        pstm.setString(3, brand.getLogo());
        pstm.setInt(4, IDBrand);
        pstm.executeUpdate();
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionUtils.getConnection();
        List<Brand> listB = getAllBrand(conn);
        for (Brand b : listB) {
            System.out.println(b);
        }
    }
}
