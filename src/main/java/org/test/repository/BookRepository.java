package org.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.test.entity.Book;

/**
 * Created by okan on 18.03.2017.
 */
@Repository
public interface BookRepository extends CrudRepository<Book,Long>{
    public Book findBookByIsbn(String isbn);
}
