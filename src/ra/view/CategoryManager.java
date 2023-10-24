package ra.view;

import ra.config.Constans;
import ra.config.InputMethod;
import ra.controller.CategoryController;
import ra.model.Category;
import ra.service.CategoryService;

import java.util.List;

public class CategoryManager {
    private CategoryController categoryController;

    public CategoryManager(CategoryController categoryController) {
        this.categoryController = categoryController;
        while (true) {
            Navbar.menuCategory();
            int choice = InputMethod.getInteger();
            switch (choice) {
                case 1:
                    // Show Category
                    showCategory();
                    break;
                case 2:
                    // Thêm mới
                    addCategory();
                    break;
//                case 3:
//                    deleteCategory();
//                    break;
                case 3:
                    // Thay đổi trạng thái
                    changeStatus();
                    break;
                case 4:
                    Navbar.menuAdmin();
                    break;
                default:
                    System.out.println("Nhập lựa chọn của bạn từ 1 => 4");
            }
        }
    }

    public void showCategory() {
        if(categoryController.findAll().isEmpty()){
            System.err.println("Danh mục rỗng.");
        } else {
            for (Category category : categoryController.findAll()) {

                    System.out.println("\033[0;34m--------------------------------");
                    System.out.println("\033[0;35m"+category);
                    System.out.println("\033[0;34m--------------------------------\33[0m");

            }}
    }

    public void addCategory() {
        CategoryService categoryService = new CategoryService();
        System.out.println("Nhập số lượng muốn thêm: ");
        int number = InputMethod.getInteger();
        for (int i = 0; i < number; i++) {
            System.out.println("Sản phẩm thứ: " + (i + 1));
            categoryController.save(categoryService.inputData());
        }
        System.out.println(Constans.ADD_TRUE);


    }

    public void deleteCategory() {
        System.out.println("Nhập Id muốn xóa: ");
        int id = InputMethod.getInteger();
        if (categoryController.findById(id) == null) {
            System.err.println(Constans.DELETE_FALSE);
            return;
        }
        categoryController.delete(id);
        System.out.println(Constans.DELETE_TRUE);
    }

    public void changeStatus() {
        System.out.println("Nhập Id muốn thay đổi: ");
        int id = InputMethod.getInteger();
        Category category = categoryController.findById(id);
        if (category == null) {
            System.err.println(Constans.NOT_FOUND);
        } else {
            category.setStatus(!category.isStatus());
            categoryController.save(category);
            System.out.println("Thay đổi thành công.");
        }
    }
}

