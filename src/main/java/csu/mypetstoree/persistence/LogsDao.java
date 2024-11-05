package csu.mypetstoree.persistence;

import csu.mypetstoree.domain.Logs;

public interface LogsDao {

    void insertLoginLogs(Logs loginLogs);

    void insertCartLogs(Logs cartLogs);

    void getLoginLogs(String username);

    void getCartLogs(String username);

}
