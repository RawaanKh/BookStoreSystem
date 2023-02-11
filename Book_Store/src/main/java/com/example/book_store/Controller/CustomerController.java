package com.example.book_store.Controller;

import com.example.book_store.Api.ApiException;
import com.example.book_store.Model.Customer;
import com.example.book_store.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/Customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/get")
    public ResponseEntity getCustomers(){
        List<Customer> customers=customerService.getCustomers();
        return ResponseEntity.status(200).body(customers);
    }
    @PostMapping("/add")
    public ResponseEntity addCustomer(@Valid @RequestBody Customer customer ){
        customerService.addCustomer(customer);
        return ResponseEntity.status(200).body("Customer Added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Integer id,@Valid@RequestBody Customer customer){
        boolean isUpdate= customerService.updateCustomer(id,customer);
        if(isUpdate) {
            return ResponseEntity.status(200).body("Customer Updated");
        }
        throw new ApiException("Invalid Id");
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity deleteCustomer(@PathVariable Integer id){
        boolean isDelete= customerService.deleteCustomer(id);
        if(isDelete) {
            return ResponseEntity.status(200).body("Customer Deleted");
        }
        throw new ApiException("Invalid  Id");
    }






}
