package com.github.bilak.usercreator.data.model;

import java.util.Objects;

public class CreateUserCommand implements Command {

  private int id;
  private String groupId;
  private String name;

  public CreateUserCommand(int id, String groupId, String name) {
    this.id = id;
    this.groupId = groupId;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    CreateUserCommand that = (CreateUserCommand) o;
    return id == that.id && Objects.equals(groupId, that.groupId) && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, groupId, name);
  }
}
