package servlets;

import beans.Account;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "manage-comment", value = "/manage-comment")
public class manage_comment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account loginedUser = MyUtils.getLoginedUser(session);
        if (loginedUser == null || loginedUser.isAdmin() == false) {
            response.sendRedirect(request.getContextPath() + "/login-register");
        } else {
            // Page name
            request.setAttribute("pageName", "Comment");
            request.getRequestDispatcher("WEB-INF/admin/tables-comment.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
