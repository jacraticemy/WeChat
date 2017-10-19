package cn.nexuslink.dao;

import cn.nexuslink.pojo.Signment;

import java.util.List;

/**
 * Created by 罗浩 on 2017/4/24.
 */
public interface ISignmentDao {

    /**
     * 通过id找到签到信息
     * @param id
     * @return
     */
    public Signment getSignInfoById(int id);

    /**
     * 得到在研的所有成员敲到表
     * @return
     */
    public List<Signment> getSignments();

    /**
     * 通过用户真实名查找其签到信息
     * @param realname
     * @return
     */
    public Signment getSignInfoByName(String realname);

}
