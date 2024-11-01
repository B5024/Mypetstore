import org.example.mypetstore.domain.Account;
import org.example.mypetstore.persistance.AccountDao;

import java.sql.*;

public class JDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Account account = AccountDao.getAccountByUsername("ACID");
        System.out.println(account);
    }
}
