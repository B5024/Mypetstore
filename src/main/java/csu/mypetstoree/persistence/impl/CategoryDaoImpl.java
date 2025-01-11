package csu.mypetstoree.persistence.impl;

import csu.mypetstoree.domain.Category;
import csu.mypetstoree.persistence.CategoryDao;
import csu.mypetstoree.persistence.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private static final String GET_CATEGORY_LIST =
            "SELECT CATID AS categoryId, NAME AS name,DESCN AS description FROM CATEGORY";

    private static final String GET_CATEGORY =
            "SELECT CATID AS categoryId, NAME AS name,DESCN AS description FROM CATEGORY WHERE CATID = ?";

    @Override
    public List<Category> getCategoryList(){
        List<Category> categoryList = new ArrayList<>();

        try {
            Connection conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_CATEGORY_LIST);
            while(rs.next()){
                Category category = new Category();
                category.setCategoryId(rs.getString("categoryId"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                categoryList.add(category);
            }
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categoryList;
    }
    @Override
    public Category getCategory(String categoryId) {
        Category category = new Category();
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_CATEGORY);
            ps.setString(1, categoryId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                category.setCategoryId(rs.getString("categoryId"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
            }
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return category;
    }
}
