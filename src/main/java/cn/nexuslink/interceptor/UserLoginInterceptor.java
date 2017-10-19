package cn.nexuslink.interceptor;

import cn.nexuslink.utils.CookieUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 罗浩 on 2017/5/9.
 */
public class UserLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie cookie = CookieUtil.getCookieByKey(request, "token");
        if (cookie == null)
            return false;
        return true;
    }
}
