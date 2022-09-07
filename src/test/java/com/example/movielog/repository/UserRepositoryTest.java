package com.example.movielog.repository;

import com.example.movielog.model.user.User;
import com.example.movielog.repository.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  @DisplayName("repo: findByEmail 테스트")
  void findByEmail(){

    // given
    User user = userRepository.save(
            User.builder()
            .email("sunny@gmail.com")
            .password("1234")
            .nickname("sunny")
            .build()
    );

    // when
    Optional<User> actualUser = userRepository.findByEmail(user.getEmail());

    User actual = actualUser.get();

    // then
    assertThat(user.getEmail(), is(actual.getEmail()));
  }
}
