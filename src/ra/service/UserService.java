package ra.service;

import ra.config.Constans;
import ra.model.User;
import ra.util.DataBase;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IGenericService<User,Integer> {
    private List<User> users;
    private DataBase<User> userData = new DataBase();
    public UserService() {
        List<User> list = userData.readFromFile(DataBase.USER_PATH);
        if(list == null){
            list = new ArrayList<>();
        }
        this.users = list; // Dữ liệu đọc từ file
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public void save(User user) {
        if(findById(user.getId()) == null){
            // add
            users.add(user);
        }else{
             // update
            users.set(users.indexOf(findById(user.getId())),user);
        }
        // lưu vào file database
        userData.writeToFile(users,DataBase.USER_PATH);
    }

    @Override
    public void delete(Integer id) {
        users.remove(findById(id));
        userData.writeToFile(users,DataBase.USER_PATH);
    }

    @Override
    public User findById(Integer id) {
        for (User u:users) {
            if(u.getId() == id) {
                return u;
            }
        }
        return null;
    }
    public void changeStatus(int idUser){
        User user = findById(idUser);
        if(user == null) {
            System.out.println(Constans.NOT_FOUND);
            return;
        }
        user.setStatus(!user.isStatus());
        save(user);

    }
    public User login(String username, String password){
        for (User u: users) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                return u;
            }
        }
    return null;
    }
    public User register(String username){

        for (User u: users) {
            if(u.getUsername().equals(username) ){
                return u;
            }
        }
        return null;
    }
    public int getNewId(){
        int max = 0;
        for (User u: users) {
            if (u.getId() > max) {
                max = u.getId();
            }
        }
        return max+1;
    }

}
