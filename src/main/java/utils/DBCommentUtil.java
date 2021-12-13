package utils;

import beans.Comment;
import conn.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBCommentUtil {
    // Get comment by id product
    public static boolean createComment(Connection conn, int idP, String username, String comment) throws SQLException {
        PreparedStatement pstm = conn.prepareCall("insert into Comment(idProduct, username, comment) values(?, ?, ?)");
        pstm.setInt(1, idP);
        pstm.setString(2, username);
        pstm.setString(3, comment);

        if (pstm.executeUpdate() > 0)
            return true;
        else
            return false;
    }

    // Get comment by id product
    public static List<Comment> getComment_byIdProduct(Connection conn, int idP) throws SQLException {
        List<Comment> listC = new ArrayList<Comment>();
        PreparedStatement pstm = conn.prepareCall("select * from Comment where idProduct = ?");
        pstm.setInt(1, idP);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            int id = rs.getInt(1);
            int idProduct = rs.getInt(2);
            String username = rs.getString(3);
            String comment = rs.getString(4);
            Comment c = new Comment(id, idProduct, username, comment);
            listC.add(c);
        }

        return listC;
    }
    // Get comment by usernaem
    public static List<Comment> getByUsername(Connection conn, String tenTK) throws SQLException {
        List<Comment> listC = new ArrayList<Comment>();
        PreparedStatement pstm = conn.prepareCall("select * from Comment where username = ?");
        pstm.setString(1, tenTK);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            int id = rs.getInt(1);
            int idProduct = rs.getInt(2);
            String username = rs.getString(3);
            String comment = rs.getString(4);
            Comment c = new Comment(id, idProduct, username, comment);
            listC.add(c);
        }

        return listC;
    }
    // Get all comment
    public static List<Comment> getAllComment(Connection conn) throws SQLException {
        List<Comment> listC = new ArrayList<Comment>();
        PreparedStatement pstm = conn.prepareCall("select * from Comment");

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            int id = rs.getInt(1);
            int idProduct = rs.getInt(2);
            String username = rs.getString(3);
            String comment = rs.getString(4);
            Comment c = new Comment(id, idProduct, username, comment);
            listC.add(c);
        }

        return listC;
    }
    //Delete Comment by ID
    public static void DeleteCommentByID(Connection conn,int IDcomment) throws SQLException {
        String sql = "DELETE FROM Comment Where id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, IDcomment);
        pstm.executeUpdate();
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionUtils.getConnection();
        List<Comment> listC = getAllComment(conn);
        for (Comment c: listC)
            System.out.println(c);
    }
}
