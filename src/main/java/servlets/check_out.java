package servlets;

import beans.Account;
import beans.Cart;
import utils.DBCartUtil;
import utils.DBOrderDetailUtil;
import utils.DBOrderUtil;
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

@WebServlet(name = "check-out", value = "/check-out")
public class check_out extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Page name
        request.setAttribute("pageName", "my-account");

        request.getRequestDispatcher("WEB-INF/views/check-out.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        Account user = MyUtils.getLoginedUser(session);
        List<Cart> cartList = MyUtils.getUserCart(session);

        try {
            int idOrder = DBOrderUtil.insertOrder(conn, user);
            if (idOrder == -1)
                throw new Exception("Can't create Order");
            else {
                DBOrderDetailUtil.insertOrderDetail_byIdOrder_listCart(conn, idOrder, cartList);
                cartList = DBCartUtil.getCart_byUsername(conn, user.getUsername());
                MyUtils.storeUserCart(session, cartList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/my-account");
    }
}
