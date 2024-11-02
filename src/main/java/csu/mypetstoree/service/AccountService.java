package csu.mypetstoree.service;

import csu.mypetstoree.domain.Account;
import csu.mypetstoree.persistence.AccountDao;

public class AccountService {

    public static Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return AccountDao.getAccountByUsernameAndPassword(account);
    }
}
