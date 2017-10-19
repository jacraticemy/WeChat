package cn.nexuslink.utils.validateCode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by 罗浩 on 2017/3/21.
 */
public class VcodeImage {

    /**
     * 生成图片验证码
     *
     * @param Vcode  要生成图片的验证码
     * @param width   图片宽度
     * @param height  图片高度
     * @param inerLine   图片中干扰线的条数
     * @param randomLocation  每个字符串的高低位置是否随机
     * @param backColor  图片颜色，若为null，表示采用随机颜色
     * @param foreColor  字体颜色，若为null，表示采用随机颜色
     * @param lineColor   干扰线颜色，若为null，则采用随机颜色
     * @return   图片缓存对象
     */
    public static BufferedImage creatImageVcode(String Vcode, int width, int height, int inerLine, boolean randomLocation,
                                               Color backColor,Color foreColor,Color lineColor){

        BufferedImage bim = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = bim.getGraphics();

        //画背景图
        g.setColor(backColor == null ? getRandomColor() : backColor);
        g.fillRect(0, 0, width, height);

        //画干扰线
        Random r = new Random();
        if(inerLine > 0){
            int x = 0,y = 0,x1 = width,y1 = 0;
            for(int i = 0;i < inerLine;i++) {
                g.setColor(lineColor == null ? getRandomColor() : lineColor);
                y = r.nextInt(height);
                y1 = r.nextInt(height);

                g.drawLine(x, y, x1, y1);
            }
        }

        //写验证码

//        g.setColor(getRandomColor());
//        g.setColor(isSimpleColor ? Color.BLACK : Color.WHITE);

        //字体大小为图片高度的80%
        int fsize = (int) (height * 0.8);
        int fx = height - fsize;
        int fy = fsize;

        g.setFont(new Font("Default", Font.PLAIN, fsize));

        //写验证码字符
        for (int i = 0; i < Vcode.length(); i++) {
            fy = randomLocation ? (int) ((Math.random() * 0.3 + 0.6) * height) :fy;
            g.setColor(foreColor == null ? getRandomColor() : foreColor);
            g.drawString(Vcode.charAt(i) + "", fx, fy);
            fx += fsize * 0.9;
        }

        g.dispose();

        return bim;
    }

    /**
     * 对上面方法的重载，内部生成Vcode
     *
     * @param type
     * @param length
     * @param exChars
     * @param width
     * @param heigth
     * @param inerLine
     * @param randomLocation
     * @param backColor
     * @param foreColor
     * @param lineColor
     * @return
     */

    public static BufferedImage creatImageVcode(int type, int length, String exChars, int width, int heigth, int inerLine,
                                                boolean randomLocation, Color backColor, Color foreColor, Color lineColor) {
        String Vcode = ValidateCode.creatValidateCode(type, length, exChars);
        BufferedImage bim = creatImageVcode(Vcode, width, heigth, inerLine, randomLocation, backColor, foreColor, lineColor);

        return bim;
    }

    /**
     * 产生随机颜色
     *
     * @return
     */
    private static Color getRandomColor() {
        Random r = new Random();
        Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        return c;
    }

//    public static void main(String[] args) {
//        BufferedImage bim = creatImageVcode("1234", 10, 10, 5, true, null, null, null);
//        System.out.print(bim.toString());
//    }

}
