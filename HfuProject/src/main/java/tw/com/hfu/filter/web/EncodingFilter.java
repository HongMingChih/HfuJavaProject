package tw.com.hfu.filter.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 處理中文亂碼
 */
@WebFilter(filterName = "/encodingFilter", urlPatterns = "/*")
public class EncodingFilter extends BaseFilter {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String request_uri = request.getRequestURI();
		if (request_uri.toString().contains(".css") || request_uri.toString().contains(".js")
				|| request_uri.toString().contains(".jpg") || request_uri.toString().contains(".png")
				|| request_uri.toString().contains(".do")) {

			chain.doFilter(request, response);
		}else {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		chain.doFilter(request, response);// 放行
		}
	}
}
