package ra.service;

import ra.model.Order;
import ra.util.DataBase;
import ra.view.Navbar;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private DataBase<Order> orderData;
    private List<Order> orders;

    public OrderService() {
        orderData= new DataBase<>();
        orders = orderData.readFromFile(DataBase.ORDER_PATH);
    }
    public void save(Order o){
        int index = -1;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == o.getId()) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            // add
            orders.add(o);
        } else {
            // update
            orders.set(index, o);
        }
        orderData.writeToFile(orders,DataBase.ORDER_PATH);
    }
    public Order findById(int id){
        for (Order o: findOrderByUserId()
        ) {
            if(o.getId()==id){
                return o;
            }
        }
        return  null;
    }
    public Order findByIdAdmin(int id){
        orders = orderData.readFromFile(DataBase.ORDER_PATH);
        for (Order o: orders
        ) {
            if(o.getId()==id){
                return o;
            }
        }
        return  null;
    }
    public List<Order> findOrderByUserId(){
        List<Order> findList = new ArrayList<>();
        for (Order o: orders) {
            if(o.getIdUser()== Navbar.userLogin.getId()){
                findList.add(o);
            }
        }
        return findList;
    }
    public int getNewId(){
        int max = 0;
        for (Order o : orders
        ) {
            if(o.getId() > max){
                max= o.getId();
            }
        }
        return max+1;
    }

    public List<Order> findAll() {
        return orders;
    }
}
