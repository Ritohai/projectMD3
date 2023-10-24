package ra.view;


import ra.model.Product;
import ra.model.Roll;
import ra.model.User;
import ra.service.UserService;
import ra.util.DataBase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Set<Roll> set = new HashSet<>();
        Set<Roll> set2 = new HashSet<>();
        set.add(Roll.USER);
        set2.add(Roll.ADMIN);
        User users = new User(2, "Hai", "hai0123", "123456", "NB", "0123456789", true, set);
        User admin = new User();
        admin.setId(1);
        admin.setUsername("admin123");
        admin.setPassword("admin123");
        admin.setRolls(set2);
        userService.save(admin);
        userService.save(users);
        DataBase<User> userData = new DataBase();
        for (User user : userData.readFromFile(DataBase.USER_PATH)) {
            System.out.println("-----------------------------------------");
            System.out.println(user);
        }

        // Sp
        List<Product> products = new ArrayList<Product>();
//        Product p1 = new Product(1, "Ổ cứng 500Gb", "Ổ cứng", 120, "Ổ cứng tốt, chạy tot", "kkkk", 500, true);

    }
}
