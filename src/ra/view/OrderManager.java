package ra.view;

import ra.config.Constans;
import ra.config.InputMethod;
import ra.config.Message;
import ra.controller.OrderController;
import ra.controller.ProductController;
import ra.model.*;
import ra.util.DataBase;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private OrderController orderController;
    private static ProductController productController = new ProductController();

    public OrderManager() {
        orderController = new OrderController();
        while (true) {
            System.out.println("╔══════════════════════════════════════════╗");
            System.out.println("║            \033[0;34mLịch sử đặt hàng\033[0m              ║");
            System.out.println("╠════╦═════════════════════════════════════╣");
            System.out.println("║ \033[0;35m 1 \033[0m║ Xem tất cả đơn                      ║");
            System.out.println("║ \033[0;35m 2 \033[0m║ Xem các đơn đang chờ                ║");
            System.out.println("║ \033[0;35m 3 \033[0m║ Xem các đơn đã xác nhận             ║");
            System.out.println("║ \033[0;35m 4 \033[0m║ Xem các đơn bị từ chối đặt          ║");
            System.out.println("║ \033[0;35m 5 \033[0m║ Hiển thị chi tiết đơn hàng          ║");
            System.out.println("║ \033[0;35m 0 \033[0m║ Trở lại                             ║");
            System.out.println("╚════╩═════════════════════════════════════╝");
            System.out.println("Nhập lựa chọn: ");
            int choice = InputMethod.getInteger();
            switch (choice) {
                case 1:
                    // hiển thị tất cả
                    showUserOrder();
                    break;
                case 2:
                    // chờ xác nhận
                    showOrderByCode((byte) 0);
                    break;
                case 3:
                    showOrderByCode((byte) 1);
                    break;
                case 4:
                    showOrderByCode((byte) 2);
                    break;
                case 5:
                    // chi tiết hóa đơn
                    showOrderDetail();
                    break;
                case 0:

                    break;
                default:
                    System.err.println("Nhập lựa chọn từ 0 đến 5");
            }
            if (choice == 0) {
                break;
            }
        }

    }

    public void showUserOrder() {
        List<Order> list = orderController.findOrderByUserId();
        if (list.isEmpty()) {
            System.err.println("Đơn hàng rỗng");
            return;
        }
        for (Order o : list) {
            System.out.println("\033[0;34m--------------------------------");
            System.out.println("\033[0;35m" + o);
            System.out.println("\033[0;3m--------------------------------");
        }
    }

    public void showOrderByCode(byte code) {
        List<Order> orders = orderController.findOrderByUserId();
        List<Order> filter = new ArrayList<>();
        for (Order o : orders) {
            if (o.getStatus() == code) {
                filter.add(o);
            }
        }
        if (filter.isEmpty()) {
            System.err.println("Đơn hàng rỗng");
            return;
        }
        for (Order o : filter) {
            System.out.println(o);
        }
    }
//    public void showOrderByCodeAdmin(byte code) {
//        List<Order> orders = orderController.findOrderByUserId();
//        List<Order> filter = new ArrayList<>();
//        for (Order o : orders) {
//            if (o.getStatus() == code) {
//                filter.add(o);
//            }
//        }
//        if (filter.isEmpty()) {
//            System.err.println("order is empty");
//            return;
//        }
//        for (Order o : filter) {
//            System.out.println(o);
//        }
//    }

    public void showOrderDetail() {
        System.out.println("Nhập ID đơn hàng:");
        int orderId = InputMethod.getInteger();
        Order order = orderController.findById(orderId);
        if (order == null) {
            System.err.println(Constans.NOT_FOUND);
            return;
        }

        // in ra chi tiết hóa đơn
        System.out.printf("\033[0;34m---------------------Chi tiết đơn hàng-----------------------\n");
        System.out.printf(" \033[0;35m                   Id:%5d                              \n", order.getId());
        System.out.println("\033[0;34m--------------------Thông tin --------------------------");
        System.out.print("\033[0;35mNgười nhận: " + order.getReceiver() + "\033[0;35m | Số điện thoại : " + order.getPhoneNumber() + "\n");
        System.out.println("\033[0;35mĐịa chỉ : " + order.getAddress());
        System.out.println("\033[0;34m--------------------Chi tiết-------------------------------");
        for (CartItem ci : order.getOrderDetail()) {
            System.out.println("\033[0;35mId: " + ci.getId() + "\033[0;35m | Tên sản phẩm: " + ci.getProduct().getNameProduct() + "\033[0;35m | Giá: " + ci.getProduct().getPrice() + "\033[0;34m$" + "\033[0;35m | Số lượng: " + ci.getQuantity());
        }
        System.out.println("\033[0;35mTổng số tiền thanh toán : " + order.getTotal() + "$");
        System.out.println("\033[0;34m------------------------Kết thúc------------------------------\033[0m ");
        if (order.getStatus() == 0) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║  \033[0;35mBạn có muốn hủy đơn đặt hàng này?  \033[0m   ║");
            System.out.println("╠════╦═══════════════════════════════════╣");
            System.out.println("║ \033[0;35m 1 \033[0m║ Có                                ║");
            System.out.println("║ \033[0;35m 2 \033[0m║ Không                             ║");
            System.out.println("╚════╩═══════════════════════════════════╝");
            System.out.println("Nhập lựa chọn");
            int choice = InputMethod.getInteger();
            if (choice == 1) {
                System.out.println("Đơn hàng đã huỷ");
                for (CartItem cartItem : order.getOrderDetail()) {
                    Product product = productController.findById(cartItem.getProduct().getId());
                    product.setStock(product.getStock() + cartItem.getQuantity());
                    productController.save(product);
                    if (Navbar.userLogin.getPay().equals(Pay.CARD)) {
                        Navbar.userLogin.setMyMoney(Navbar.userLogin.getMyMoney() + (int) order.getTotal());
                    }
                }
                // hủy
                order.setStatus((byte) 2);
                orderController.save(order);
            }
        }
    }


//    public void showOrderDetailAdmin() {
//        System.out.println("Enter order ID");
//        int orderId = InputMethod.getInteger();
//        Order order = orderController.findByIdAdmin(orderId);
//        if (order == null) {
//            System.err.println(Constans.NOT_FOUND);
//            return;
//        }
//
//        // in ra chi tiết hóa đơn
//        System.out.printf("---------------------OrderDetail-----------------------\n");
//        System.out.printf("                    Id:%5d                              \n", order.getId());
//        System.out.println("--------------------Infomation--------------------------");
//        System.out.print("Receiver: " + order.getReceiver() + "| Phone : " + order.getPhoneNumber() + "\n");
//        System.out.println("Address : " + order.getAddress());
//        System.out.println("--------------------Detail-------------------------------");
//        for (CartItem ci : order.getOrderDetail()) {
//            System.out.println("Id: " + ci.getId() + " | Nameproduct: " + ci.getProduct().getNameProduct() + " | Price: " + ci.getProduct().getPrice() + "$" + " | Quantity: " + ci.getQuantity());
//        }
//        System.out.println("Total : " + order.getTotal() + "$");
//        System.out.println("------------------------End------------------------------");
//        if (order.getStatus() == 0) {
//            System.out.println("Do you want to cancel this order?");
//            System.out.println("1. Yes");
//            System.out.println("2. No");
//            System.out.println("Enter your choice");
//            int choice = InputMethod.getInteger();
//            if (choice == 1) {
//                // hủy
//                order.setStatus((byte) 2);
//                orderController.save(order);
//            }
//        }
//    }


}
