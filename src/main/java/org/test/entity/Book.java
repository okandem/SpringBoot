package org.test.entity;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by okan on 18.03.2017.
 */
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String isbn;
    private String title;
    private String description;

    @ManyToOne
    private Author author;
    @ManyToOne
    private Publisher publisher;
    @ManyToMany
    private List<Reviewer> reviewers;

    protected Book() {}

    public Book (String isbn,String title,Author author, Publisher publisher) {
        this.isbn=isbn;
        this.title=title;
        this.author=author;
        this.publisher=publisher;

    }


    public List<Reviewer> getReviewers() {
        return reviewers;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
