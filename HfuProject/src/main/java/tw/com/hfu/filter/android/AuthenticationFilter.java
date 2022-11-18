package tw.com.hfu.filter.android;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/MemberController/*"})
public class AuthenticationFilter extends HttpFilter implements Filter {
       
    private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("Entering AuthenticationFilter");
		
		// transfer the request and response
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		req.getSession(false);
		
		/* ----- check session ----- */
		if(req.isRequestedSessionIdFromCookie()) {
			
			System.out.println("session in cookie? " + req.isRequestedSessionIdFromCookie());
			System.out.println("is it valid? " + req.isRequestedSessionIdValid());
			
			if(!req.isRequestedSessionIdValid()) {
				
				// send 401 if invalid
				res.sendError(401); 
			}
		}
		
		chain.doFilter(req, res);
	}
}
