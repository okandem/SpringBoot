package org.test;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.test.repository.PublisherRepository;

/**
 * Created by okan on 30.03.2017.
 */
@Configuration
@UsedForTesting
public class TestMockBeansConfig {
  @Bean
  @Primary
  public PublisherRepository createMockPublisherRepository(){
    return Mockito.mock(PublisherRepository.class);
  }
}
