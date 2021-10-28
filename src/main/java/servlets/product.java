package servlets;

import beans.Brand;
import beans.Product;
import conn.ConnectionUtils;
import utils.DBBrandUtil;
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
import java.util.List;

@WebServlet(name = "product", value = "/product")
public class product extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn;
        List<Product> listP;
        List<Brand> listB;
        int numP_display = 12; // Số sp hiển thị trên trang
        int idBrand = 0, page = 1;
        
        try {
            idBrand = Integer.valueOf(request.getParameter("brand"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            page = Integer.valueOf(request.getParameter("page"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            conn = MyUtils.getStoredConnection(request);

            listP = DBProductUtil.getAllProduct(conn);
            listB = DBBrandUtil.getAllBrand(conn);

            // Filter brand
            if (idBrand != 0) {
                int filterIdBrand = idBrand;
                try {
                    listP.removeIf(p -> p.getIdBrand() != filterIdBrand);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            // Check page
            int maxPage = (listP.size() % numP_display > 0) ? listP.size()/numP_display + 1 : listP.size()/numP_display;

            if (page <= 0 || page > maxPage) {
                System.out.println("page: " + page + " not found! Set page = 1!");
                page = 1;
            }

            // Start and end index product display
            int begin = (page-1) * numP_display;
            int end = begin + numP_display - 1;

            request.setAttribute("page", page);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("begin", begin);
            request.setAttribute("end", end);
            request.setAttribute("brand", idBrand);
            System.out.println("id brand: " + idBrand + ", page: " + page);
            request.setAttribute("listP", listP);
            request.setAttribute("listB", listB);
            request.setAttribute("numP_display", numP_display);

            // Page name
            request.setAttribute("pageName", "product");

            request.getRequestDispatcher("/WEB-INF/views/product.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
