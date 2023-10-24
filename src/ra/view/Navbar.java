package ra.view;

import ra.config.InputMethod;
import ra.controller.CategoryController;
import ra.controller.ProductController;
import ra.controller.UserController;
import ra.model.Roll;
import ra.model.User;
import ra.service.ProductService;
import ra.service.UserService;

import java.util.Arrays;
import java.util.List;

public class Navbar {
    private static UserController userController = new UserController();
    private static ProductController productController = new ProductController();
    public static User userLogin;
    public static CategoryController categoryController = new CategoryController();


    /*private UserManager userManager;*/
    // tất cả menu giao diện điều hướng
    public static void menuStore() {
        while (true) {
            System.out.println("╔══════════════════════════╗");
            System.out.println("║ \033[0;34m      Menu Cửa hàng      \033[0m║");
            System.out.println("╠══════════════════════════╣");
            System.out.println("║\033[0;35m 1. Đăng nhập             \033[0m║");
            System.out.println("║\033[0;35m 2. Đăng ký               \033[0m║");
            System.out.println("║\033[0;35m 3. Xem sản phẩm          \033[0m║");
            System.out.println("║\033[0;35m 4. Thoát                 \033[0m║");
            System.out.println("╚══════════════════════════╝");
            System.out.println("Nhập lựa chọn của bạn");
            int choice = InputMethod.getInteger();
            switch (choice) {
                case 1:
                    Navbar.login();
                    break;
                case 2:
                    Navbar.register();
                    break;
                case 3:
                    ProductService.displayListProduct();
                    Navbar.menuStore();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nhập lựa chọn của bạn từ 1 => 5");
            }
        }
    }

    public static void menuUser() {
        while (true) {
            System.out.println("╔══════════════════════════════════════════╗");
            System.out.println("║ \033[0;34m      Xin chào - " + userLogin.getName() + "        \033[0m             ║");
            System.out.println("╠════╦═════════════════════════════════════╣");
            System.out.print("║");
            System.out.print("\033[1;36m");
            System.out.printf("%3s", "1");
            System.out.print("\033[0m");
            System.out.println(" │ Xem tất cả sản phẩm                 ║");
            System.out.print("║");
            System.out.print("\033[1;36m");
            System.out.printf("%3s", "2");
            System.out.print("\033[0m");
            System.out.println(" │ Thêm vào giỏ hàng                   ║");
            System.out.print("║");
            System.out.print("\033[1;36m");
            System.out.printf("%3s", "3");
            System.out.print("\033[0m");
            System.out.println(" │ Xem giỏ hàng                        ║");
            System.out.print("║");
            System.out.print("\033[1;36m");
            System.out.printf("%3s", "0");
            System.out.print("\033[0m");
            System.out.println(" │ Đăng xuất                           ║");
            System.out.println("╚════╩═════════════════════════════════════╝");
            System.out.println("Nhập lựa chọn: ");
            int choice = InputMethod.getInteger();
            switch (choice) {
                case 1:
                    ProductService.displayListProduct();
                    Navbar.menuUser();
                    break;
                case 2:
                    CartManager.addCart();
                    break;
                case 3:
                    new CartManager();
                    break;
                case 0:
                    Navbar.menuStore();
                    break;
                default:
                    System.out.println("Nhập lựa chọn từ 0 đến 3 ");
            }
        }
    }

