package cn.nexuslink.service.impl;

import cn.nexuslink.dao.IDocumentDao;
import cn.nexuslink.pojo.Document;
import cn.nexuslink.pojo.Signment;
import cn.nexuslink.service.IDocumentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 罗浩 on 2017/4/25.
 */
@Service("documentService")
public class DocumentService implements IDocumentService {

    @Resource
    private IDocumentDao documentDao;

    @Override
    public boolean uploadDoc(Document doc) {
        return documentDao.publishDoc(doc);
    }

    @Override
    public Document findDoc(int id) {
        return documentDao.getDocById(id);
    }

    @Override
    public boolean addCount(int id, String column) {
        if(column.equals("lookCount")){
            documentDao.addLookCount(id);
        }else if(column.equals("downloadCount")){
            documentDao.addDownloadCount(id);
        }
        return false;
    }

    @Override
    public List<Document> getDocs(int pageCount, int size, String sort_by, String order) {
        int index=(pageCount-1)*size;
        return documentDao.getDocList(index,size,sort_by,order);
    }

    @Override
    public int getDocNum() {
        return documentDao.getDocNum();
    }
}
