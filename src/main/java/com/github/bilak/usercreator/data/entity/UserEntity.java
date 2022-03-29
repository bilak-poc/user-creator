package com.github.bilak.usercreator.data.entity;

import java.util.Objects;
import java.util.StringJoiner;

public class UserEntity {

  private Integer id;
  private String guid;
  private String name;

  public UserEntity() {
  }

  public UserEntity(Integer id, String guid, String name) {
    this.id = id;
    this.guid = guid;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
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
    UserEntity that = (UserEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(guid, that.guid) && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, guid, name);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", UserEntity.class.getSimpleName() + "[", "]").add("id=" + id)
      .add("guid='" + guid + "'")
      .add("name='" + name + "'")
      .toString();
  }
}
