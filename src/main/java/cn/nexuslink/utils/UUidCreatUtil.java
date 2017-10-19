package cn.nexuslink.utils;

import java.util.UUID;

/**
 * Created by 罗浩 on 2017/4/25.
 * description 在用户注册时产生唯一识别的uuid，也被用作token
 */
public class UUidCreatUtil {

    public static String creatUUid(){
        return String.valueOf(UUID.randomUUID());
    }

}
