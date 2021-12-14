package servlets;

import beans.Account;
import beans.Brand;
import beans.Comment;
import beans.Product;
import utils.DBBrandUtil;
import utils.DBCommentUtil;
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

@WebServlet(name = "manage-brand", value = "/manage-brand")
public class manage_brand extends HttpServlet {
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
            List<Brand> listBrand = null;


            try {
                listBrand = DBBrandUtil.getAllBrand(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //
            request.setAttribute("listBrand", listBrand);
            // Page name
            request.setAttribute("pageName", "Brand");
            request.getRequestDispatcher("WEB-INF/admin/tables-brand.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("_id");
        String type = request.getParameter("type");
        //Type
        if(type == null && id == ""){
            type = "add";
        }
        else if(type == null && id != ""){
            type = "edit";
        }

        //Action
        if (type.equals("delete"))
            DeleteBrand(request, response,id);
        else if(type.equals("add"))
            addBrand(request,response);
        else if(type.equals("edit"))
            editBrand(request,response,id);
    }
    private void DeleteBrand(HttpServletRequest request, HttpServletResponse response,String id) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(request);
        try {
            DBBrandUtil.DeleteBrandByID(conn,Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new manage_brand().doGet(request,response);
    }
    private void editBrand(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {

        String name = request.getParameter("_name");
        String image = request.getParameter("_logo");
        String email =  request.getParameter("_email");

        Brand brand = new Brand(name,email,image);


        try {
            Connection conn = MyUtils.getStoredConnection(request);
            DBBrandUtil.UpdateBrandByID(conn,brand,Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new manage_brand().doGet(request,response);

    }
    private void addBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("_name");
        String image = request.getParameter("_logo");
        String email =  request.getParameter("_email");

        Brand brand = new Brand(name,email,image);


        try {
            Connection conn = MyUtils.getStoredConnection(request);
            DBBrandUtil.insertBrand(conn,brand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new manage_brand().doGet(request,response);

    }
}
