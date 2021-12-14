package servlets;

import beans.Account;
import beans.Brand;
import beans.Product;
import utils.DBBrandUtil;
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

@WebServlet(name = "manage-product", value = "/manage-product")
public class manage_product extends HttpServlet {
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
            List<Product> listProduct = null;


            try {
                listProduct = DBProductUtil.getAllProduct(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("conn", conn);
            // Page name
            request.setAttribute("pageName", "Product");
            request.getRequestDispatcher("WEB-INF/admin/tables-product.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
