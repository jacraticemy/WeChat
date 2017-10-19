package cn.nexuslink.pojo;

import java.util.List;

/**
 * Created by 罗浩 on 2017/4/27.
 */
public class SignMembers {
    private List<Member> memberList;
    private List<Signment> signmentList;

    public SignMembers(List<Member> members, List<Signment> signments) {
        this.memberList=members;
        this.signmentList=signments;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public List<Signment> getSignmentList() {
        return signmentList;
    }

    public void setSignmentList(List<Signment> signmentList) {
        this.signmentList = signmentList;
    }
}
