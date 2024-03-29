package com.example.movielog.service;

import com.example.movielog.model.user.User;
import com.example.movielog.repository.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

  @Mock
  UserRepository userRepository;

  @InjectMocks
  UserService userService;

  @Test
  @DisplayName("회원 가입")
  void join() {

    // given
    User mockUser = User.builder()
            .email("sunny@gmail.com")
            .password("1234")
            .nickname("sunny")
            .build();

    when(userRepository.save(any())).thenReturn(mockUser);

    // when
    User user = userService.join(mockUser);

    // then
    assertThat(user.getEmail(), is("sunny@gmail.com"));
  }

  @Test
  @DisplayName("회원 닉네임 수정하기")
  void update() {

    // given
    User mockUser = User.builder()
            .email("sunny@gmail.com")
            .password("1234")
            .nickname("sunny")
            .build();

    // when
    userService.update(mockUser, "cloud");

    // then
    assertThat(mockUser.getNickname(), is("cloud"));
  }

  @Test
  @DisplayName("이메일으로 회원 찾기")
  void findByEmail() {

    // given
    User user = User.builder()
            .email("sunny@gmail.com")
            .password("1234")
            .nickname("sunny")
            .build();

    // when
    Optional<User> mockUser = Optional.of(user);
    when(userService.findByEmail(any())).thenReturn(mockUser);

    Optional<User> actualUser = this.userService.findByEmail(user.getEmail());

    // then
    assertThat(user.getId(), is(actualUser.get().getId()));
    assertThat(user.getEmail(), is(actualUser.get().getEmail()));
  }
}
