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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionUtils.getConnection();
        List<Comment> listC = getComment_byIdProduct(conn,1);
        for (Comment c: listC)
            System.out.println(c);
    }
}
