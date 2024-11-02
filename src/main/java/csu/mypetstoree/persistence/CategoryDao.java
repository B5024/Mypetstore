package csu.mypetstoree.persistence;

import csu.mypetstoree.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
    List<Category> getCategoryList() throws SQLException;

    Category getCategory(String categoryId);

}
