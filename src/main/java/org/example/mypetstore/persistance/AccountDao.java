package org.example.mypetstore.persistance;

import org.example.mypetstore.domain.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDao {

    public static Account getAccountByUsername(String username){
        Account  ac = null;
        try {
            Connection connection = DButil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                ac = new Account();
                ac.setUsername(resultSet.getString("username"));
                ac.setEmail(resultSet.getString("email"));
                ac.setFirstName(resultSet.getString("firstname"));
                ac.setLastName(resultSet.getString("lastname"));
                ac.setStatus(resultSet.getString("status"));
                ac.setAddress1(resultSet.getString("address1"));
                ac.setAddress2(resultSet.getString("address2"));
                ac.setCity(resultSet.getString("city"));
                ac.setState(resultSet.getString("state"));
                ac.setZip(resultSet.getString("zip"));
                ac.setCountry(resultSet.getString("country"));
                ac.setPhone(resultSet.getString("phone"));
                ac.setLanguagePreference(resultSet.getString("languagePreference"));
                ac.setFavouriteCategoryId(resultSet.getString("favouriteCategoryId"));
                ac.setListOption(resultSet.getInt("listOption") == 0);
                ac.setBannerOption(resultSet.getInt("bannerOption") == 0);
                ac.setBannerName(resultSet.getString("bannerName"));
            }
            DButil.closeResultSet(resultSet);
            DButil.closePreparedStatement(statement);
            DButil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ac;
    };

    public static Account getAccountByUsernameAndPassword(Account account){
        Account  ac = null;
        try {
            Connection connection = DButil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
             ResultSet resultSet = statement.executeQuery();
             if(resultSet.next()){
                 ac = new Account();
                 ac.setUsername(resultSet.getString("username"));
                 ac.setEmail(resultSet.getString("email"));
                 ac.setFirstName(resultSet.getString("firstname"));
                 ac.setLastName(resultSet.getString("lastname"));
                 ac.setStatus(resultSet.getString("status"));
                 ac.setAddress1(resultSet.getString("address1"));
                 ac.setAddress2(resultSet.getString("address2"));
                 ac.setCity(resultSet.getString("city"));
                 ac.setState(resultSet.getString("state"));
                 ac.setZip(resultSet.getString("zip"));
                 ac.setCountry(resultSet.getString("country"));
                 ac.setPhone(resultSet.getString("phone"));
                 ac.setLanguagePreference(resultSet.getString("languagePreference"));
                 ac.setFavouriteCategoryId(resultSet.getString("favouriteCategoryId"));
                 ac.setListOption(resultSet.getInt("listOption") == 0);
                 ac.setBannerOption(resultSet.getInt("bannerOption") == 0);
                 ac.setBannerName(resultSet.getString("bannerName"));
             }
            DButil.closeResultSet(resultSet);
            DButil.closePreparedStatement(statement);
            DButil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ac;
    };

    public static void insertAccount(Account account){
        try {
            Connection connection = DButil.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_ACCOUNT);
            statement.setString(1, account.getEmail());
            statement.setString(2, account.getFirstName());
            statement.setString(3, account.getLastName());
            statement.setString(4, account.getStatus());
            statement.setString(5, account.getAddress1());
            statement.setString(6, account.getAddress2());
            statement.setString(7, account.getCity());
            statement.setString(8, account.getState());
            statement.setString(9, account.getZip());
            statement.setString(10, account.getCountry());
            statement.setString(11, account.getPhone());
            statement.setString(12, account.getUsername());
            int rows = statement.executeUpdate();
            if(rows == 1){
                System.out.println("Account Insert succeed");
            }else {
                System.out.println("Account Insert fail");
            }
            DButil.closePreparedStatement(statement);
            DButil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    };

    public static void insertProfile(Account account){
        try {
            Connection connection = DButil.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_PROFILE);
            statement.setString(1, account.getLanguagePreference());
            statement.setString(2, account.getFavouriteCategoryId());
            statement.setString(3, account.getUsername());
            int rows = statement.executeUpdate();
            if(rows == 1){
                System.out.println("Profile Insert succeed");
            }else {
                System.out.println("Profile Insert fail");
            }
            DButil.closePreparedStatement(statement);
            DButil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    };

    public static void insertSignon(Account account){
        try {
            Connection connection = DButil.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_SIGN_ON);
            statement.setString(1, account.getPassword());
            statement.setString(2, account.getUsername());
            int rows = statement.executeUpdate();
            if(rows == 1){
                System.out.println("SignOn Insert succeed");
            }else {
                System.out.println("SignOn Insert fail");
            }
            DButil.closePreparedStatement(statement);
            DButil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    };

    public static void updateAccount(Account account){
        try {
            Connection connection = DButil.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT);
            statement.setString(1, account.getEmail());
            statement.setString(2, account.getFirstName());
            statement.setString(3, account.getLastName());
            statement.setString(4, account.getStatus());
            statement.setString(5, account.getAddress1());
            statement.setString(6, account.getAddress2());
            statement.setString(7, account.getCity());
            statement.setString(8, account.getState());
            statement.setString(9, account.getZip());
            statement.setString(10, account.getCountry());
            statement.setString(11, account.getPhone());
            statement.setString(12, account.getUsername());
            int rows = statement.executeUpdate();
            if(rows == 1){
                System.out.println("Account update succeed");
            }else {
                System.out.println("Account update fail");
            }
            DButil.closePreparedStatement(statement);
            DButil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    };

    public static void updateProfile(Account account){
        try {
            Connection connection = DButil.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_PROFILE);
            statement.setString(1, account.getLanguagePreference());
            statement.setString(2, account.getFavouriteCategoryId());
            statement.setString(3, account.getUsername());
            int rows = statement.executeUpdate();
            if(rows == 1){
                System.out.println("Profile update succeed");
            }else {
                System.out.println("Profile update fail");
            }
            DButil.closePreparedStatement(statement);
            DButil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    };

    public static void updateSignon(Account account){
        try {
            Connection connection = DButil.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SIGN_ON);
            statement.setString(1, account.getPassword());
            statement.setString(2, account.getUsername());
            int rows = statement.executeUpdate();
            if(rows == 1){
                System.out.println("SignOn update succeed");
            }else {
                System.out.println("SignOn update fail");
            }
            DButil.closePreparedStatement(statement);
            DButil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    };


    private static final String UPDATE_SIGN_ON = "UPDATE SIGNON SET PASSWORD = ?\n" +
            "    WHERE USERNAME = ?";

    private static final String UPDATE_PROFILE = "UPDATE PROFILE SET\n" +
            "      LANGPREF = ?,\n" +
            "      FAVCATEGORY = ?\n" +
            "    WHERE USERID = ?";

    private static final String UPDATE_ACCOUNT = "UPDATE ACCOUNT SET\n" +
            "      EMAIL = ?,\n" +
            "      FIRSTNAME = ?,\n" +
            "      LASTNAME = ?,\n" +
            "      STATUS = ?,\n" +
            "      ADDR1 = ?,\n" +
            "      ADDR2 = ?,\n" +
            "      CITY = ?,\n" +
            "      STATE = ?,\n" +
            "      ZIP = ?,\n" +
            "      COUNTRY = ?,\n" +
            "      PHONE = ?\n" +
            "    WHERE USERID = ?";

    private static final String INSERT_SIGN_ON = "INSERT INTO SIGNON (PASSWORD,USERNAME)\n" +
            "    VALUES (?, ?)";

    private static final String INSERT_PROFILE = "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID)\n" +
            "    VALUES (?, ?, ?)";

    private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD = "SELECT\n" +
            "      SIGNON.USERNAME,\n" +
            "      ACCOUNT.EMAIL,\n" +
            "      ACCOUNT.FIRSTNAME,\n" +
            "      ACCOUNT.LASTNAME,\n" +
            "      ACCOUNT.STATUS,\n" +
            "      ACCOUNT.ADDR1 AS address1,\n" +
            "      ACCOUNT.ADDR2 AS address2,\n" +
            "      ACCOUNT.CITY,\n" +
            "      ACCOUNT.STATE,\n" +
            "      ACCOUNT.ZIP,\n" +
            "      ACCOUNT.COUNTRY,\n" +
            "      ACCOUNT.PHONE,\n" +
            "      PROFILE.LANGPREF AS languagePreference,\n" +
            "      PROFILE.FAVCATEGORY AS favouriteCategoryId,\n" +
            "      PROFILE.MYLISTOPT AS listOption,\n" +
            "      PROFILE.BANNEROPT AS bannerOption,\n" +
            "      BANNERDATA.BANNERNAME\n" +
            "    FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA\n" +
            "    WHERE ACCOUNT.USERID = ?\n" +
            "      AND SIGNON.PASSWORD = ?\n" +
            "      AND SIGNON.USERNAME = ACCOUNT.USERID\n" +
            "      AND PROFILE.USERID = ACCOUNT.USERID\n" +
            "      AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String GET_ACCOUNT_BY_USERNAME = "SELECT\n" +
            "          SIGNON.USERNAME,\n" +
            "          ACCOUNT.EMAIL,\n" +
            "          ACCOUNT.FIRSTNAME,\n" +
            "          ACCOUNT.LASTNAME,\n" +
            "          ACCOUNT.STATUS,\n" +
            "          ACCOUNT.ADDR1 AS address1,\n" +
            "          ACCOUNT.ADDR2 AS address2,\n" +
            "          ACCOUNT.CITY,\n" +
            "          ACCOUNT.STATE,\n" +
            "          ACCOUNT.ZIP,\n" +
            "          ACCOUNT.COUNTRY,\n" +
            "          ACCOUNT.PHONE,\n" +
            "          PROFILE.LANGPREF AS languagePreference,\n" +
            "          PROFILE.FAVCATEGORY AS favouriteCategoryId,\n" +
            "          PROFILE.MYLISTOPT AS listOption,\n" +
            "          PROFILE.BANNEROPT AS bannerOption,\n" +
            "          BANNERDATA.BANNERNAME\n" +
            "    FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA\n" +
            "    WHERE ACCOUNT.USERID = ?\n" +
            "      AND SIGNON.USERNAME = ACCOUNT.USERID\n" +
            "      AND PROFILE.USERID = ACCOUNT.USERID\n" +
            "      AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String INSERT_ACCOUNT = "INSERT INTO ACCOUNT\n" +
            "      (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID)\n" +
            "    VALUES\n" +
            "      (?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?)";


}
