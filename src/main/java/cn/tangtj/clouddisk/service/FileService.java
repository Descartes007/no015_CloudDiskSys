package cn.tangtj.clouddisk.service;

import cn.tangtj.clouddisk.dao.FileDao;
import cn.tangtj.clouddisk.entity.UploadFile;
import cn.tangtj.clouddisk.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;

/**
 * @author tang
 */
@Service
public class FileService {

    private final FileDao fileDao;

    @Autowired
    public FileService(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    public List<UploadFile> findByUserId(int id){
        return fileDao.findByUserId(id);
    }

    public UploadFile findById(int id){
        return fileDao.findById(id);
    }

    public UploadFile findByShareCode(String shareCode){
        return fileDao.findByShareCode(shareCode);
    }

    public UploadFile save(UploadFile file){
        return fileDao.save(file);
    }

    public String shareFileById(int fileId) {
        int codeLength = 7;
        String shareCode;
        for (int i = 0;i < 10;i++){
            shareCode = StringUtil.createRandStr(codeLength);
            if (fileDao.shareFileByFileId(fileId,shareCode)){
                return  shareCode;
            }
        }
        return null;
    }

    public void unshareFile(int fileId) {
        fileDao.unshareFileByFileId(fileId);
    }

    public void deleteById(int id){
        fileDao.deleteById(id);
    }

}
