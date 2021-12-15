package servlets;

import beans.Account;
import beans.Order;
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

@WebServlet(name = "my-account", value = "/my-account")
public class my_account extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Kiểm tra người dùng đã đăng nhập (login) chưa.
        Account loginedUser = MyUtils.getLoginedUser(session);

        // Nếu chưa đăng nhập (login).
        if (loginedUser == null) {
            // Redirect (Chuyển hướng) tới trang login.
            response.sendRedirect(request.getContextPath() + "/login-register");
            return;
        }
        // Lưu thông tin vào request attribute trước khi forward (chuyển tiếp).
        request.setAttribute("user", loginedUser);
        if (loginedUser.isAdmin())
            response.sendRedirect(request.getContextPath() + "/admin-profile");
        else {
            Connection conn = MyUtils.getStoredConnection(request);
            List<Order> listOrder = null;

            try {
                listOrder = DBOrderUtil.getOrderByUsername(conn,loginedUser.getUsername());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("conn", conn);
            request.setAttribute("listOrder", listOrder);
            // Page name
            request.setAttribute("pageName", "my-account");

            // Nếu người dùng đã login thì forward (chuyển tiếp) tới trang
            request.getRequestDispatcher("/WEB-INF/views/my-account.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
