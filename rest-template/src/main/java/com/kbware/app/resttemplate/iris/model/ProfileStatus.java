package com.kbware.app.resttemplate.iris.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProfileStatus {
  FREE(0),
  ALLOCATED(1),
  BOUND(2),
  RELEASED(3),
  DOWNLOADED(4),
  INSTALLED(5),
  DELETED(6),
  BOUNDANDRELEASED(7),
  DOWNLOADERROR(8);

  private int status;

  ProfileStatus(int status) {
    this.status = status;
  }

  /** Enum from text value. */
  @JsonCreator
  public static ProfileStatus fromValue(int status) {
    for (ProfileStatus b : ProfileStatus.values()) {
      if (b.status == status) {
        return b;
      }
    }
    return null;
  }

  public int getStatus() {
    return status;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(status);
  }
}
