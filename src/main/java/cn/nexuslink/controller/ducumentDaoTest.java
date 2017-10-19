package cn.nexuslink.controller;

import cn.nexuslink.dao.IDocumentDao;
import cn.nexuslink.pojo.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by 罗浩 on 2017/4/24.
 */

@Controller
@RequestMapping ("/test")
public class ducumentDaoTest {
    @Resource
    private IDocumentDao documentDao;

    /**
     * 测试成功，可以与本地数据库取得数据交互
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "/getnum")
    public String getnum(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        Document result = null;
        try{
            out=response.getWriter();
            result = documentDao.getDocById(1);
            out.print(result+result.getTitle());

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            out.close();
        }
        return null;
    }
}
