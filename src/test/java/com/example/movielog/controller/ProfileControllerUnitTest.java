package com.example.movielog.controller;

import com.example.movielog.controller.profile.ProfileController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

public class ProfileControllerUnitTest {

  @Test
  public void real_profile_조회(){
    String expectedProfile = "real";
    MockEnvironment env = new MockEnvironment();

    env.addActiveProfile(expectedProfile);
    env.addActiveProfile("real-db");

    ProfileController controller = new ProfileController(env);

    String profile = controller.profile();

    Assertions.assertEquals(profile, expectedProfile);
  }

  @Test
  public void real_profile_없으면_첫번째조회(){
    String expectedProfile = "oauth";
    MockEnvironment env = new MockEnvironment();

    env.addActiveProfile(expectedProfile);
    env.addActiveProfile("real-db");

    ProfileController controller = new ProfileController(env);

    String profile = controller.profile();

    Assertions.assertEquals(profile, expectedProfile);
  }

  @Test
  public void hi_default(){
    String expectedProfile = "default";
    MockEnvironment env = new MockEnvironment();

    ProfileController controller = new ProfileController(env);

    String profile = controller.profile();

    Assertions.assertEquals(profile, expectedProfile);
  }

}
