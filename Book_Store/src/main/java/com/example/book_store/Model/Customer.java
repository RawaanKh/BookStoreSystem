package com.example.book_store.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    @NotEmpty(message = "Name cannot be Empty")
    @Size(min = 2,message = "name must be 2 letters or longer")
    private String name;
    @NotEmpty(message = "phone number cannot be Empty")
    @Size(min = 10,max = 10,message = "phone number must be 10 digits")
    private String phoneNumber;
    @Email(message = "Invalid Email")
    @NotEmpty(message = "Email cannot be Empty")
    private String email;

    //ManyToMany Relation(Many Stores has Many Customers)
    @ManyToMany
    @JsonIgnore
    private Set<Store> stores;


}
