package cn.nexuslink.service;


import cn.nexuslink.pojo.Document;

import java.util.List;

/**
 * Created by 罗浩 on 2017/4/11.
 */
public interface IDocumentService {

    public boolean uploadDoc(Document doc);

    public Document findDoc(int id);

    public boolean addCount(int id,String column);

    public List<Document> getDocs(int pageCount,int size,String sort_by,String order);

    public int  getDocNum();

}
