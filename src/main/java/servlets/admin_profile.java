package servlets;

import beans.Account;
import utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "admin-profile", value = "/admin-profile")
public class admin_profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account loginedUser = MyUtils.getLoginedUser(session);
        if (loginedUser == null)
            response.sendRedirect(request.getContextPath() + "/login-register");
        else if (loginedUser.isAdmin() == false)
            response.sendRedirect(request.getContextPath() + "/home");
        else {
            // Page name
            request.setAttribute("pageName", "Profile");
            request.getRequestDispatcher("WEB-INF/admin/admin-profile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
