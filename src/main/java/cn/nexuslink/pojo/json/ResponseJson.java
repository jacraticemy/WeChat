package cn.nexuslink.pojo.json;

/**
 * Created by 罗浩 on 2017/4/25.
 */
public class ResponseJson {

    private int code;
    private String message;
    private Object obj;

    public ResponseJson(int code, String message, Object obj){
        this.code=code;
        this.message=message;
        this.obj=obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
