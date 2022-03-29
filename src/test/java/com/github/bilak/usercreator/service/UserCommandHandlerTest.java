package com.github.bilak.usercreator.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.github.bilak.usercreator.data.model.CreateUserCommand;
import com.github.bilak.usercreator.data.model.DeleteUsersCommand;
import com.github.bilak.usercreator.data.model.PrintUsersCommand;
import com.github.bilak.usercreator.data.repository.UserRepository;

class UserCommandHandlerTest extends CommonMockitoTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserCommandHandlerImpl tested;

  @Test
  void onCreateUserCommand_userIsCreatedWithRepository() {
    tested.handleCommand(new CreateUserCommand(1, "group", "username"));
    verify(userRepository).addUser(1, "group", "username");
  }

  @Test
  void onDeleteUsersCommand_usersAreDeletedWithRepository() {
    tested.handleCommand(new DeleteUsersCommand());
    verify(userRepository).deleteAll();
  }

  @Test
  void onPrintUsersCommand_usersAreListedWithRepository() {
    tested.handleCommand(new PrintUsersCommand());
    verify(userRepository).findAll();
  }
}