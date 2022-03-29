package com.github.bilak.usercreator.service;

import java.util.Queue;

import com.github.bilak.usercreator.data.model.Command;
import com.github.bilak.usercreator.data.model.CreateUserCommand;
import com.github.bilak.usercreator.data.model.DeleteUsersCommand;
import com.github.bilak.usercreator.data.model.PrintUsersCommand;

public class UserProducerImpl implements UserProducer {

  private final Queue<Command> queue;
  private boolean stop;

  public UserProducerImpl(Queue<Command> queue) {
    this.queue = queue;
  }

  @Override
  public void add(int id, String groupId, String name) {
    addCommandToQueue(new CreateUserCommand(id, groupId, name));
  }

  @Override
  public void printAll() {
    addCommandToQueue(new PrintUsersCommand());
  }

  @Override
  public void deleteAll() {
    addCommandToQueue(new DeleteUsersCommand());
  }

  @Override
  public void stop() {
    this.stop = true;
  }

  @Override
  public void run() {
    while (!stop) {
      synchronized (queue) {
        try {
          queue.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException("Producer interrupted", e);
        }
      }
    }
  }

  private void addCommandToQueue(Command command) {
    synchronized (queue) {
      queue.add(command);
      queue.notifyAll();
    }
  }
}
