package com.kbware.app.feign.iris.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProfileReturnCode {
  OK(0),
  TIMEOUT(1),
  UNKNOWN_ICCID(2),
  UNKNOWN_TECH_ERROR(3),
  NOT_AUTHORIZED(4);

  private int returnCode;

  ProfileReturnCode(int returnCode) {
    this.returnCode = returnCode;
  }

  /** Enum from text value. */
  @JsonCreator
  public static ProfileReturnCode fromValue(int returnCode) {
    for (ProfileReturnCode b : ProfileReturnCode.values()) {
      if (b.returnCode == returnCode) {
        return b;
      }
    }
    return null;
  }

  public int getReturnCode() {
    return returnCode;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(returnCode);
  }
}
