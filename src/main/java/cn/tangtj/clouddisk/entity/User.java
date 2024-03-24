package cn.tangtj.clouddisk.entity;

/**
 * @author tang
 */
public class User {

    private Integer id;
    private String username;
    private String password;
    private Long fileMaxSize = 50L * 1024 * 1024;
    private Integer fileMaxCount = 50;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getFileMaxSize() {
        return fileMaxSize;
    }

    public void setFileMaxSize(Long fileMaxSize) {
        this.fileMaxSize = fileMaxSize;
    }

    public Integer getFileMaxCount() {
        return fileMaxCount;
    }

    public void setFileMaxCount(Integer fileMaxCount) {
        this.fileMaxCount = fileMaxCount;
    }
}
