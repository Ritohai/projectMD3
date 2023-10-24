package ra.view;

import ra.config.Constans;
import ra.config.InputMethod;
import ra.controller.CategoryController;
import ra.controller.ProductController;
import ra.model.Category;
import ra.model.Product;
import ra.service.ProductService;


public class ProductManager {
    private ProductController productController;


    public ProductManager(ProductController productController) {
        this.productController = productController;
        while (true) {
            Navbar.menuProduct();
            int choice = InputMethod.getInteger();
            switch (choice) {
                case 1:
                    // Hển thị ra toan bộ product
                    showProduct();
                    break;
                case 2:
                    // Thêm product
                    addProduct();
                    break;
                case 3:
                    // Xóa product
                    deleteProduct();
                    break;
                case 4:
                    editProduct();
                    break;
                case 5:
                    changeStatus();
                    break;
                case 6:
                    searchProduct();
                    break;
                case 7:
                    Navbar.menuAdmin();
                    break;
                default:
                    System.out.println("Nhập lựa chọn từ 1 đến 6");
            }
        }
    }

    private void searchProduct() {
        System.out.println("Nhập tên sản phẩm tìm kiếm: ");
        String nameP = InputMethod.getString();
        boolean check = false;
        for (Product p : productController.findAll()) {
            if(p.getNameProduct().contains(nameP)) {
                System.out.println(p);
                check = true;
            }
        }
        if(!check) {
            System.err.println("Không thấy sản phẩm.");
        }
    }

    public void showProduct() {
        if (productController.findAll().isEmpty()) {
            System.err.println("Sản phẩm rỗng");
        }
        else {
            for (Product product : productController.findAll()) {
                System.out.println("\033[0;34m--------------------------------");
                System.out.println("\033[0;35m"+product);
                System.out.println("\033[0;34m--------------------------------\33[0m");
            }
        }
    }

    public void addProduct() {
        ProductService productService = new ProductService();
        productController.save(productService.inputData());
        System.out.println(Constans.ADD_TRUE);
    }

    public void deleteProduct() {
        System.out.println("Nhập id muốn xóa: ");
        int idDelete = InputMethod.getInteger();
        if (productController.findById(idDelete) == null) {
            System.err.println(Constans.DELETE_FALSE);
            return;
        }
        productController.delete(idDelete);
        System.out.println(Constans.DELETE_TRUE);
    }

    public void editProduct() {
        Product product = new Product();
        System.out.println("Nhập Id cần sửa: ");
        int id = InputMethod.getInteger();
        boolean flag = false;
        for (Product p : productController.findAll()) {
             if (id == p.getId()) {
                System.out.println("Id: " + id);
                System.out.println("Danh sách các danh mục: ");
                CategoryController categoryController = new CategoryController();
                for (Category category : categoryController.findAll()) {
                    if (category.isStatus()) {
                        System.out.println(category.getIdCategory() + " : " + category.getNameCategory());
                    }
                }
                System.out.println("Nhập id danh mục: ");
                while (true) {
                    int categoryId = InputMethod.getInteger();
                    boolean check = false;
                    for (Category category : categoryController.findAll()) {
                        if (category.getIdCategory() == categoryId) {
                            check = true;
                            break;
                        }
                    }
                    if (!check) {
                        System.err.println(Constans.NOT_FOUND);
                    } else {
                        product.setCategory(categoryController.findById(categoryId));

                        break;
                    }
                }
                product.setId(id);
                System.out.println("Nhập tên sản phẩm: ");
                product.setNameProduct(InputMethod.getString());
                System.out.println("Nhập giá nhập vào: ");
                product.setImportPrice(InputMethod.getDouble());
                System.out.println("Nhập miêu tả: ");
                product.setDescription(InputMethod.getString());
                System.out.println("Nhập hãng: ");
                product.setManufacturer(InputMethod.getString());
                System.out.println("Nhập số lượng: ");
                product.setStock(InputMethod.getInteger());
                product.setStatus(true);
                productController.save(product);
                flag = true;
            }
        }
        if(!flag){
            System.err.println("Không thấy id.");
        } else {
            System.out.println("Sửa thành công.");
        }
    }

    public void changeStatus() {
        System.out.println("Nhập Id muốn thay đổi trạng thái: ");
        int idNumber = InputMethod.getInteger();
        Product product = productController.findById(idNumber);
        if (product == null) {
            System.err.println(Constans.NOT_FOUND);
        } else {
            product.setStatus(!product.isStatus());
            productController.save(product);
            System.out.println("Thay đổi trạng thái thành công.");
        }
    }
}


