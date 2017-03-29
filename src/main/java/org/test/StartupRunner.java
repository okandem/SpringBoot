package org.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.test.entity.Author;
import org.test.entity.Book;
import org.test.entity.Publisher;
import org.test.repository.AuthorRepository;
import org.test.repository.BookRepository;
import org.test.repository.PublisherRepository;


/**
 * Created by okan on 18.03.2017.
 */
public class StartupRunner implements CommandLineRunner {
    protected  final Log logger = LogFactory.getLog(getClass());
//    @Autowired
//    private DataSource ds;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;


//    @Scheduled(initialDelay = 1000, fixedRate = 10000)
//    public void run() {
//        logger.info("sayi:" +bookRepository.count());
//    }


    @Override
    public void run(String... args) throws Exception {
//        logger.info("DataSource:"+ ds.toString());

//        Author author	=	new	Author("Alex",	"Antonov");
//        author	=	authorRepository.save(author);
//        Publisher publisher	=	new	Publisher("Packt");
//        publisher	=	publisherRepository.save(publisher);
//        Book book	=	new	Book("978-1-78528-415-1",	"Spring	Boot	Recipes",	author,	publisher);
//        bookRepository.save(book);
        logger.info("Kitab sayısı :"+ bookRepository.count());
        logger.info("Kitap:"+ bookRepository.findBookByIsbn("978-1-78528-415-1").getTitle());

    }
}
