package com.bula.gzh.Config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(urlPatterns = {"/*"})
public class CrosConfig implements Filter {
    private Logger logger = LoggerFactory.getLogger(com.bula.gzh.Config.CrosConfig.class);

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept,Authorization");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
        logger.info("过滤器启动成功");
        filterChain.doFilter(servletRequest, (ServletResponse) response);
    }

    public void destroy() {
    }
}

