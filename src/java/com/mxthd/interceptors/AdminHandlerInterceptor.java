package com.mxthd.interceptors;

import com.mxthd.bean.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User login_user = (User)request.getSession().getAttribute("login_user");
        if(login_user!=null && login_user.getType()==1){
            return true;
        }
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<html>\n" +
                "<body>\n" +
                "<h2>您不是管理员!</h2>\n" +
                "<script>\n" +
                "    var i =5;\n" +
                "    function djs() {\n" +
                "        if(i==0){\n" +
                "            window.location.href=\"/\";\n" +
                "        }\n" +
                "        i--;\n" +
                "        setTimeout(\"djs()\",1000);\n" +
                "    }\n" +
                "    djs();\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>\n");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User login_user = (User)request.getSession().getAttribute("login_user");
        if(login_user==null && login_user.getType()!=1){
            response.getWriter().flush();
            response.getWriter().close();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
