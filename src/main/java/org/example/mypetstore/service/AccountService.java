package org.example.mypetstore.service;

import org.example.mypetstore.domain.Account;
import org.example.mypetstore.persistance.AccountDao;

public class AccountService {

    public static Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return AccountDao.getAccountByUsernameAndPassword(account);
    }
}
