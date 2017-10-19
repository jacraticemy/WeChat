package cn.nexuslink.service.impl;

import cn.nexuslink.dao.IMemberDao;
import cn.nexuslink.pojo.Member;
import cn.nexuslink.service.IMemberService;
import cn.nexuslink.utils.HashPasswordUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import static cn.nexuslink.utils.UUidCreatUtil.creatUUid;

/**
 * Created by 罗浩 on 2017/4/25.
 */
@Service("memberService")
public class MemberService implements IMemberService {

    @Resource
    private IMemberDao memberDao;

    @Override
    public Member addMember(Member member) {
        String uuid= creatUUid().toString();
        member.setUuid(uuid);
        if (memberDao.addMember(member))
            return memberDao.getMemberByName(member.getRealname());
        return null;
    }

    @Override
    public boolean updateMember(Member member) {
        int n = memberDao.updateMember(member);
        System.out.println("更新记录：" + n);
         if(0 < memberDao.updateMember(member)) {
             return true;
         }
        return false;
    }

    @Override
    public Member ensureMember(String name, String password) {
        Member member = memberDao.getMemberByName(name);
        if(member==null)
            return null;
        String correctPasswordHash = member.getPassword();
        try {
            if(HashPasswordUtil.validatePassword(password,correctPasswordHash))
                member.setPassword(null);
            else
                member = null;
        } catch (NoSuchAlgorithmException e) {
            member=null;
        } catch (InvalidKeySpecException e) {
            member=null;
        }
        return member;
    }

    @Override
    public List<Member> getMembers(int isnow) {
        return memberDao.getMembers(isnow);
    }
}
