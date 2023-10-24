package ra.view;

import ra.config.Constans;
import ra.config.InputMethod;
import ra.controller.CartController;
import ra.controller.OrderController;
import ra.controller.ProductController;
import ra.model.*;
import ra.util.DataBase;

public class CartManager {
    private static CartController cartController;
    private ProductController productController;

    public CartManager() {
        cartController = new CartController(Navbar.userLogin);
        productController = new ProductController();
        while (true) {
            Navbar.menuCart();
            int choice = InputMethod.getInteger();
            switch (choice) {
                case 1:
                    showCart();
                    break;
                case 2:
                    changeQuantity();
                    break;
                case 3:
                    deleteItem();
                    break;
                case 4:
                    cartController.clearAll();
                    break;
                case 5:
                    checkout(productController);
                    break;
                case 6:
                    myAccount();
                    break;
                case 7:
                    new OrderManager();
                    break;
                case 8:
                    Navbar.menuUser();
                    break;
                default:
                    System.out.println("Chọn từ 1 đến 8.");
            }
        }
    }


    public static void addCart() {
        ProductController productController1 = new ProductController();
        CartItem cartItem = new CartItem();
        cartController = new CartController(Navbar.userLogin);
        System.out.println("Nhap Id muon them: ");
        int id = InputMethod.getInteger();
        Product product = productController1.findById(id);
        if (product == null || product.isStatus() == false || product.getStock() == 0) {
            System.err.println(Constans.NOT_FOUND);
        } else {
            cartItem.setProduct(product);
            cartItem.setId(cartController.getNewId());
            System.out.println("Nhập số lượng: ");
            cartItem.setQuantity(InputMethod.getInteger());
            cartController.save(cartItem);
            System.out.println("Thêm thành công!!!");
        }
    }

    public void showCart() {
        User userLogin = Navbar.userLogin;
        if (userLogin.getCartItems().isEmpty()) {
            System.out.println("Giỏ hàng rỗng");
            return;
        }
        for (CartItem cart : userLogin.getCartItems()) {
            cart.setProduct(productController.findById(cart.getProduct().getId()));
            System.out.println("Id: " + cart.getId() + " | Tên sản phẩm: " + cart.getProduct().getNameProduct() +
                    " | Giá: " + cart.getProduct().getPrice() + "$" + " | Số lượng: " + cart.getQuantity());
        }
    }

    public void changeQuantity() {
        System.out.println("Nhập Id muốn thay đổi: ");
        int cartId = InputMethod.getInteger();
        CartItem cartItem = cartController.findById(cartId);
        if (cartItem == null) {
            System.err.println(Constans.NOT_FOUND);
            return;
        }
        System.out.println("Nhập số lượng muốn thay đổi: ");
        cartItem.setQuantity(InputMethod.getInteger());
        cartController.save(cartItem);
    }

    public void deleteItem() {
        System.out.println("Nhập Id muốn xóa: ");
        int idCart = InputMethod.getInteger();
        if (cartController.findById(idCart) == null) {
            System.err.println(Constans.NOT_FOUND);
            return;
        }
        cartController.delete(idCart);
        System.out.println("Xóa thành công");
    }

    public void checkout(ProductController productController) {
        OrderController orderController = new OrderController();
        User userLogin = Navbar.userLogin;
        if (userLogin.getCartItems().isEmpty()) {
            System.err.println("Giỏ hàng rỗng");
            return;
        }
        //  kiểm tra số lượng trong kho
        for (CartItem ci : userLogin.getCartItems()) {
            Product p = productController.findById(ci.getProduct().getId());
            if (ci.getQuantity() > p.getStock()) {
                System.err.println("Sản phẩm " + p.getNameProduct() + " chỉ còn " + p.getStock() + " sản phẩm, vui lòng giảm số lượng");
                return;
            }
        }

        Order newOrder = new Order();
        newOrder.setId(orderController.getNewId());
        // coppy sp trong gior hàng sang hóa đơn
        newOrder.setOrderDetail(userLogin.getCartItems());
        // cập nhật tổng tiền
        int total = 0;
        for (CartItem ci : userLogin.getCartItems()) {
            total += (int) (ci.getQuantity() * ci.getProduct().getPrice());
        }
        newOrder.setTotal(total);
        newOrder.setIdUser(userLogin.getId());
        System.out.println("Nhập tên: ");
        newOrder.setReceiver(InputMethod.getString());
        System.out.println("Nhập số điện thoại: ");
        newOrder.setPhoneNumber(InputMethod.getPhoneNumber());
        System.out.println("Nhập địa chỉ: ");
        newOrder.setAddress(InputMethod.getString());
        int choice;
        boolean check = true;
        while (check) {
            System.out.println("Lựa chọn thanh toán:\n" +
                    "1. Thanh toán tiền mặt.\n" +
                    "2. Thanh toán qua ví.");
            choice = InputMethod.getInteger();
            switch (choice) {
                case 1:
                    userLogin.setPay(Pay.CASH);
                    System.out.println("Số tiền thanh toán tiền mặt: " + total);
                    check = false;
                    break;
                case 2:
                    userLogin.setPay(Pay.CARD);
                    System.out.println("Thanh toán qua ví.");
                    if (userLogin.getMyMoney() < total) {
                        System.err.println("Tài khoản của bạn không đủ.");
                        myAccount();
                    } else {
                        userLogin.setMyMoney(userLogin.getMyMoney() - total);
                        check = false;
                    }
                    break;
                default:
                    System.out.println("Lựa chọn chưa đúng.");
                    break;
            }
        }
        orderController.save(newOrder);
        // giảm số lượng đi
        for (CartItem ci : userLogin.getCartItems()) {
            Product p = productController.findById(ci.getProduct().getId());
            p.setStock(p.getStock() - ci.getQuantity());
            productController.save(p);
        }
        System.out.println(newOrder);
        cartController.clearAll();
    }

    private void myAccount() {
        int choice = 0;
        while (choice != 3) {
            System.out.println("╔══════════════════════════╗");
            System.out.println("║\033[0;34m       Ví của " + Navbar.userLogin.getName() + "        \033[0m ║");
            System.out.println("╠══════════════════════════╣");
            System.out.println("║\033[0;35m 1. Tiền trong tài khoản  \033[0m║");
            System.out.println("║\033[0;35m 2. Nạp thêm tiền         \033[0m║");
            System.out.println("║\033[0;35m 3. Trở về                \033[0m║");
            System.out.println("╚══════════════════════════╝");
            System.out.println("Lựa chọn của bạn: ");
            choice = InputMethod.getInteger();
            switch (choice) {
                case 1:
                    showMoney();
                    break;
                case 2:
                    addMoney();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Nhập lại từ 1 - 3.");
                    break;
            }
        }
    }

    private void showMoney() {
        if (Navbar.userLogin.getMyMoney() == 0) {
            System.out.println("Tài khoản của bạn không có tiền.");
        } else {
            System.out.println("Số tiền trong tài khoản là: " + Navbar.userLogin.getMyMoney());
        }
    }

    private void addMoney() {
        System.out.println("Nhập số tiền bạn muốn nạp vào: ");
        int money = InputMethod.getInteger();
        int number = Navbar.userLogin.getMyMoney() + money;
        Navbar.userLogin.setMyMoney(number);
//        if() {
        System.out.println("Số tiền trong tài khoản bạn là: " + (Navbar.userLogin.getMyMoney()));
//        }
    }
}
