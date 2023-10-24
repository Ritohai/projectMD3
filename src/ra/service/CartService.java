package ra.service;

import ra.model.CartItem;
import ra.model.User;
import ra.util.DataBase;

import java.util.ArrayList;
import java.util.List;

public class CartService implements IGenericService<CartItem, Integer> {
    private User userLogin;
    private UserService userService;
    private DataBase<CartItem> cartData = new DataBase();
    private List<CartItem> cartItems = new ArrayList<>();


//    public CartService(User userLogin) {
//        List<CartItem> list = cartData.readFromFile(DataBase.USER_PATH);
//        if (list == null) {
//            list = new ArrayList<>();
//        }
//        this.cartItems = list;
//    }

    public CartService(User userLogin) {
        this.userLogin = userLogin;
        userService = new UserService();
    }

    @Override
    public List<CartItem> findAll() {
        return userLogin.getCartItems();
    }

    @Override
    public void save(CartItem cartItem) {
//        List<CartItem> list = userLogin.getCartItems();
        if (findById(cartItem.getId()) == null) {
            CartItem ct = findByProductId(cartItem.getProduct().getId());
            if (ct != null) {
                ct.setQuantity(ct.getQuantity() + cartItem.getQuantity());
            } else {
                userLogin.getCartItems().add(cartItem);
            }
        } else {
            userLogin.getCartItems().set(userLogin.getCartItems().indexOf(findById(cartItem.getId())), cartItem);
        }
        userService.save(userLogin);
    }

    @Override
    public void delete(Integer id) {
//        cartItems.remove(findById(id));
//        cartData.writeToFile(cartItems, DataBase.USER_PATH);
        userLogin.getCartItems().remove(findById(id));
        userService.save(userLogin);
    }

    @Override
    public CartItem findById(Integer id) {
        List<CartItem> cartItems = userLogin.getCartItems();
        for (CartItem c : cartItems) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public CartItem findByProductId(int productId) {
        for (CartItem ci : userLogin.getCartItems()) {
            if (ci.getProduct().getId() == productId) {
                return ci;
            }
        }
        return null;
    }
    public int getNewId() {
        List<CartItem> list = userLogin.getCartItems();
        if (list == null) {
            return 1;
        }
        int max = 0;
        for (CartItem cartItem : list) {
            if (cartItem.getId() > max) {
                max = cartItem.getId();
            }
        }
        return max+1;
    }

    public void clearAll() {
        userLogin.setCartItems(new ArrayList<>());
        userService.save(userLogin);
    }
}

