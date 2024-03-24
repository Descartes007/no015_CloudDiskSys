package cn.tangtj.clouddisk.entity.vo;

import cn.tangtj.clouddisk.entity.UploadFile;

import java.util.List;

/**
 * @author tang
 */
public class Files {

    private List<UploadFile> uploadFiles;
    private long filesSize;
    private int filesCount;

    public Files(List<UploadFile> list){
        this.uploadFiles = list;
        if (list != null){
            this.filesCount = list.size();
            this.filesSize = list.stream().mapToLong(UploadFile::getFileSize).sum();
        }
    }

    public List<UploadFile> getUploadFiles() {
        return uploadFiles;
    }

    public void setUploadFiles(List<UploadFile> uploadFiles) {
        this.uploadFiles = uploadFiles;
    }

    public long getFilesSize() {
        return filesSize;
    }

    public void setFilesSize(Long filesSize) {
        this.filesSize = filesSize;
    }

    public int getFilesCount() {
        return filesCount;
    }

    public void setFilesCount(int filesCount) {
        this.filesCount = filesCount;
    }
}
