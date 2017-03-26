package org.test.formatters;

import org.springframework.format.Formatter;
import org.test.entity.Book;
import org.test.repository.BookRepository;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by okan on 26.03.2017.
 */
public class BookFormatter implements Formatter<Book> {

  private BookRepository repository;
  public BookFormatter(BookRepository repository){
    this.repository=repository;
  }

  @Override
  public Book parse(String bookIdentifier,Locale locale ) throws ParseException{
    Book book =repository.findBookByIsbn(bookIdentifier);
    return book!=null?book: repository.findOne(Long.valueOf(bookIdentifier));
  }
  @Override
  public String print(Book book,Locale locale){
    return book.getIsbn();
  }
}
