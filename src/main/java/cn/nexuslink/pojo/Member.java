package cn.nexuslink.pojo;

import cn.nexuslink.pojo.group.AddMember;
import cn.nexuslink.pojo.group.LoginMember;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by 罗浩 on 2017/4/24.
 */
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;


    private int id;
    private  String uuid;

    @NotNull(groups = {AddMember.class,LoginMember.class})
    @Min(1)
    @Max(15)
    private String realname;

    @NotNull(groups = LoginMember.class)
    private String loginname;

    @Min(6)
    @Max(15)
    private String password;

    @NotNull(groups = AddMember.class)
    @Min(6)
    @Max(20)
    private String department;

    private String signature;

    @Pattern(regexp="^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$",groups= AddMember.class)
    private String email;

    private int isnow;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSignature() {
        return signature;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsnow() {
        return isnow;
    }

    public void setIsnow(int isnow) {
        this.isnow = isnow;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", realname='" + realname + '\'' +
                ", loginname='" + loginname + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", signature='" + signature + '\'' +
                ", email='" + email + '\'' +
                ", isnow=" + isnow +
                '}';
    }
}
