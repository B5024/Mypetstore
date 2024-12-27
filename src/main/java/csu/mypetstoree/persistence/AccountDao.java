package csu.mypetstoree.persistence;

import csu.mypetstoree.domain.Account;

public interface AccountDao {
    Account getAccountByUsernameAndPassword(Account account);

    void insertAccount(Account account);

    void insertProfile(Account account);

    void insertSignon(Account account);

    void updateAccount(Account account);

    void updateProfile(Account account);

    void updateSignon(Account account);

    boolean isMailExist(String mail);
}

