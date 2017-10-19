package cn.nexuslink.dao.impl.mybatis;

import cn.nexuslink.dao.ISignmentDao;
import cn.nexuslink.pojo.Signment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 罗浩 on 2017/4/24.
 */
public interface SignmentMapper extends ISignmentDao {

    @Override
    List<Signment> getSignments();

    @Override
    Signment getSignInfoById(int id);

    @Override
    Signment getSignInfoByName(@Param("realname") String realname);

}


