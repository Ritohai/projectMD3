package ra.service;

import ra.config.InputMethod;
import ra.controller.CategoryController;
import ra.model.Category;
import ra.util.DataBase;

import java.util.ArrayList;
import java.util.List;

public class CategoryService implements IGenericService<Category, Integer> {
    private List<Category> categories;
    private Category category = new Category();
    private DataBase<Category> categoryData = new DataBase();

    public CategoryService() {
        List<Category> list = categoryData.readFromFile(DataBase.CATEGORY_PATH);
        if (list == null) {
            list = new ArrayList<>();
        }
        this.categories = list;
    }

    @Override
    public List<Category> findAll() {
        return categories;
    }

    @Override
    public void save(Category category) {
        this.categories = categoryData.readFromFile(DataBase.CATEGORY_PATH);
        if (findById(category.getIdCategory()) == null) {
            categories.add(category);
        } else {
            categories.set(categories.indexOf(findById(category.getIdCategory())), category);
        }
        categoryData.writeToFile(categories, DataBase.CATEGORY_PATH);
    }

    @Override
    public void delete(Integer id) {
        categories.remove(findById(id));
        categoryData.writeToFile(categories, DataBase.CATEGORY_PATH);
    }

    @Override
    public Category findById(Integer id) {
        for (Category cate : categories) {
            if (cate.getIdCategory() == id) {
                return cate;
            }
        }
        return null;
    }

    public int getIdCategory() {
        int max = 0;
        for (Category cate : categories) {
            if (cate.getIdCategory() > max) {
                max = cate.getIdCategory();
            }
        }
        return max + 1;
    }

    public Category inputData() {
        category.setIdCategory(idAutoIncrement());
        System.out.println("Nhập vào tên danh mục: ");
        while (true) {
            String categoryName = InputMethod.getString();
            boolean check = false;
            for (Category c : categories) {
                if (c.getNameCategory().equals(categoryName)) {
                    System.err.println("Đã tồn tại danh mục.");
                    check = true;
                    break;
                }
            }
            if (!check) {
                category.setNameCategory(categoryName);
                break;
            }
        }
        category.setStatus(true);
        return category;
    }

    private int idAutoIncrement() {
        this.categories = categoryData.readFromFile(DataBase.CATEGORY_PATH);
        int maxId = 0;
        for (Category category : this.categories) {
            if (category.getIdCategory() > maxId) {
                maxId = category.getIdCategory();
            }
        }
        return maxId + 1;
    }


}
