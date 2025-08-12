package com.example.demospringboot.service;

// import java.util.List;
// import java.util.Optional;

// public abstract class BaseService<T, ID> {

//     public abstract List<T> findAll();

//     public abstract Optional<T> findById(ID id);

//     public abstract T save(T entity);

//     public abstract void deleteById(ID id);
// }

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {

    // karena hampir semua service yang dibuat terdapat findall, find by id, delete, save
    // maka dibuat interface yang bisa mewakili fungsi2 tersebut, nantinya tiap class akan override
    // getAll -> findAll
    // add -> save
    // delete -> delete
    // getById -> findById


    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T entity);

    void deleteById(ID id);
}