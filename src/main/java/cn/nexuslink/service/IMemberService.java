package cn.nexuslink.service;

import cn.nexuslink.pojo.Member;

import java.util.List;

/**
 * Created by 罗浩 on 2017/4/25.
 */
public interface IMemberService {

    public Member addMember(Member member);

    public boolean updateMember(Member member);

    public Member ensureMember(String name,String password);

    public List<Member> getMembers(int isnow);
}
