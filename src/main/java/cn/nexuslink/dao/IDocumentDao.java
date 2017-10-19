package cn.nexuslink.dao;

import cn.nexuslink.pojo.Document;

import java.util.List;

/**
 * Created by 罗浩 on 2017/4/11.
 */

public interface IDocumentDao {

    /**
     * 根据id获得会议记录文档
     * @param id 会议记录id
     * @return
     */
    public Document getDocById(int id) ;

    /**
     * 添加会议记录文档
     * @param doc 会议记录model
     * @return
     */
    public boolean publishDoc(Document doc);

    /**
     * 实现会议记录列表分页
     * @param index 索引下标=(pageCount-1)*size
     * @param size 每页文档数量
     * @param sort_by 排序时采用的字段
     * @param order 排序方式-顺序或者倒序
     * @return
     */
    public List<Document> getDocList(int index,int size,String sort_by,String order);

    /**
     * 得到会议文档总数目
     * @return
     */
    public int getDocNum();

    /**
     * 添加会议记录浏览数
     * @param id 会议记录文档的id
     * @return
     */
    public boolean addLookCount(int id);


    /**
     * 添加会议记录文档的下载次数
     * @param id 会议记录文档的id
     * @return
     */
    public boolean addDownloadCount(int id);

}
