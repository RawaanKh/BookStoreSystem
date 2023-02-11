package com.example.book_store.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter @Getter
public class Store {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    @NotEmpty(message = "Name cannot be Empty")
    @Size(min = 2,message = "name must be 2 letters or longer")
    private String name;

    //OneToOne Relation(One store has one location)
    @OneToOne
    @MapsId
    @JsonIgnore
    private Location location;
    //OneToMany Relation(one store has many books)
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="store" )
    private Set<Book> books;

    //ManyToMany Relation(Many Stores has Many Customers)
    @ManyToMany(mappedBy = "store")
    private Set<Customer> customers;




}
