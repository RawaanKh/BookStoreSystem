package com.example.book_store.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    @NotEmpty(message = "Name cannot be Empty")
    @Size(min = 2,message = "name must be 2 letters or longer")
    private String name;
    @NotNull(message = "Number of available books must be provided")
    private Integer bookCount;
    @NotEmpty(message = "Allowed Book Genre(Adventure stories.\n Classics.\n Crime.\n Fairy tales, fables, and folk tales.\n  Fantasy.\n Historical fiction.\n  Horror.\n  Humour and satire.) ")
    private String genre;

    //ManyToOne Relation(Many books has one store)
    @ManyToOne
    @JoinColumn(name = "store_id",referencedColumnName = "id")
    @JsonIgnore
    private Store store;
}
