package com.waytoodanny.demo.vavr.domain;

import java.util.Optional;
import java.util.stream.Stream;

public enum SupportedLanguage {
  UKRAINIAN, ENGLISH;

  public static Optional<SupportedLanguage> ofRawValue(String rawValue) {
    return Stream.of(SupportedLanguage.values())
        .filter(value -> value.toString().equalsIgnoreCase(rawValue))
        .findAny();
  }

  public static boolean isSupported(String rawValue) {
    return ofRawValue(rawValue).isPresent();
  }
}
