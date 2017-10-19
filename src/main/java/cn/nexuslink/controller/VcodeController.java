package cn.nexuslink.controller;

import cn.nexuslink.utils.validateCode.ValidateCode;
import cn.nexuslink.utils.validateCode.VcodeImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import static cn.nexuslink.utils.validateCode.ValidateCode.TYPE_ALL_MIXED;

/**
 * Created by 罗浩 on 2017/5/6.
 */
@Controller
@RequestMapping(value = "/api/Vcode")
public class VcodeController {

    @RequestMapping(value = "/VcodeImage")
    public void creatVcodeImage(HttpServletRequest request, HttpServletResponse  response){
        String Vcode = ValidateCode.creatValidateCode(TYPE_ALL_MIXED,4,null);//生成含有大小写数字三种类型的子字符串、字符个数为4
        //字符串图片宽度160，高度40，干扰线条数5，高低位置随机
        System.out.println(Vcode);
        BufferedImage bufferedImage = VcodeImage.creatImageVcode(Vcode, 160, 40, 5,true, Color.WHITE, Color.BLACK, Color.WHITE);
        request.getSession().setAttribute("Vcode", Vcode);
        System.out.println("===================");
        OutputStream outputStream=null;
        try {
            outputStream=response.getOutputStream();
            ImageIO.write(bufferedImage, "jpg",outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
