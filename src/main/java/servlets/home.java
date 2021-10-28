package servlets;

import beans.Product;
import utils.DBProductUtil;
import utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "home", value = "/home")
public class home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn;
        List<Product> listPNike;
        List<Product> listPAdidas;
        List<Product> listLastestPurchasedProduct;
        Product pNike;
        Product pAdidas;

        try {
            conn = MyUtils.getStoredConnection(request);
            listPNike = DBProductUtil.getAllNikeProduct(conn);
            listPAdidas = DBProductUtil.getAllAdidasProduct(conn);
//            listLastestPurchasedProduct = DBProductUtil.getLastestPurchasedProduct(conn);
            pNike = DBProductUtil.getSelectionNikeProduct(conn);
            pAdidas = DBProductUtil.getSelectionAdidasProduct(conn);

            // Make random
            Collections.shuffle(listPNike);
            Collections.shuffle(listPAdidas);

            request.setAttribute("listPNike", listPNike);
            request.setAttribute("listPAdidas", listPAdidas);
//            request.setAttribute("listLastestPurchasedProduct", listLastestPurchasedProduct);
            request.setAttribute("pNike", pNike);
            request.setAttribute("pAdidas", pAdidas);

            // Page name
            request.setAttribute("pageName", "home");

            request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
//            request.setAttribute("eMessage", e.getMessage());
//            request.getRequestDispatcher("WEB-INF/views/404.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
