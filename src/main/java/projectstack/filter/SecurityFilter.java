package projectstack.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import projectstack.util.jwtUtil;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private jwtUtil util;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException
	{
		
		
		//1.read token from Auth head
		String token=request.getHeader("Authorization");
		if(token!=null)
		{
			
			String username=util.getUsername(token);
			if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				  UserDetails user=userDetailService.loadUserByUsername(username);
				  //validate Token
				 boolean isValid= util.validateToken(token, user.getUsername());
				 if(isValid)
				 {
					 UsernamePasswordAuthenticationToken authtoken=new UsernamePasswordAuthenticationToken(username,user.getPassword(),user.getAuthorities());
					 
					 authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					 
					 SecurityContextHolder.getContext().setAuthentication(authtoken);
				 }
			}
			
			
		}
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "authorization, content-type");
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	response.setContentType(MediaType.APPLICATION_JSON.toString());
        if ("OPTIONS".equals(request.getMethod())) {
        	response.setStatus(HttpServletResponse.SC_OK);
        } else {

            filterChain.doFilter(request, response);

        }
	}

}
