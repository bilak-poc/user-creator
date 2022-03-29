package com.github.bilak.usercreator.data.repository;

import java.util.Collection;

import com.github.bilak.usercreator.data.entity.UserEntity;

/**
 * Repository for operations with {@link UserEntity}.
 */
public interface UserRepository {

  /**
   * Creates a new user.
   * @param id user identifier
   * @param guid group identifier
   * @param name username
   */
  void addUser(int id, String guid, String name);

  /**
   * Retrieves all users.
   * @return a collection of {@link UserEntity} or empty collection
   */
  Collection<UserEntity> findAll();

  /**
   * Removes all users.
   */
  void deleteAll();
}
