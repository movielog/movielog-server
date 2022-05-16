package com.example.movielog.model.user;

import com.example.movielog.model.review.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class User implements UserDetails {

  @Id @GeneratedValue
  @Column(name = "USER_NO")
  private Long id;

  private String email;

  private String password;

  private String nickname;

  @OneToMany(mappedBy = "user")
  private List<Review> reviews = new ArrayList<>();

  public User(String email, String password, String nickname){
    this.email = email;
    this.password = password;
    this.nickname = nickname;
  }

  public User(Long id, String email, String password){
    this.id = id;
    this.email = email;
    this.password = password;
  }

  @ElementCollection(fetch = FetchType.EAGER)
  @Builder.Default
  private List<String> roles = new ArrayList<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
  }

  public void update(String nickname){
    this.nickname = nickname;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
