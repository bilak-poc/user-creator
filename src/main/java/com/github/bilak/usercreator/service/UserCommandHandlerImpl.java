package com.github.bilak.usercreator.service;

import java.util.Collection;
import java.util.Queue;

import com.github.bilak.usercreator.data.entity.UserEntity;
import com.github.bilak.usercreator.data.model.Command;
import com.github.bilak.usercreator.data.model.CreateUserCommand;
import com.github.bilak.usercreator.data.model.DeleteUsersCommand;
import com.github.bilak.usercreator.data.model.PrintUsersCommand;
import com.github.bilak.usercreator.data.repository.UserRepository;

public class UserCommandHandlerImpl implements UserCommandHandler {

  private final Queue<Command> queue;
  private final UserRepository userRepository;
  private boolean stop;

  public UserCommandHandlerImpl(Queue<Command> queue, UserRepository userRepository) {
    this.queue = queue;
    this.userRepository = userRepository;
  }

  @Override
  public void run() {
    while (!stop) {
      while (queue.isEmpty()) {
        synchronized (queue) {
          try {
            queue.wait();
          } catch (InterruptedException e) {
            throw new RuntimeException("Handler interrupted", e);
          }
        }
      }
      synchronized (queue) {
        final Command command = queue.remove();
        handleCommand(command);
      }
    }
  }

  @Override
  public void handleCommand(Command command) {
    if (command instanceof CreateUserCommand) {
      createUser((CreateUserCommand) command);
    } else if (command instanceof DeleteUsersCommand) {
      deleteUsers();
    } else if (command instanceof PrintUsersCommand) {
      printUsers();
    }
  }

  @Override
  public void stop() {
    this.stop = true;
  }

  void createUser(CreateUserCommand command) {
    userRepository.addUser(command.getId(), command.getGroupId(), command.getName());
  }

  void deleteUsers() {
    userRepository.deleteAll();
  }

  void printUsers() {
    final Collection<UserEntity> users = userRepository.findAll();
    if (users.isEmpty()) {
      System.out.println("No user found");
    } else {
      users.forEach(System.out::println);
    }
  }
}
