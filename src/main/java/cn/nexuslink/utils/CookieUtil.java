package cn.nexuslink.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 罗浩 on 2017/4/25.
 */
public class CookieUtil {

    public  static Cookie getCookieByKey(HttpServletRequest request,String key){
        Cookie[] cookies = request.getCookies();
        Cookie cookie=null;
        if(null!=cookies){
            for (Cookie c:cookies
                 ) {
                if (c.getName().equals(key)) {
                    cookie = c;
                }
            }
        }
        return cookie;
    }
}
