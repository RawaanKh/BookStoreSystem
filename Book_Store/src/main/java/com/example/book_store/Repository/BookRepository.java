package com.example.book_store.Repository;

import com.example.book_store.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    Book findBookById(Integer id);
    List<Book> findBooksByStoreId(Integer id);
    //5
    Book findBookByName(String name);
    //6
    List<Book> findBooksByGenre(String genre);
}
