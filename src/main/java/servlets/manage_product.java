package servlets;

import beans.Account;
import beans.Brand;
import beans.Product;
import utils.DBBrandUtil;
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
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "manage-product", value = "/manage-product")
public class manage_product extends HttpServlet {
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
            List<Product> listProduct = null;


            try {
                listProduct = DBProductUtil.getAllProduct(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("conn", conn);
            // Page name
            request.setAttribute("pageName", "Product");
            request.getRequestDispatcher("WEB-INF/admin/tables-product.jsp").forward(request, response);
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
            DeleteProduct(request, response,id);
        else if(type.equals("add"))
            addProduct(request,response);
        else if(type.equals("edit"))
            editProduct(request,response,id);
    }
    private void DeleteProduct(HttpServletRequest request, HttpServletResponse response,String id) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(request);
        try {
            DBProductUtil.DeleteProductByID(conn,Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new manage_product().doGet(request,response);
    }
    private void editProduct(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {

        String idBrand = request.getParameter("_idBrand");
        String name = request.getParameter("_name");
        String image =  request.getParameter("_image");
        String describe = request.getParameter("_describe");
        String quantity = request.getParameter("_quantity");
        String cost =  request.getParameter("_cost");
        String saleDate =  request.getParameter("_saleDate");

        Product product = new Product(Integer.parseInt(idBrand),name,image,describe,
                Integer.parseInt(quantity),Double.valueOf(cost), Date.valueOf(saleDate));

        try {
            Connection conn = MyUtils.getStoredConnection(request);
           DBProductUtil.UpdateProductByID(conn,product,Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new manage_product().doGet(request,response);

    }
    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idBrand = request.getParameter("_idBrand");
        String name = request.getParameter("_name");
        String image =  request.getParameter("_image");
        String describe = request.getParameter("_describe");
        String quantity = request.getParameter("_quantity");
        String cost =  request.getParameter("_cost");
        String saleDate =  request.getParameter("_saleDate");


        Product product = new Product(Integer.parseInt(idBrand),name,image,describe,
                Integer.parseInt(quantity),Double.valueOf(cost), Date.valueOf(saleDate));

        try {
            Connection conn = MyUtils.getStoredConnection(request);
            DBProductUtil.insertProduct(conn,product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new manage_product().doGet(request,response);

    }
}
