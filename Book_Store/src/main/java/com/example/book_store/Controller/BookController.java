package com.example.book_store.Controller;

import com.example.book_store.Api.ApiException;
import com.example.book_store.Model.Book;
import com.example.book_store.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/Book")
@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping("/get")
    public ResponseEntity getBooks(){
        List<Book> books=bookService.getBooks();
        return ResponseEntity.status(200).body(books);
    }
    @PostMapping("/add")
    public ResponseEntity addBook(@Valid @RequestBody Book book ){
        bookService.addBook(book);
        return ResponseEntity.status(200).body("Book Added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateBook(@PathVariable Integer id,@Valid@RequestBody Book book){
        boolean isUpdate= bookService.updateBook(id,book);
        if(isUpdate) {
            return ResponseEntity.status(200).body("Book Updated");
        }
        throw new ApiException("Invalid Book");
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity deleteBook(@PathVariable Integer id){
        boolean isDelete= bookService.deleteBook(id);
        if(isDelete) {
            return ResponseEntity.status(200).body("Book Deleted");
        }
        throw new ApiException("Invalid Book");
    }
    @PutMapping("/{store_id}/book/{book_id}")
    public ResponseEntity assignBookToStore(@PathVariable Integer store_id,@PathVariable Integer book_id){
        bookService.assignBookToStore(store_id,book_id);
        return ResponseEntity.status(200).body("Book Has Been Assigned");
    }

    //Create endpoint that takes bookId and return number of books available
    @GetMapping("/number/{id}")
    public ResponseEntity getNumberOfBooks(@PathVariable Integer id){
        Integer numberOfBooks= bookService.getNumberOfBooks(id);
        return  ResponseEntity.status(400).body("Number Of Available Books: "+numberOfBooks);
    }
    //Create endpoint that takes a book name and return all book information
    @GetMapping("/name/{name}")
    public ResponseEntity getBookInfo(@PathVariable String name){
        Book book=bookService.getBookInfo(name);
        return ResponseEntity.status(200).body(book);
    }
    //Create endpoint that return all books under a specific genre
    @GetMapping("/genre/{genre}")
    public ResponseEntity getBooksByGenre(@PathVariable String genre ){
        List<Book>books=bookService.getBooksByGenre(genre);
        return  ResponseEntity.status(200).body(books);
    }
    //Create endpoint that takes store id and return all the books
    @GetMapping("/Books/{id}")
    public ResponseEntity getBooksByStoreId(@PathVariable Integer id){
        List<Book>books=bookService.getBooksByStoreId(id);
        return ResponseEntity.status(200).body(books);
    }
}
