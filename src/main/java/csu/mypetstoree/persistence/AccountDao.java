package csu.mypetstoree.persistence;

import csu.mypetstoree.domain.Account;

public interface AccountDao {
    static Account getAccountByUsernameAndPassword(Account account) {
        return account;
    }
}

