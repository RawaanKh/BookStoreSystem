package com.example.book_store.Service;

import com.example.book_store.Api.ApiException;
import com.example.book_store.Model.Book;
import com.example.book_store.Model.Store;
import com.example.book_store.Repository.BookRepository;
import com.example.book_store.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private  final BookRepository bookRepository;
    private final StoreRepository storeRepository;
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
    public void addBook(Book book){
        bookRepository.save(book);
    }
    public boolean updateBook(Integer ID,Book book){
        Book oldBook=bookRepository.findBookById(ID);
        if(oldBook==null){
            return false;
        }
        oldBook.setName(book.getName());
        oldBook.setBookCount(book.getBookCount());
        oldBook.setGenre(book.getGenre());
        bookRepository.save(oldBook);
        return true;
    }
    public boolean deleteBook(Integer ID){
        Book oldBook=bookRepository.findBookById(ID);
        if(oldBook==null){
            return false;
        }
        bookRepository.delete(oldBook);
        return true;
    }
    public void assignBookToStore(Integer store_id,Integer book_id){
        Store store=storeRepository.findStoreById(store_id) ;
        Book book=bookRepository.findBookById(book_id);
        if(store==null||book==null){
            throw new ApiException("Invalid Entries ");
        }
        book.setStore(store);
        bookRepository.save(book);
    }
    public Integer getNumberOfBooks(Integer id){
        Book book=bookRepository.findBookById(id);
        if(book==null){
            throw new ApiException("Required Book Not Found");
        }
        return book.getBookCount();
    }
    public Book getBookInfo(String name){
        Book book=bookRepository.findBookByName(name);
        if(book==null){
            throw new ApiException("Required Book Not Found");
        }
        return book;
    }
    public List<Book> getBooksByGenre(String genre){
        List<Book>books=bookRepository.findBooksByGenre(genre);
        if(books==null){
            throw new ApiException("Required Genre Not Found");
        }
        return books;
    }
    public List<Book> getBooksByStoreId(Integer id){
        Store store=storeRepository.findStoreById(id);
        if(store==null){
            throw new ApiException("Store Not Found");
        }
        List<Book>books=bookRepository.findBooksByStoreId(id);
        return books;
    }
}
