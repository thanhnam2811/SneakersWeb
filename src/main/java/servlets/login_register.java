package servlets;

import beans.Account;
import beans.Cart;
import utils.DBAccountUtil;
import utils.DBCartUtil;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "login-register", value = "/login-register")
public class login_register extends HttpServlet {
    private static String prePage = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        prePage = request.getHeader("referer");

        // Page name
        request.setAttribute("pageName", "login-register");
        request.getRequestDispatcher("WEB-INF/views/login-register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type.equals("login"))
            Login(request, response);
        else if (type.equals("register"))
            Register(request, response);
    }

    private void Register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "Unknown error!";
        boolean hasError = false;

        String _username = request.getParameter("_username");
        String _password = request.getParameter("_password");
        String _fullname = request.getParameter("_fullname");
        String _avatar = request.getParameter("_avatar");
        String _phoneNumber = request.getParameter("_phoneNumber");
        String _address = request.getParameter("_address");
        String _sex = request.getParameter("_sex");
        Date _dateOfBirth;

        try {
            Connection conn = MyUtils.getStoredConnection(request);

            // Check username
            if (DBAccountUtil.findAccount(conn, _username) != null)
                throw new Exception("'" + _username + "' already taken, please choose another!");

            // Check valid Date
            try {
                _dateOfBirth = Date.valueOf(request.getParameter("_dateOfBirth"));
            } catch (Exception e) {
                throw new Exception("Invalid date of birth!");
            }

            // Create new account
            DBAccountUtil.createAccount(conn, _username, _password, _fullname, _avatar, _phoneNumber, _address, _sex, _dateOfBirth);
        } catch (Exception e) {
            hasError = true;
            message = e.getMessage();
        }
        if (hasError) {
            request.setAttribute("register_message", message);
            doGet(request, response);
        } else
            response.sendRedirect(request.getContextPath() + "/login-register");
    }

    private void Login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        Account user = null;
        List<Cart> userCart = new ArrayList<>();
        boolean hasError = false;
        String message = null;

        if (username == null || password == null || username.length() == 0 || password.length() == 0) {
            hasError = true;
            message = "Required username and password!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // T??m user trong DB.
                user = DBAccountUtil.findAccount(conn, username, password);
                if (user == null) {
                    message = "username or password invalid";
                    throw new Exception(message);
                }
                userCart = DBCartUtil.getCart_byUsername(conn, user.getUsername());
            } catch (Exception e) {
                e.printStackTrace();
                hasError = true;
                message = e.getMessage();
            }
        }
        // Trong tr?????ng h???p c?? l???i,
        // forward (chuy???n h?????ng) t???i /WEB-INF/views/login.jsp
        if (hasError) {

            // L??u c??c th??ng tin v??o request attribute tr?????c khi forward.
            request.setAttribute("login_message", message);
            request.setAttribute("username", username);
            request.setAttribute("password", password);

            // Forward (Chuy???n ti???p) t???i trang /WEB-INF/views/login.jsp
            doGet(request, response);
        }
        // Tr?????ng h???p kh??ng c?? l???i.
        // L??u th??ng tin ng?????i d??ng v??o Session.
        // V?? chuy???n h?????ng sang trang userInfo.
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
            MyUtils.storeUserCart(session, userCart);

            // N???u ng?????i d??ng ch???n t??nh n??ng "Remember me".
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            }
            // Ng?????c l???i x??a Cookie
            else {
                MyUtils.deleteUserCookie(response);
            }
            if (user.isAdmin())
                // Chuy???n sang trang admin
                response.sendRedirect(request.getContextPath() + "/admin-home");
            else {
                System.out.println(prePage);
                if (prePage == null || prePage.contains("login"))
                    response.sendRedirect(request.getContextPath() + "/home");
                else
                    // Redirect (Chuy???n h?????ng) sang trang /userInfo.
                    response.sendRedirect(prePage);
            }
        }
    }

}
