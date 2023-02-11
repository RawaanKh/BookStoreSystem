package com.example.book_store.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class Location {
    @Id
    private Integer id;
    @NotEmpty(message = "Area cannot be Empty")
    private String area;
    @NotEmpty(message = "Street cannot be Empty")
    private String street;

    //OneToOne Relation(One sTore Has One Location)
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "location")
    @PrimaryKeyJoinColumn
    private Store store;

}
