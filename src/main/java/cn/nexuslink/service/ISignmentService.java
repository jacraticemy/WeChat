package cn.nexuslink.service;

import cn.nexuslink.pojo.Signment;

import java.util.List;

/**
 * Created by 罗浩 on 2017/4/25.
 */
public interface ISignmentService {

    public List<Signment> getSignmentsWithMember();

    public Signment getSignInfoById(int id);

    public Signment getSignInfoByName(String name);
}
