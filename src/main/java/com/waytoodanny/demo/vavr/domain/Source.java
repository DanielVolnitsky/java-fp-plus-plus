package com.waytoodanny.demo.vavr.domain;

import java.util.Optional;
import java.util.stream.Stream;

public enum Source {
  PERSON, BOOK;

  public static Optional<Source> ofRawValue(String rawValue) {
    return Stream.of(Source.values())
        .filter(value -> value.toString().equalsIgnoreCase(rawValue))
        .findAny();
  }

  public static boolean isSupported(String rawValue) {
    return ofRawValue(rawValue).isPresent();
  }
}
