package ra.service;

import java.util.List;

public interface IGenericService<T, E> {
    List<T> findAll();
    void save(T t);  // Thêm mới, add, update
    void delete(E id); // Xóa
    T findById(E id);

}
