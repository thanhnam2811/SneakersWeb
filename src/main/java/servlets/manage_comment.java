package servlets;

import beans.Account;
import beans.Comment;
import beans.Product;
import utils.DBCommentUtil;
import utils.DBProductUtil;
import utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "manage-comment", value = "/manage-comment")
public class manage_comment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account loginedUser = MyUtils.getLoginedUser(session);
        if (loginedUser == null)
            response.sendRedirect(request.getContextPath() + "/login-register");
        else if (loginedUser.isAdmin() == false)
            response.sendRedirect(request.getContextPath() + "/home");
        else {

            Connection conn = MyUtils.getStoredConnection(request);
            List<Comment> CommentList = null;
            List<Product> ProductList = null;

            try {
                CommentList = DBCommentUtil.getAllComment(conn);
                ProductList = DBProductUtil.getAllProduct(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //
            request.setAttribute("CommentList", CommentList);
            request.setAttribute("ProductList", ProductList);
            // Page name
            request.setAttribute("pageName", "Comment");
            request.getRequestDispatcher("WEB-INF/admin/tables-comment.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Delete comment
        String id = request.getParameter("id");

        Connection conn = MyUtils.getStoredConnection(request);
        try {
            DBCommentUtil.DeleteCommentByID(conn,Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new manage_comment().doGet(request,response);
    }
}
