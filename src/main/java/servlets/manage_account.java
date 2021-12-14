package servlets;

import beans.Account;
import utils.DBAccountUtil;
import utils.DBCommentUtil;
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

@WebServlet(name = "manage-account", value = "/manage-account")
public class manage_account extends HttpServlet {
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
            List<Account> listAccount = null;

            try {
                listAccount = DBAccountUtil.getAllAccount(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //
            request.setAttribute("listAccount", listAccount);

            // Page name
            request.setAttribute("pageName", "Account");
            request.getRequestDispatcher("WEB-INF/admin/tables-account.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Delete comment
        String id = request.getParameter("id");

        Connection conn = MyUtils.getStoredConnection(request);
        try {
            DBAccountUtil.DeleteAccountByID(conn,Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new manage_account().doGet(request,response);
    }
}
