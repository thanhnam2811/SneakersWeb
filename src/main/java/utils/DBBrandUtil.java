package utils;

import beans.Brand;
import conn.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBBrandUtil {
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

    public static void insertBrand(Connection conn,Brand brand) throws SQLException{
        PreparedStatement pstm = conn.prepareCall("insert into Brand(name, email, logo) " +
                "values(?, ?, ?)");
        pstm.setString(1, brand.getName());
        pstm.setString(2, brand.getEmail());
        pstm.setString(3, brand.getLogo());
        pstm.execute();

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionUtils.getConnection();
        List<Brand> listB = getAllBrand(conn);
        for (Brand b : listB ) {
            System.out.println(b);
        }
    }
}
