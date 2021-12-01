package servlets;

import beans.Account;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

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

        // Page name
        request.setAttribute("pageName", "my-account");

        // Nếu người dùng đã login thì forward (chuyển tiếp) tới trang
        request.getRequestDispatcher("/WEB-INF/views/my-account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
