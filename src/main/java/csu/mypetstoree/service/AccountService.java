package csu.mypetstoree.service;

import csu.mypetstoree.domain.Account;
import csu.mypetstoree.persistence.AccountDao;
import csu.mypetstoree.persistence.impl.AccountDaoImpl;

public class AccountService {

    public static Account getAccountByUsername(String username) {
        AccountDao accountDao = new AccountDaoImpl();
        return accountDao.getAccountByUsername(username);
    }

    public static Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        AccountDao accountDao = new AccountDaoImpl();
        return accountDao.getAccountByUsernameAndPassword(account);
    }

    public static void addAccount(Account account) {
        AccountDao accountDao = new AccountDaoImpl();
        accountDao.insertAccount(account);
        accountDao.insertSignon(account);
        accountDao.insertProfile(account);
    }

    public static void updateAccount(Account account) {
        AccountDao accountDao = new AccountDaoImpl();
        accountDao.updateAccount(account);
        accountDao.updateSignon(account);
        accountDao.updateProfile(account);
    }

    public static void updateSignOn(Account account) {
        AccountDao accountDao = new AccountDaoImpl();
        accountDao.updateSignon(account);
    }

    public static boolean isMailExist(String mail) {
        AccountDao accountDao = new AccountDaoImpl();
        return accountDao.isMailExist(mail);
    }
}
