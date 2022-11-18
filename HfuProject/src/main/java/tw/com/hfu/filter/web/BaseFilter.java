package tw.com.hfu.filter.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class BaseFilter
 */

public abstract class BaseFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	private FilterConfig fConfig;
	
	public void destroy() {
	}
	// 進行轉換將servletRequrst 轉成 HttpServletReqsponse
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
//		//獲得請求介面的路徑
//		 String a=request.getRequestURI();
//	        if(a.contains(".css") || a.contains(".js") || a.contains(".png")|| a.contains(".jpg")){
//	            //如果近來文件如上直接放行
//	               chain.doFilter(request, response);
////	        	doFilter(request, response, chain);
//	            }

		doFilter(request, response, chain);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
		init();
	}
	// 需要初始化的運行，進行複寫（子）
	public void init() {
	}

	// 繼承後需實作doFilter方法
	protected abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException;

	// 供子類別調用config 進行呼叫Filter 初始化變數（web.xml)中
	public FilterConfig getFilterConfig() {
		return fConfig;
	}

}
