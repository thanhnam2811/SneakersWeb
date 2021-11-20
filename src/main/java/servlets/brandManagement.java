package servlets;

import beans.Brand;
import utils.DBBrandUtil;
import utils.DBProductUtil;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
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

    }
}
