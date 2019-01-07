package com.example.common.filter;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Order(1)
@WebFilter(filterName = "testFilter1", urlPatterns = "/*")
public class TestFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
 
  }
 
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
    System.out.println("uri:"+httpRequest.getRequestURI());
    filterChain.doFilter(servletRequest,servletResponse);
  }
 
  @Override
  public void destroy() {
 
  }


}