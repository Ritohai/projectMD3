package ra.service;

import ra.config.Constans;
import ra.config.InputMethod;
import ra.controller.CategoryController;
import ra.controller.ProductController;
import ra.model.Category;
import ra.model.Product;
import ra.model.User;
import ra.util.DataBase;
import ra.view.Navbar;

import java.util.ArrayList;
import java.util.List;


public class ProductService implements IGenericService<Product, Integer> {
    private List<Product> products;
    private Product product = new Product();
    private DataBase<Product> productDataBase = new DataBase();

    public ProductService() {
        List<Product> list = productDataBase.readFromFile(DataBase.PRODUCT_PATH);
        if (list == null) {
            list = new ArrayList<>();
        }
        this.products = list;
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        if (findById(product.getId()) == null) {
            products.add(product);
        } else {
            products.set(products.indexOf(findById(product.getId())), product);
        }
        productDataBase.writeToFile(products, DataBase.PRODUCT_PATH);
    }

    @Override
    public void delete(Integer id) {
        products.remove(findById(id));
        productDataBase.writeToFile(products, DataBase.PRODUCT_PATH);
    }

    @Override
    public Product findById(Integer id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public int getNewId() {
        int max = 0;
        for (Product p : products) {
            if (p.getId() > max) {
                max = p.getId();
            }
        }
        return max + 1;
    }

    public Product inputData() {
        CategoryController categoryController = new CategoryController();
        Product product = new Product();
        product.setId(getNewId());
        System.out.println("Danh sách danh mục: ");
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
                if (category.isStatus()) {
                    if (category.getIdCategory() == categoryId) {
                        check = true;
                        break;
                    }
                }
            }
            if (!check) {
                System.out.println(Constans.NOT_FOUND);
            } else {
                product.setCategory(categoryController.findById(categoryId));
                break;
            }
        }
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
        return product;
    }

    //showUserProduct
    public void showProductUser() {
        System.out.println();
        for (Product product : findAll()) {
            if (product.isStatus()) {
                System.out.println("\033[0;34m--------------------------------");
                System.out.println("\033[0;35m" + product);
                System.out.println("\033[0;34m--------------------------------\33[0m");
            }
        }
    }

    public static void displayListProduct() {
        ProductController productController = new ProductController();
        if (productController.findAll().isEmpty()) {
            System.err.println("Không có sản phẩm");
            return;
        }
        for (Product p : productController.findAll()) {
            if (p.isStatus() && p.getStock() > 0) {
                System.out.println("\033[0:34m--------------------------------");
                System.out.println("\033[0;35m" + p);
                System.out.println("\033[0:34m--------------------------------\33[0m");

            }
        }
    }
}
