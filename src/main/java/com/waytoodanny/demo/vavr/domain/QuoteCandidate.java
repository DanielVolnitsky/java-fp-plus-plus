package com.waytoodanny.demo.vavr.domain;

import lombok.Value;

@Value
public class QuoteCandidate {
  String text;
  String language;
  String author;
  String source;
}
