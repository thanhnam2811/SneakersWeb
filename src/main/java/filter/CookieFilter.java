package filter;

import beans.Account;
import beans.Cart;
import utils.DBAccountUtil;
import utils.DBCartUtil;
import utils.MyUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {

	public CookieFilter() {
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		Account userInSession = MyUtils.getLoginedUser(session);
		// 
		if (userInSession != null) {
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
			chain.doFilter(request, response);
			return;
		}

		// Connection đã được tạo trong JDBCFilter.
		Connection conn = MyUtils.getStoredConnection(request);

		// Cờ (flag) để kiểm tra Cookie.
		String checked = (String) session.getAttribute("COOKIE_CHECKED");
		if (checked == null && conn != null) {
			String userName = MyUtils.getUserNameInCookie(req);
			try {
				Account user = DBAccountUtil.findAccount(conn, userName);
				List<Cart> cart = DBCartUtil.getCart_byUsername(conn, userName);
				MyUtils.storeLoginedUser(session, user);
				MyUtils.storeUserCart(session, cart);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// Đánh dấu đã kiểm tra Cookie.
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
		}

		chain.doFilter(request, response);
	}

}