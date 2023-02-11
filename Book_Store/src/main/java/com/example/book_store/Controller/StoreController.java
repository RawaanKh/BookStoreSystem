package com.example.book_store.Controller;

import com.example.book_store.Api.ApiException;
import com.example.book_store.Model.Book;
import com.example.book_store.Model.Customer;
import com.example.book_store.Model.Store;
import com.example.book_store.Service.BookService;
import com.example.book_store.Service.CustomerService;
import com.example.book_store.Service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/Store")
@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    private final CustomerService customerService;
    @GetMapping("/get")
    public ResponseEntity getStores(){
        List<Store> stores=storeService.getStores();
        return ResponseEntity.status(200).body(stores);
    }
    @PostMapping("/add")
    public ResponseEntity addStore(@Valid @RequestBody Store store ){
        storeService.addStore(store);
        return ResponseEntity.status(200).body("Store Added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateStore(@PathVariable Integer id,@Valid@RequestBody Store store){
        boolean isUpdate= storeService.updateStore(id,store);
        if(isUpdate) {
            return ResponseEntity.status(200).body("Store Updated");
        }
        throw new ApiException("Invalid  Id");
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity deleteStore(@PathVariable Integer id){
        boolean isDelete= storeService.deleteStore(id);
        if(isDelete) {
            return ResponseEntity.status(200).body("Store Deleted");
        }
        throw new ApiException("Invalid Id");
    }
    @PutMapping("/{store_id}/{customer_id}")
    public ResponseEntity assignCustomerToStore(@PathVariable Integer store_id,@PathVariable Integer customer_id){
        storeService.assignCustomerToStore(store_id,customer_id);
        return ResponseEntity.status(200).body("Customer has been assigned");
    }
    //Create endpoint thar takes store id and return all customers
    @GetMapping("/customers/{id}")
    public ResponseEntity getAllCustomerByStoreId(@PathVariable Integer id){
        List<Customer>customers=customerService.getCustomersByStoreId(id);
        return ResponseEntity.status(200).body(customers);
    }
}
