package com.waytoodanny.demo.vavr.service.impl;

import com.waytoodanny.demo.vavr.domain.Quote;
import com.waytoodanny.demo.vavr.service.QuoteSource;
import io.vavr.control.Try;
import lombok.Value;

import java.util.Set;

@Value
public class InMemoryQuoteSource implements QuoteSource {

  //TODO fill quotes
  private static final Set<Quote> PREDEFINED_QUOTES = Set.of(
      new Quote(""),
      new Quote(""),
      new Quote(""),
      new Quote(""),
      new Quote("")
  );

  @Override
  public Try<Quote> randomQuote() {
    return PREDEFINED_QUOTES.stream()
        .findAny()
        .map(Try::success)
        .orElseGet(() -> Try.failure(new IllegalStateException()));
  }
}
