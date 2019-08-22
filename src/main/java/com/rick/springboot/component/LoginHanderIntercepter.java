package com.rick.springboot.component;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * 实现登录检查用的拦截器
 */
public class LoginHanderIntercepter implements HandlerInterceptor {

    //目标方法执行前预检查
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser==null){

            //如果是空则表示未登录
            //转发之前,发送错误消息
            request.setAttribute("msg","没有权限，请先登录");
            //返回登录页面
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else {
            //如果不是空则表示已经登录，放行请求
            return true;
        }


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
