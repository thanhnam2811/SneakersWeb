package servlets;

import beans.Account;
import beans.Cart;
import beans.Product;
import utils.DBCartUtil;
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

@WebServlet(name = "cart", value = "/cart")
public class cart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type == null)
            doPost(request, response);
        else if (type.equals("AddToCart"))
            AddToCart(request, response);
        else if (type.equals("Update"))
            UpdateCart(request, response);
        else if (type.equals("Delete"))
            DeleteCart(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Kiểm tra người dùng đã đăng nhập (login) chưa.
        Account loginedUser = MyUtils.getLoginedUser(session);

        // Nếu chưa đăng nhập (login).
        if (loginedUser == null) {
            // Redirect (Chuyển hướng) tới trang login.
            response.sendRedirect(request.getContextPath() + "/login-register");
            return;
        }

        // Page name
        request.setAttribute("pageName", "my-account");

        // Nếu người dùng đã login thì forward (chuyển tiếp) tới trang
        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
    }

    private void DeleteCart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Account loginedUser = MyUtils.getLoginedUser(session);
        Connection conn = MyUtils.getStoredConnection(request);

        boolean hasError = false;
        String message = "Unknown Error!";

        int idProduct = -1;

        try {
            idProduct = Integer.parseInt(request.getParameter("idProduct"));
            DBCartUtil.deleteCart_byUsername_idProduct(conn, loginedUser.getUsername(), idProduct);

            List<Cart> updatedCart = DBCartUtil.getCart_byUsername(conn, loginedUser.getUsername());
            MyUtils.storeUserCart(session, updatedCart);
        } catch (NumberFormatException | SQLException e) {
            hasError = true;
            message = e.getMessage();
        }

        if (hasError) {
            request.setAttribute("error_message", message);
            request.getRequestDispatcher("WEB-INF/views/404.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/cart");
        }
    }

    private void UpdateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account loginedUser = MyUtils.getLoginedUser(session);
        Connection conn = MyUtils.getStoredConnection(request);

        boolean hasError = false;
        String message = "Unknown Error!";

        int idProduct = -1;
        int quantity = -1;

        try {
            idProduct = Integer.parseInt(request.getParameter("idProduct"));
            quantity = Integer.parseInt(request.getParameter("new_quantity"));
            DBCartUtil.updateCart_byUsername_idProduct(conn, loginedUser.getUsername(), idProduct, quantity);

            List<Cart> updatedCart = DBCartUtil.getCart_byUsername(conn, loginedUser.getUsername());
            MyUtils.storeUserCart(session, updatedCart);
        } catch (NumberFormatException | SQLException e) {
            hasError = true;
            message = e.getMessage();
        }

        if (hasError) {
            request.setAttribute("error_message", message);
            request.getRequestDispatcher("WEB-INF/views/404.jsp").forward(request, response);
        } else {
            System.out.println("idProduct: " + idProduct + ", quantity = " + quantity);
            response.sendRedirect(request.getContextPath() + "/cart");
        }
    }

    private void AddToCart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Account loginedUser = MyUtils.getLoginedUser(session);

        Connection conn = MyUtils.getStoredConnection(request);

        Product product;
        int idProduct = -1;

        boolean hasError = false;
        String message = "Unknown Error!";

        List<Cart> cartList = MyUtils.getUserCart(session);
        int quantity = 1;

        try {
            quantity = Integer.parseInt(request.getParameter("quantity"));
            idProduct = Integer.parseInt(request.getParameter("idProduct"));
            product = DBProductUtil.getProduct_byId(conn, idProduct);

            if (cartList.isEmpty()) {
                assert product != null;
                cartList.add(new Cart(0, loginedUser.getUsername(), product, quantity, quantity * product.getCost()));
                DBCartUtil.insertCart_byUsername_idProduct(conn, loginedUser.getUsername(), product.getId(), quantity);
            } else {
                boolean isExists = false;
                for (Cart c : cartList) {
                    if (c.getUsername().equals(loginedUser.getUsername())) {
                        assert product != null;
                        if (c.getProduct().getId() == product.getId()) {
                            c.setQuantity(c.getQuantity() + quantity);
                            c.setCost(c.getQuantity() * c.getProduct().getCost());
                            DBCartUtil.updateCart_byUsername_idProduct(conn, c.getUsername(), c.getProduct().getId(), c.getQuantity());
                            isExists = true;
                        }
                    }
                }
                if (!isExists) {
                    assert product != null;
                    cartList.add(new Cart(0, loginedUser.getUsername(), product, quantity, quantity * product.getCost()));
                    DBCartUtil.insertCart_byUsername_idProduct(conn, loginedUser.getUsername(), product.getId(), quantity);
                }
            }
        } catch (NumberFormatException | SQLException e) {
            hasError = true;
            message = e.getMessage();
        }

        if (hasError) {
            request.setAttribute("error_message", message);
            request.getRequestDispatcher("WEB-INF/views/404.jsp").forward(request, response);
        } else {
            MyUtils.storeUserCart(session, cartList);
            System.out.println("idProduct: " + idProduct + ", quantity = " + quantity);
            response.sendRedirect(request.getContextPath() + "/cart");
        }
    }
}
