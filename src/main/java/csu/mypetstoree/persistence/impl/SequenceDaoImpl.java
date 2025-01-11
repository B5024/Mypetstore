package csu.mypetstoree.persistence.impl;

import csu.mypetstoree.domain.Sequence;
import csu.mypetstoree.persistence.DBUtil;
import csu.mypetstoree.persistence.SequenceDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceDaoImpl implements SequenceDao {

    public Sequence getSequence(Sequence sequence){
        Sequence seq = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_SEQUENCE);
            statement.setString(1, sequence.getName());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                seq = new Sequence();
                seq.setName(resultSet.getString("name"));
                seq.setNextId(resultSet.getInt("nextId"));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return seq;
    };

    public void updateSequence(Sequence sequence){
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SEQUENCE);
            statement.setInt(1, sequence.getNextId());
            statement.setString(2, sequence.getName());
            int rows = statement.executeUpdate();
            if(rows == 1){
                System.out.println("Sequence update succeed");
            }else {
                System.out.println("Sequence update fail");
            }
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    };

    private final String GET_SEQUENCE = "SELECT name, nextid\n" +
            "    FROM SEQUENCE\n" +
            "    WHERE NAME = ?";

    private final String UPDATE_SEQUENCE = "UPDATE SEQUENCE\n" +
            "    SET NEXTID = ?\n" +
            "    WHERE NAME = ?";

}
