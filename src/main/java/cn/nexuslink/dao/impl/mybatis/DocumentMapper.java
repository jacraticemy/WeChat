package cn.nexuslink.dao.impl.mybatis;

import cn.nexuslink.dao.IDocumentDao;
import cn.nexuslink.pojo.Document;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 由mybatis自动映射,继承自{@code IDocumentDao},使用时使用IDocumentDao
 * 当参数映射错误时，可以覆盖父类方法通过注解指定参数
 * Created by 罗浩 on 2017/4/20.
 */
@Repository("documentMapper")
public interface DocumentMapper extends IDocumentDao {

    @Override
    public Document getDocById(@Param("id") int id) ;

    @Override
    public boolean publishDoc( Document doc);

    @Override
    public List<Document> getDocList(@Param("index") int index, @Param("size") int size,@Param("sort_by") String sort_by,@Param("order") String order);

    @Override
    public boolean addDownloadCount(@Param("id") int id);

    @Override
    public boolean addLookCount(@Param("id") int id);

    @Override
    public int getDocNum();
}
