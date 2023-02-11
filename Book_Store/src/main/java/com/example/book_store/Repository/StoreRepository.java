package com.example.book_store.Repository;

import com.example.book_store.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store,Integer> {
    Store findStoreById(Integer id);

}
