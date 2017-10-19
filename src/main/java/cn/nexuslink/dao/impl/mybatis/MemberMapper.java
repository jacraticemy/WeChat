package cn.nexuslink.dao.impl.mybatis;

import cn.nexuslink.dao.IMemberDao;
import cn.nexuslink.pojo.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 罗浩 on 2017/4/24.
 */
@Repository("memberMapper")
public interface MemberMapper extends IMemberDao {

    @Override
    public boolean addMember( Member member);

    @Override
    public int updateMember( Member member);

    @Override
    public int getMemberIdByName(@Param("name") String name);

    @Override
    public Member getMemberById(@Param("id") int id);

    @Override
    String getEmailByRealname(@Param("realname") String realname);

    @Override
    public List<Member> getMembers(@Param("isnow") int isnow);

    @Override
    public Member getMemberByUUid(@Param("uuid") String uuid);

    @Override
    public Member getMemberByName(@Param("name") String name);
}
