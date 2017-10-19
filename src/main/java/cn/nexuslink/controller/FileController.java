package cn.nexuslink.controller;

import cn.nexuslink.pojo.Document;
import cn.nexuslink.pojo.Member;
import cn.nexuslink.pojo.json.ResponseJson;
import cn.nexuslink.service.impl.CacheService;
import cn.nexuslink.service.impl.DocumentService;
import cn.nexuslink.utils.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static cn.nexuslink.pojo.constant.StatusCode.*;

/**
 * Created by 罗浩 on 2017/4/25.
 */
@Controller
@RequestMapping(value = "/api/file")
public class FileController {

    @Resource
    private DocumentService documentService;

    @Resource
    private CacheService cacheService;

    @ResponseBody
    @RequestMapping(value = "/upload")
    public ResponseJson uploadDoc(@RequestParam("description") String description,@RequestParam("file") MultipartFile file, HttpServletRequest request){
        final String fileLocation = request.getServletContext().getInitParameter("fileLunixLocation");
        String fileName = file.getOriginalFilename();
        String type=ValidateFileType(fileName);
        Document doc = new Document();

        if(file!=null&&file.getSize()>0&&type!=null){

            doc.setTitle(fileName);
            fileName = System.currentTimeMillis() + "." + type ;
            File realfile = new File(fileLocation,fileName);
            try {
                file.transferTo(realfile);
                //通过cookie得到token,然后通过token得到缓存中的用户对象
                Cookie cookie = CookieUtil.getCookieByKey(request,"token");
                if (cookie == null) {
                    return new ResponseJson(TOKEN_NOT_EXIST.getCode(),TOKEN_NOT_EXIST.getMessage(),"cookie中不存在token，请重新登陆！");
                }

                Member member= (Member) cacheService.getCacheObj("tokenCache",cookie.getValue());
                doc.setAuthorId(member.getId());
                doc.setUrl(fileName);
                doc.setDescription(description);
                documentService.uploadDoc(doc);
                return new ResponseJson(OK.getCode(), OK.getMessage(),"上传成功！");
            } catch (IOException e) {
                return new ResponseJson(INTERNAL_SERVER_ERROR.getCode(),INTERNAL_SERVER_ERROR.getMessage(),"服务器保存文件失败！");
            }
        }

        return new ResponseJson(INVALID_REQUEST.getCode(),INVALID_REQUEST.getMessage(),"文件格式或内容为空！");
    }

    @RequestMapping(value = "/addLookCount",method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson addCount(@RequestParam("docId") int docId,@RequestParam("column") String column){
        documentService.addCount(docId,column);
        return new ResponseJson(OK.getCode(),OK.getMessage(),"增加访问量或者下载量成功！");
    }

    @RequestMapping(value = "/getDocs",method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson getDocs(@RequestParam(value = "pageCount",defaultValue = "10",required = false)int pageCount,
                                @RequestParam(value = "size",defaultValue = "1",required = false)int size){
        List<Document> result = documentService.getDocs(pageCount,size,"creat_time","DESC");
        return new ResponseJson(OK.getCode(), OK.getMessage(), result);
    }

    @RequestMapping(value = "/getDocNum",method = RequestMethod.GET)
    @ResponseBody
    public ResponseJson getDocsCount(){
        int num = documentService.getDocNum();
        return new ResponseJson(OK.getCode(), OK.getMessage(),num);
    }


    //----------------------------私有方法区--------------
    //得到上传文件的属性后后缀名，只允许pdf和doc两种文件格式

    public static String ValidateFileType(String originalFilename){
        String type = originalFilename. substring(originalFilename.lastIndexOf(".")+1);
        if("pdf".equals(type)||"docx".equals(type)||"doc".equals(type))
            return type;
        return null;
    }
}
