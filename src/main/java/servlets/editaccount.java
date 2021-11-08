package servlets;

import beans.Account;
import utils.DBAccountUtil;
import utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "edit-account", value = "/edit-account")
public class editaccount extends HttpServlet {
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
        request.getRequestDispatcher("/WEB-INF/views/edit-account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type.equals("edit-account"))
            EditAccount(request, response);
        else if (type.equals("change-password"))
            ChangePassword(request, response);
    }

    private void ChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean hasError = false;
        String message = "unknown error!";
        HttpSession session = request.getSession();
        Account user = MyUtils.getLoginedUser(session);

        // Nếu chưa đăng nhập (login).
        if (user == null) {
            // Redirect (Chuyển hướng) tới trang login.
            response.sendRedirect(request.getContextPath() + "/login-register");
            return;
        }

        String username = user.getUsername();
        String old_password = request.getParameter("_oldpassword");
        String new_password = request.getParameter("_newpassword");

        try {
            Connection conn = MyUtils.getStoredConnection(request);
            boolean isSuccess = DBAccountUtil.changePassword(conn, username, old_password, new_password);
            if (!isSuccess) {
                hasError = true;
                message = "Incorrect password!";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            hasError = true;
            message = throwables.getMessage();
        }

        if (hasError) {
            request.setAttribute("change_password_message", message);
            doGet(request, response);
        } else {
            MyUtils.storeLoginedUser(session, user);
            response.sendRedirect(request.getContextPath() + "/my-account");
        }

    }

    private void EditAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String message = "unknown error!";
        boolean hasError = false;

        String _username = request.getParameter("_username");
        String _fullname = request.getParameter("_fullname");
        String _avatar = request.getParameter("_avatar");
        String _phoneNumber = request.getParameter("_phoneNumber");
        String _address = request.getParameter("_address");
        String _sex = request.getParameter("_sex");
        Date _dateOfBirth = null;

        HttpSession session = request.getSession();
        Account user = MyUtils.getLoginedUser(session);

        try {
            _dateOfBirth = Date.valueOf(request.getParameter("_dateOfBirth"));
        } catch (Exception e) {
            message = "Invalid Date!";
            hasError = true;
        }

        try {
            Connection conn = MyUtils.getStoredConnection(request);
            DBAccountUtil.editAccount(conn, _username, _fullname, _avatar, _phoneNumber, _address, _sex, _dateOfBirth);

            // Cập nhật user
            user = DBAccountUtil.findAccount(conn, user.getUsername());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            message = throwables.getMessage();
            hasError = true;
        }

        if (hasError) {
            request.setAttribute("edit_account_message", message);
            doGet(request, response);
        } else {
            MyUtils.storeLoginedUser(session, user);
            response.sendRedirect(request.getContextPath() + "/my-account");
        }

    }
}
