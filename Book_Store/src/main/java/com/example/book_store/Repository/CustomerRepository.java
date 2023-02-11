package com.example.book_store.Repository;

import com.example.book_store.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findCustomerById(Integer id);
    List<Customer> findCustomersByStoreId(Integer id);
}
