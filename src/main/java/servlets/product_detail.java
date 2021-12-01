package servlets;

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
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "product-detail", value = "/product-detail")
public class product_detail extends HttpServlet {
    static int id;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String brandName = null;
        Product product = null;
        List<Comment> listComment = new ArrayList<>();
        boolean hasError = false;
        String message = "Unknown error!";

        try {
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (Exception ignored) { }

            Connection conn = MyUtils.getStoredConnection(request);
            product = DBProductUtil.getProduct_byId(conn, id);
            brandName = product.getBrandName(conn);
            listComment = DBCommentUtil.getComment_byIdProduct(conn, id);
        } catch (SQLException e) {
            hasError = true;
            message = e.getMessage();
        }
        if (hasError) {
            request.setAttribute("error_message", message);
            request.getRequestDispatcher("WEB-INF/views/404.jsp").forward(request, response);
        }
        else {
            // Page name
            request.setAttribute("pageName", "product");

            request.setAttribute("listComment", listComment);
            request.setAttribute("brandName", brandName);
            request.setAttribute("product", product);
            request.getRequestDispatcher("WEB-INF/views/product-detail.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean hasError = false;
        String message = "";

        int idProduct = Integer.parseInt(request.getParameter("idProduct"));
        String username = request.getParameter("username");
        String comment = request.getParameter("new_comment");

        try {
            Connection conn = MyUtils.getStoredConnection(request);
            boolean isSuccess = DBCommentUtil.createComment(conn, idProduct, username, comment);
            if (!isSuccess)
                throw new Exception("Comment Fail!");
        } catch (Exception e) {
            hasError = true;
            message = e.getMessage();
        }
        if (hasError)
            request.setAttribute("comment_message", message);

        id = idProduct;
        doGet(request, response);
    }
}
