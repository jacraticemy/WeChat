package cn.nexuslink.utils.validateCode;

import java.util.Random;

/**验证码生成器，可生成数字、大写、小写、字母及三者混合类型的验证码。
 * 支持自定义验证码字符数量，支持自定义验证码图片的大小，支持自定义需排除的特殊字符
 * 支持自定义干扰线的数量，支持自定义验证码图文颜色
 * Created by 罗浩 on 2017/3/21.
 */
public class ValidateCode {
    /**
     * 验证码类型只有数字
     */
    public static final int TYPE_NUM_ONLY = 0;

    /**
     * 验证码类型只为大小写字母混合
     */
    public static final int TYPE_LETTER_ONLY = 1;

    /**
     * 验证码类型为数字、大小写字母混合
     */
    public static final int TYPE_ALL_MIXED = 2 ;

    /**
     * 验证码类型为数字、大写字母混合
     */
    public static final int TYOE_NUM_UPPER = 3 ;

    /**
     * 验证码类型为数字、小写字母混合
     */
    public static final int TYPE_NUM_LOWER= 4 ;

    /**
     * 验证码类型为只有大写字母
     */
    public static final int TYPE_UPPER_ONLY= 5 ;

    /**
     * 验证码类型只有小写字母
     */
    public static final int TYPE_LOWER_ONLY = 6 ;

    private ValidateCode(){}

    /**
     * 生成验证码字符串
     *
     * @param type 验证码类型，见本类的静态属性
     * @param length 验证码的长度，大于0的整数
     * @param exChars 需排除的特殊字符串（仅对数字、字母混合型验证码有效，无需排除则为null）
     * @return 验证码字符串
     */
    public static String creatValidateCode(int type,int length,String exChars){
        if(length<=0)
            return "";

        StringBuffer code = new StringBuffer();
        int i = 0;
        Random r = new Random();

        switch (type){

            case TYPE_NUM_ONLY:
                while(i<length){
                    int t = r.nextInt(10);
                    if (exChars == null || exChars.indexOf(t + "") < 0) {
                        code.append(t);
                        i++;
                    }
                }
                break;

            case TYPE_LETTER_ONLY:
                while(i<length){
                    int t = r.nextInt(123);
                    if((t >= 97 || ( t >= 65 && t <= 90))
                            && (exChars == null || exChars.indexOf((char) t) < 0)) {
                        code.append((char) t);
                        i++;
                    }
                }
                break;

            case TYPE_ALL_MIXED:
                while (i < length) {
                    int t = r.nextInt(123);
                    if ((t >= 97 || (t >= 65 && t <= 90) || (t >= 48 && t <= 57))
                            && (exChars == null || exChars.indexOf((char) t) < 0)) {
                        code.append((char) t);
                        i++;
                    }

                }
                break;

            case TYOE_NUM_UPPER:
                while (i < length) {
                    int t = r.nextInt(91);
                    if ((t >= 65 || (t >= 48 && t <= 57))
                            && (exChars == null || exChars.indexOf((char) t) < 0)) {
                        code.append((char) t);
                        i++;
                    }
                }
                break;

            case TYPE_UPPER_ONLY:
                while (i < length) {
                    int t = r.nextInt(91);
                    if( (t >= 65) && (exChars == null || exChars.indexOf((char) t) < 0)){
                        code.append((char) t);
                        i++;
                    }
                }
                break;

            case TYPE_LOWER_ONLY:
                while (i < length) {
                    int t = r.nextInt(123);
                    if( (t >= 97) && (exChars == null || exChars.indexOf((char) t) < 0)){
                        code.append((char) t);
                        i++;
                    }
                }
                break;
        }
        return code.toString();
    }

    //测试所有均可成功
//    public static void main(String[] args) {
//        String Vcode = "123";
//        Vcode = creatValidateCode(TYPE_ALL_MIXED, 10, null);
//        System.out.print("Vcode==" + Vcode);
//    }

}