    public static void menuAdmin() {

        while (true) {
            System.out.println("╔══════════════════════════╗");
            System.out.println("║ \033[0;34m       Menu Admin       \033[0m ║");
            System.out.println("╠══════════════════════════╣");
            System.out.println("║\033[0;35m 1. Quản lí người dùng    \033[0m║");
            System.out.println("║\033[0;35m 2. Quản lí danh mục      \033[0m║");
            System.out.println("║\033[0;35m 3. Quản lí sản phẩm      \033[0m║");
            System.out.println("║\033[0;35m 4. Quản lí giỏ hàng      \033[0m║");
            System.out.println("║\033[0;35m 0. Đăng xuất             \033[0m║");
            System.out.println("╚══════════════════════════╝");
            System.out.println("Nhập lựa chọn: ");
            int choice = InputMethod.getInteger();
            switch (choice) {
                case 1:
                    // Quản lí tài khoản
                    new UserManager(userController);
                    break;
                case 2:
                    // QUản lí danh mục
                    new CategoryManager(categoryController);
                    break;
                case 3:
                    // Quản lí sản phẩm
                    new ProductManager(productController);
                    break;
                case 4:
                    // Cart: Quản lí giỏ hàng
                    new OrderAdmin();
                    break;
                case 0:
                    logout();
                    break;
                default:
                    System.out.println("Nhập lựa chọn của bạn từ 0 => 4");
            }
            if (choice == 0) {
                break;
            }
        }
    }

