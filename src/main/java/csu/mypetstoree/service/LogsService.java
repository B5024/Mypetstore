package csu.mypetstoree.service;

import csu.mypetstoree.domain.Logs;
import csu.mypetstoree.persistence.LogsDao;
import csu.mypetstoree.persistence.impl.LogsDaoImpl;

import java.sql.Date;
import java.time.LocalDateTime;

public class LogsService {

    public static void insertLoginLogs(String username) {
        LocalDateTime currentDate = LocalDateTime.now();

        Logs loginLog = new Logs(username,currentDate);

        LogsDao logsDao = new LogsDaoImpl();
        logsDao.insertLoginLogs(loginLog);
    }

    public static void insertCartLogs(String username,String action,String itemId) {
        LocalDateTime currentDate = LocalDateTime.now();

        Logs cartLog = new Logs(username,currentDate,action,itemId);

        LogsDao logsDao = new LogsDaoImpl();
        logsDao.insertCartLogs(cartLog);
    }

    public static void main(String[] args) {
        LogsDao logsDao = new LogsDaoImpl();

//        insertLoginLogs("j2ee");
//        insertCartLogs("j2ee","add","EST-1");
        logsDao.getLoginLogs("j2ee");
        logsDao.getCartLogs("j2ee");
    }
}
