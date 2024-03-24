package cn.tangtj.clouddisk.entity.vo;

import java.util.Date;

public class Guest {
    private Date date;
    private int DownloadTime;
    private String guestCode;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDownloadTime() {
        return DownloadTime;
    }

    public void setDownloadTime(int downloadTime) {
        DownloadTime = downloadTime;
    }

    public String getGuestCode() {
        return guestCode;
    }

    public void setGuestCode(String guestCode) {
        this.guestCode = guestCode;
    }
}
