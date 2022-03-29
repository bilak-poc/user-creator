package com.github.bilak.usercreator.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

import java.util.Queue;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.github.bilak.usercreator.data.model.Command;
import com.github.bilak.usercreator.data.model.CreateUserCommand;
import com.github.bilak.usercreator.data.model.DeleteUsersCommand;
import com.github.bilak.usercreator.data.model.PrintUsersCommand;

class UserProducerTest extends CommonMockitoTest {

  @InjectMocks
  private UserProducerImpl tested;

  @Mock
  private Queue<Command> queue;

  @Test
  void onAdd_CreateUserCommandIsAddedToQueue() {
    tested.add(1, "group", "username");
    final ArgumentCaptor<CreateUserCommand> commandCaptor = ArgumentCaptor.forClass(CreateUserCommand.class);
    verify(queue).add(commandCaptor.capture());

    assertThat(commandCaptor.getValue(), is(new CreateUserCommand(1, "group", "username")));
  }

  @Test
  void onPrintAll_PrintUsersCommandIsAddedToQueue() {
    tested.printAll();
    final ArgumentCaptor<PrintUsersCommand> commandCaptor = ArgumentCaptor.forClass(PrintUsersCommand.class);
    verify(queue).add(commandCaptor.capture());

    assertThat(commandCaptor.getValue(), instanceOf(PrintUsersCommand.class));
  }

  @Test
  void onDeleteAll_DeleteUsersCommandIsAddedToQueue() {
    tested.deleteAll();
    final ArgumentCaptor<DeleteUsersCommand> commandCaptor = ArgumentCaptor.forClass(DeleteUsersCommand.class);
    verify(queue).add(commandCaptor.capture());

    assertThat(commandCaptor.getValue(), instanceOf(DeleteUsersCommand.class));
  }

}