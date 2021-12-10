package servlets;

import beans.Brand;
import utils.DBBrandUtil;
import utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "brandManagement", value = "/brandManagement")
public class brandManagement extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn;
        List<Brand>  brandList;

        try {
            conn = MyUtils.getStoredConnection(request);
            brandList = DBBrandUtil.getAllBrand(conn);

            request.setAttribute("brandList", brandList);

            request.getRequestDispatcher("/WEB-INF/views/mangge-brand.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type.equals("forwardNew"))
            forwardNewBrand(request, response);
        else if (type.equals("delete"))
            DeleteBrand(request, response);
        else if(type.equals("add"))
            addBrand(request,response);
    }

    private void forwardNewBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/new-brand.jsp").forward(request, response);

    }
    private void DeleteBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
    private void addBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("brandName");
        String image = request.getParameter("brandImage");
        String email =  request.getParameter("brandEmail");

        Brand brand = new Brand(name,email,image);


        try {
            Connection conn = MyUtils.getStoredConnection(request);
            DBBrandUtil.insertBrand(conn,brand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new brandManagement().doGet(request,response);

    }
}
