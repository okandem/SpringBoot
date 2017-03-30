package org.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.repository.PublisherRepository;

import static org.junit.Assert.assertEquals;

/**
 * Created by okan on 30.03.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DemoApplication.class,TestMockBeansConfig.class})

public class PublisherRepositoryTests {
  @Autowired
  private PublisherRepository repository;
  @Before
  public void setupPublisherRepositoryMock() {
    Mockito.when(repository.count()).thenReturn(1L);
  }
  @Test
  public void publishersExist() {
    assertEquals(1, repository.count());
  }
  @After
  public void resetPublisherRepositoryMock() {
    Mockito.reset(repository);
  }
}

