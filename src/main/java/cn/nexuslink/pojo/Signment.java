package cn.nexuslink.pojo;

import java.io.Serializable;

/**
 * Created by 罗浩 on 2017/4/24.
 */
public class Signment implements Serializable {
    private static final long serialVersionUID = 1L;

    private int memberId;
    private int totaltime;
    private int frequency;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(int tataltime) {
        this.totaltime = tataltime;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
