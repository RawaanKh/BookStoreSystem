package com.example.book_store.Service;

import com.example.book_store.Api.ApiException;
import com.example.book_store.Model.Book;
import com.example.book_store.Model.Customer;
import com.example.book_store.Model.Store;
import com.example.book_store.Repository.BookRepository;
import com.example.book_store.Repository.CustomerRepository;
import com.example.book_store.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;

    public List<Store> getStores(){
        return storeRepository.findAll();
    }
    public void addStore(Store store){
        storeRepository.save(store);
    }
    public boolean updateStore(Integer ID,Store store){
        Store oldStore=storeRepository.findStoreById(ID);
        if(oldStore==null){
            return false;
        }
        oldStore.setName(store.getName());
        storeRepository.save(oldStore);
        return true;
    }
    public boolean deleteStore(Integer ID){
        Store oldStore=storeRepository.findStoreById(ID);
        if(oldStore==null){
            return false;
        }
        storeRepository.delete(oldStore);
        return true;
    }
    public void assignCustomerToStore(Integer store_id,Integer customer_id){
        Store store=storeRepository.findStoreById(store_id) ;
        Customer customer=customerRepository.findCustomerById(customer_id);
        if(store==null||customer==null){
            throw new ApiException("Wrong Entries ");
        }
        store.getCustomers().add(customer);
        customer.getStores().add(store);
        storeRepository.save(store);
        customerRepository.save(customer);
    }


}
