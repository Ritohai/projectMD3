package ra.view;

import ra.config.Constans;
import ra.config.InputMethod;
import ra.controller.ProductController;
import ra.controller.UserController;
import ra.model.Product;
import ra.model.Roll;
import ra.model.User;
import ra.service.ProductService;
import ra.util.DataBase;

import java.util.List;

public class UserManager {
    private UserController userController;
    private ProductController productController = new ProductController();
    private static Product product;
    public static List<Product> products;
    private static ProductService productService = new ProductService();

    public UserManager(UserController userController) {
        this.userController = userController;
        while (true) {
            Navbar.menuAccountUser();
            int choice = InputMethod.getInteger();
            switch (choice) {
                case 1:
                    // Show Account
                    showAccount();
                    break;
                case 2:
                    changeStatus();
                    break;
                case 3:
                    searchUser();
                    break;
                case 4:
                    Navbar.menuAdmin();
                    break;
                default:
                    System.out.println("Nhập lựa chọn của bạn từ 1 => 4");
            }
        }
    }

    private void searchUser() {
        System.out.println("Nhập tên muốn tìm kiếm: ");
        String nameS = InputMethod.getString();
        boolean check = false;
        for (User u: userController.findAll()) {
            if(u.getName().contains(nameS)) {
                System.out.println(u);
                check = true;
            }
        }
        if(!check) {
            System.err.println("Không thấy người dùng.");
        }
    }

    public void showAccount() {
        for (User user : userController.findAll()) {
            System.out.println("-----------------------------------------");
            System.out.println(user);
        }
    }

    public void changeStatus() {
        // Lấy userlogin để check quyển khóa tk
        System.out.println("Nhập ID tài khoản: ");
        int id = InputMethod.getInteger();
        User user = userController.findById(id);
        if (user == null) {
            System.out.println(Constans.NOT_FOUND);
        } else {
            if (user.getRolls().contains(Roll.ADMIN)) {
                System.err.println("Không thể khóa quyền admin.");
            } else {
                user.setStatus(!user.isStatus());
                userController.save(user);
                System.out.println("\033[0;34mTrạng thái đã thay đổi.\033[0m");
            }
        }
    }

    //product
//    public static void viewProduct(UserController userController){
//        while (true){
//            Navbar.menuUser();
//            int choice = InputMethod.getInteger();
//            switch (choice){
//                case 1:
//                    // Xem gio hang
//                    viewAllProduct();
//                    break;
//                case 2:
//                    // thêm giỏ hàng
//                    CartManager.addCart();
//                    break;
//                case 3:
//                    // Xem giỏ hàng
//                    new CartManager();
//                    break;
//                case 4:
//                    // Lịch sử mua hàng
//                    new OrderManager();
//                    break;
//                case 0:
//                    Navbar.menuStore();
//                    break;
//
//            }
//        }
//    }
    public static void viewAllProduct() {
        productService.showProductUser();
    }


}
