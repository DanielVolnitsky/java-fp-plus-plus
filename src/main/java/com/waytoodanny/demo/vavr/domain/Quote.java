package com.waytoodanny.demo.vavr.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;
import java.util.stream.Stream;

@Value
@Builder
public class Quote {
  String text;
  SupportedLanguage supportedLanguage;
  String author;
  Source source;

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
}
