package com.github.bilak.usercreator.service;

/**
 * Producer of user commands.
 */
public interface UserProducer extends Runnable {

  /**
   * Adds users.
   *
   * @param id      user identifier
   * @param groupId user group
   * @param name    username
   */
  void add(int id, String groupId, String name);

  /**
   * Prints all users.
   */
  void printAll();

  /**
   * Deletes all users.
   */
  void deleteAll();

  /**
   * Stops the producer.
   */
  void stop();

}
