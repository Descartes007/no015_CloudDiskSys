package cn.tangtj.clouddisk.entity.vo;

public class SignUpNameCheckResult {
    private boolean usable = false;
    private String msg = "";

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
