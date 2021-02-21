package service;

import java.util.List;

public interface IService<T> {
    void Create(T t);
    T findById(int id);
    List findAll();
    void delete(int id);
    void update(T t);

}
