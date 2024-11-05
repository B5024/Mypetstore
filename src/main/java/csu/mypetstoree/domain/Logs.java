package csu.mypetstoree.domain;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

public class Logs implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private LocalDateTime loginDate;
    private String action;
    private String itemId ;

    public Logs() {

    }

    public Logs(String username, LocalDateTime logindate) {
        this.username = username;
        this.loginDate = logindate;
    }

    public Logs(String username,LocalDateTime dateTime,String action,String itemId){
        this.username = username;
        this.loginDate = dateTime;
        this.action = action;
        this.itemId = itemId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getLogindate() {
        return loginDate;
    }

    public void setLoginDate(LocalDateTime logindate) {
        this.loginDate = logindate;
    }

    public String getAction() { return action; }

    public void setAction(String action) { this.action = action; }

    public String getItemId() { return itemId; }

    public void setItemId(String itemId) { this.itemId = itemId; }
}
