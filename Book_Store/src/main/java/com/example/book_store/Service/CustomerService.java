package com.example.book_store.Service;

import com.example.book_store.Api.ApiException;
import com.example.book_store.Model.Customer;
import com.example.book_store.Model.Store;
import com.example.book_store.Repository.CustomerRepository;
import com.example.book_store.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
    public void addCustomer(Customer customer){
        customerRepository.save(customer);
    }
    public boolean updateCustomer(Integer ID,Customer customer){
        Customer oldCustomer=customerRepository.findCustomerById(ID);
        if(oldCustomer==null){
            return false;
        }
        oldCustomer.setName(customer.getName());
        oldCustomer.setPhoneNumber(customer.getPhoneNumber());
        oldCustomer.setEmail(customer.getEmail());
        customerRepository.save(oldCustomer);
        return true;
    }
    public boolean deleteCustomer(Integer ID){
        Customer oldCustomer=customerRepository.findCustomerById(ID);
        if(oldCustomer==null){
            return false;
        }
        customerRepository.delete(oldCustomer);
        return true;
    }
    public List<Customer> getCustomersByStoreId(Integer id){
        Store store=storeRepository.findStoreById(id);
        if(store==null){
            throw new ApiException("Store Not Found");
        }
        List<Customer> customers=customerRepository.findCustomersByStoreId(id);
        return customers;
    }

}
