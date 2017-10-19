package cn.nexuslink.dao;

import cn.nexuslink.pojo.Member;

import java.util.List;

/**
 * Created by 罗浩 on 2017/4/24.
 */
public interface IMemberDao {

    /**
     * 通过uuid得到成员信息，token时候用到
     * @param uuid 成员唯一指定的uuid
     * @return
     */
    public Member getMemberByUUid(String uuid);

    /**
     * 通过用户名（昵称或者真实名字）得到成员信息
     * @param name  用户名
     * @return
     */
    public int getMemberIdByName(String name);

    /**
     * 根据id找到member对象
     * @param id
     * @return
     */

    public Member getMemberById(int id);
    /**
     * 通过用户名得到用户对象
     * @param name
     * @return
     */
    public Member getMemberByName(String name);

    /**
     * 得到用户邮件
     * @param realname  用户真实名
     * @return
     */
    public String getEmailByRealname(String realname);


    /**
     * 添加新的成员，注册时
     * @param member
     * @return
     */
    public boolean addMember(Member member);

    /**
     * 更新成员信息
     * @param member
     * @return
     */
    public int updateMember(Member member);

    /**
     * 得到成员集合（在研成员）
     * @param isnow  目前是否在研究中心
     * @return
     */
    public List<Member> getMembers(int isnow);

}