    public static void menuAccountUser() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║        \033[0;34mMenu danh sách người dùng\033[0m         ║");
        System.out.println("╠════╦═════════════════════════════════════╣");
        System.out.println("║ \033[0;35m 1 \033[0m║ Xem tất cả danh sách                ║");
        System.out.println("║ \033[0;35m 2 \033[0m║ Khóa/Mở khóa tài khoản              ║");
        System.out.println("║ \033[0;35m 3 \033[0m║ Tìm kiếm                            ║");
        System.out.println("║ \033[0;35m 4 \033[0m║ Trở lại                             ║");
        System.out.println("╚════╩═════════════════════════════════════╝");
        System.out.println("Nhập lựa chọn: ");
    }

    public static void menuCategory() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║           \033[0;34mDanh sách danh mục\033[0m             ║");
        System.out.println("╠════╦═════════════════════════════════════╣");
        System.out.println("║ \033[0;35m 1 \033[0m║ Xem tất cả danh mục                 ║");
        System.out.println("║ \033[0;35m 2 \033[0m║ Thêm danh mục                       ║");
//        System.out.println("║ \033[0;35m 3 \033[0m║ Xóa danh mục                        ║");
        System.out.println("║ \033[0;35m 3 \033[0m║ Khóa/Mở khóa danh mục               ║");
        System.out.println("║ \033[0;35m 4 \033[0m║ Trở lại                             ║");
        System.out.println("╚════╩═════════════════════════════════════╝");
        System.out.println("Nhập lựa chọn: ");
    }

    public static void menuProduct() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║          \033[0;34mDanh sách sản phẩm  \033[0m            ║");
        System.out.println("╠════╦═════════════════════════════════════╣");
        System.out.println("║ \033[0;35m 1 \033[0m║ Xem tất cả sản phẩm                 ║");
        System.out.println("║ \033[0;35m 2 \033[0m║ Thêm sản phẩm                       ║");
        System.out.println("║ \033[0;35m 3 \033[0m║ Xóa sản phẩm                        ║");
        System.out.println("║ \033[0;35m 4 \033[0m║ Sửa sản phẩm                        ║");
        System.out.println("║ \033[0;35m 5 \033[0m║ Khóa/Mở khóa sản phẩm               ║");
        System.out.println("║ \033[0;35m 6 \033[0m║ Tìm kiếm sản phẩm                   ║");
        System.out.println("║ \033[0;35m 7 \033[0m║ Trở lại                             ║");
        System.out.println("╚════╩═════════════════════════════════════╝");
        System.out.println("Nhập lựa chọn: ");
    }

    public static void menuCart() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║            \033[0;34mDanh sách giỏ hàng\033[0m            ║");
        System.out.println("╠════╦═════════════════════════════════════╣");
        System.out.println("║ \033[0;35m 1 \033[0m║ Xem tất cả giỏ hàng                 ║");
        System.out.println("║ \033[0;35m 2 \033[0m║ Thay đổi số lượng trong giỏ hàng    ║");
        System.out.println("║ \033[0;35m 3 \033[0m║ Xóa 1 sản phẩm trong giỏ hàng       ║");
        System.out.println("║ \033[0;35m 4 \033[0m║ Xóa tất cả sản phẩm trong giỏ hàng  ║");
        System.out.println("║ \033[0;35m 5 \033[0m║ Check out                           ║");
        System.out.println("║ \033[0;35m 6 \033[0m║ Ví của tôi                          ║");
        System.out.println("║ \033[0;35m 7 \033[0m║ Đơn hàng                            ║");
        System.out.println("║ \033[0;35m 8 \033[0m║ Trở lại                             ║");
        System.out.println("╚════╩═════════════════════════════════════╝");
        System.out.println("Nhập lựa chọn: ");
    }

    public static void login() {
        System.out.println("------------------Đăng nhập----------------");
        System.out.println("Nhập tài khoản: ");
        String username = InputMethod.getusename();
        System.out.println("Nhập mật khẩu: ");
        String password = InputMethod.getpassword();
        // Kiểm tra đăng nhập

        User user = userController.login(username.trim(), password.trim());
//        System.out.println(user);
        if (user == null) {
            System.err.println("Tài khoản không tồn tại.");
        } else {
            if (user.getRolls().contains(Roll.ADMIN)) {
                userLogin = user;
                menuAdmin();
            } else {
                if (user.isStatus()) {
                    userLogin = user;
                    menuUser();
                } else {
                    System.err.println("Tài khoản bị khóa.");
                    menuStore();
                }
            }
        }
    }

    public static void register() {
        System.out.println("------------------Đăng kí----------------");
        User user = new User();
        user.setId(userController.getNewId());
        System.out.println("Nhập tên: ");
        user.setName(InputMethod.getString().trim());
        System.out.println("Nhập tên tài khoản: ");
        while (true) {
            String userName = (InputMethod.getusename().trim());
            boolean check = false;
            for (User u : userController.findAll()) {
                if (u.getUsername().equals(userName)) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                user.setUsername(userName);
                break;
            } else {
                System.err.println("Tài khoản đã tồn tại.");
            }
        }
        System.out.println("Nhập mật khẩu: ");
        user.setPassword(InputMethod.getpassword().trim());

//        Cách ghi chữ
//        System.out.println("Enter Roll(ADMIN or USER) : ");
//        String roll = InputMethod.getString();
//        String[] stringRoll = roll.split(",");
//        List<String> list = Arrays.asList(stringRoll);
//        for (String string : stringRoll) {
//            switch (string) {
//                case "ADMIN":
//                    user.getRolls().add(Roll.ADMIN);
//                case "USER":
//                    user.getRolls().add(Roll.USER);
//                default:
//                    user.getRolls().add(Roll.USER);
//            }
//        }


        System.out.println("Enter Roll : \n" +
                "1. ADMIN. \n" +
                "2. USER.");
        int choice = InputMethod.getInteger();
        switch (choice) {
            case 1:
                user.getRolls().add(Roll.ADMIN);
            case 2:
                user.getRolls().add(Roll.USER);
            default:
                user.getRolls().add(Roll.USER);
        }
        if (user.getRolls().equals(Roll.ADMIN)) {
            System.out.println();
        } else {
            System.out.println("Nhập địa chỉ: ");
            user.setAddress(InputMethod.getString());
            System.out.println("Nhập số điện thoại: ");
            while (true) {
                boolean check = false;
                String phone = InputMethod.getPhoneNumber();
                for (User u : userController.findAll()) {
                    if (u.getPhoneNumber().equals(phone)) {
                        System.err.println("Số điện thoại đã tồn tại.");
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    user.setPhoneNumber(phone);
                    break;
                }
            }
        }

        userController.save(user);
        System.out.println("Đăng kí thành công!");
//        System.out.println("Vui lòng đăng nhập!");
//        login();

    }

    public static void logout() {
        userLogin = null;
        System.out.println("Đăng xuất thành công!!!");
        menuStore();
    }

}
