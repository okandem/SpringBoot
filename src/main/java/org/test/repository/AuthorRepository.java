package org.test.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.test.entity.Author;

/**
 * Created by okan on 19.03.2017.
 */
@RepositoryRestResource
public interface AuthorRepository  extends PagingAndSortingRepository<Author, Long>{
}
