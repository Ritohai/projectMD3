package ra.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements Serializable {
    private int id;
    private String name;
    private String username;
    private String password;
    private String address;
    private String phoneNumber;

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    private boolean status = true;
    private Set<Roll> rolls = new HashSet<Roll>();
    private List<CartItem> cartItems = new ArrayList<>();
    private int myMoney = 0;
    private Pay pay;

    public User() {
    }

    public User(int id, String name, String username, String password, String address, String phoneNumber, boolean status, Set<Roll> rolls) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.rolls = rolls;
    }

    public User(int id, String name, String username, String password, String address, String phoneNumber, boolean status, Set<Roll> rolls, List<CartItem> cartItems, int myMoney) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.rolls = rolls;
        this.cartItems = cartItems;
        this.myMoney = myMoney;
    }

    public User(int id, String name, String username, String password, String address, String phoneNumber, boolean status, Set<Roll> rolls, List<CartItem> cartItems, int myMoney, Pay pay) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.rolls = rolls;
        this.cartItems = cartItems;
        this.myMoney = myMoney;
        this.pay = pay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Roll> getRolls() {
        return rolls;
    }

    public void setRolls(Set<Roll> rolls) {
        this.rolls = rolls;
    }


    public int getMyMoney() {
        return myMoney;
    }

    public void setMyMoney(int myMoney) {
        this.myMoney = myMoney;
    }

    public Pay getPay() {
        return pay;
    }

    public void setPay(Pay pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        String roll;
        if (rolls.contains(Roll.ADMIN)) {
            roll = "ADMIN";
        } else {
            roll = "USER";
        }
        return "Id: " + id + " | Tên: " + name + " | Tên tài khoản: " + username +" | Địa chỉ: " + address + " | Số điện thoại: "+ phoneNumber+ " |Phân quyền: " + roll + " | Trạng thái: " +(status?"Mở khóa":"Khóa") ;
    }
}
