package mypetstore.persistence;

import mypetstore.domain.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getCategoryList();

    Category getCategory(String categoryId);
}
