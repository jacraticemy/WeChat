package cn.nexuslink.service.impl;

import cn.nexuslink.dao.ISignmentDao;
import cn.nexuslink.pojo.Signment;
import cn.nexuslink.service.ISignmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 罗浩 on 2017/4/25.
 */
@Service("signmentService")
public class SignmentService implements ISignmentService {

    @Resource
    private ISignmentDao signmentDao;

    @Override
    public List<Signment> getSignmentsWithMember() {
        return null;
    }

    @Override
    public Signment getSignInfoById(int id) {
        return signmentDao.getSignInfoById(id);
    }

    @Override
    public Signment getSignInfoByName(String name) {
        return signmentDao.getSignInfoByName(name);
    }

    public List<Signment> getSignments() {
        return signmentDao.getSignments();
    }
}
