package com.vault.demo.filter;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2020/3/19.
 */
@Component //部件，组件@Service @Resposity @Controller
public class ActInterceptor implements HandlerInterceptor {
    //请求到控制器前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //请求地址
        String path = request.getServletPath();
        //判断是否登录

        HttpSession session = request.getSession();
        Object obj = session.getAttribute("user");
        Object ren = session.getAttribute("ren");

        if(obj==null){//没有登录
            //跳到登录界面
            response.sendRedirect(request.getContextPath()+"/user/tologin");
            System.out.println("拦截url："+path);
            return false;
        }else {
            if("/user/toAO".equals(path)) return true;

            if(ren == null){
                response.sendRedirect(request.getContextPath()+"/user/toAO");
                System.out.println("拦截url："+path);
                return false;
            }else {
                return true;
            }
        }
    }

    //请求到控制器，视图渲染前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    //完成请求，视图渲染后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
