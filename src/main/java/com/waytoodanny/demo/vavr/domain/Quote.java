package com.waytoodanny.demo.vavr.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Quote {
  String text;
  SupportedLanguage supportedLanguage;
  String author;
  Source source;
}
