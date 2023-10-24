package ra.view;

import ra.config.Constans;
import ra.config.InputMethod;
import ra.config.Message;
import ra.controller.OrderController;
import ra.model.Order;
import ra.util.DataBase;

import java.util.List;

public class OrderAdmin {
    private OrderController orderController;

    public OrderAdmin() {
        orderController = new OrderController();
        while (true) {
            System.out.println("╔════════════════════════════════════╗");
            System.out.println("║         \033[0;34mLịch sử đơn hàng\033[0m           ║");
            System.out.println("╠════╦═══════════════════════════════╣");
            System.out.println("║ \033[0;35m 1 \033[0m║ Xem tất cả đơn hàng           ║");
            System.out.println("║ \033[0;35m 2 \033[0m║ Xác nhận/ Hủy bỏ đơn hàng     ║");
            System.out.println("║ \033[0;35m 3 \033[0m║ Tổng doanh thu                ║");
            System.out.println("║ \033[0;35m 0 \033[0m║ Trở lại                       ║");
            System.out.println("╚════╩═══════════════════════════════╝");
            System.out.println("Nhập lựa chọn: ");
            int choice = InputMethod.getInteger();
            switch (choice) {
                case 1:
//                  Hiển thị tất cả
                    showAllOrder();
                    break;
                case 2:
//                  Xác nhận hoặc hủy đơn hàng
                    orderAction();
                    break;
                case 3:
//                  Tổng doanh thu
                    totalRevenue();
                    break;
                case 0:
                    Navbar.menuAdmin();
                    break;
                default:
                    System.err.println("Nhập giá trị từ 0 đến 3.");
                    break;
            }
        }

    }


    public void orderAction() {
        DataBase<Order> orderData;
        List<Order> orders;
        orderData = new DataBase<>();
        orders = orderData.readFromFile(DataBase.ORDER_PATH);
        byte code;
        System.out.println("Nhập ID đơn hàng");
        int id = InputMethod.getInteger();
        Order order = orderController.findByIdAdmin(id);
        if (order == null) {
            System.err.println(Constans.NOT_FOUND);
        } else {
            System.out.println(order);
            System.out.println("╔════════════════════════════════╗");
            System.out.println("║      Chọn hành động            ║");
            System.out.println("╠════╦═══════════════════════════╣");
            System.out.println("║ \033[0;35m  1\033[0m║ Xác nhận                  ║");
            System.out.println("║ \033[0;35m  2\033[0m║ Hủy bỏ                    ║");
            System.out.println("║ \033[0;35m  3\033[0m║ Trở lại                   ║");
            System.out.println("╚════╩══════════════════════════╝");
            System.out.print("Nhập lựa chọn: ");
            int choice = InputMethod.getInteger();
            switch (choice) {
                case 1:
                    code = 1;
                    order.setStatus(code);
                    orderController.save(order);
                    break;
                case 2:
                    code = 2;
                    order.setStatus(code);
                    orderController.save(order);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Chọn từ 1 đến 3.");
                    break;
            }
        }
    }

    public void showAllOrder() {
        List<Order> list = orderController.findAll();
        if (list.isEmpty()) {
            System.err.println("Đơn hàng rỗng");
            return;
        }
        for (Order o : list) {
            System.out.println(o);
        }
    }

    private void totalRevenue() {
        DataBase<Order> orderData;
        List<Order> orders;
        orderData = new DataBase<>();
        orders = orderData.readFromFile(DataBase.ORDER_PATH);
        double sum = 0;
        for (Order o : orderController.findAll()) {
            if (o.getStatus() == 1) {
                sum += o.getTotal();
            }
        }
        System.out.println("Tổng doanh thu là: " + sum);
    }
}
