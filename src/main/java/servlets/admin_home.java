package servlets;

import beans.Account;
import utils.DBBrandUtil;
import utils.DBOrderUtil;
import utils.DBProductUtil;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "admin-home", value = "/admin-home")
public class admin_home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account loginedUser = MyUtils.getLoginedUser(session);
        if (loginedUser == null || loginedUser.isAdmin() == false) {
            response.sendRedirect(request.getContextPath() + "/login-register");
        } else {
            Connection conn = MyUtils.getStoredConnection(request);

            try {
                // monthly revenue
                double[] dataMonthlyRevenue = DBOrderUtil.getMonthlyRevenue(conn);
                request.setAttribute("dataMonthlyRevenue", dataMonthlyRevenue);

                // total revenue this month
                double totalRevenueMonth = DBOrderUtil.getTotalRevenueMonthly(conn);
                request.setAttribute("totalRevenueMonth", totalRevenueMonth);

                // total revenue this year
                double totalRevenueYear = DBOrderUtil.getTotalRevenueAnnual(conn);
                request.setAttribute("totalRevenueYear", totalRevenueYear);

                // num of product
                int numProduct = DBProductUtil.getNumProduct(conn);
                request.setAttribute("numProduct", numProduct);

                // num of brand
                int numBrand = DBBrandUtil.getNumBrand(conn);
                request.setAttribute("numBrand", numBrand);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Page name
            request.setAttribute("pageName", "Dashboard");
            request.getRequestDispatcher("WEB-INF/admin/admin-home.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
