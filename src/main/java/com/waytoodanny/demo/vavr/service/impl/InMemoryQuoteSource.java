package com.waytoodanny.demo.vavr.service.impl;

import com.waytoodanny.demo.vavr.domain.Quote;
import com.waytoodanny.demo.vavr.service.QuoteSource;
import io.vavr.control.Try;
import lombok.Value;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Value
public class InMemoryQuoteSource implements QuoteSource {

  private static final Set<Quote> PREDEFINED_QUOTES = Set.of(
      new Quote(
          "The greatest glory in living lies not in never falling, but in rising every time we fall.",
          "ENGLISH",
          "Nelson Mandela",
          Quote.Source.PERSON
      ),
      new Quote(
          "The way to get started is to quit talking and begin doing.",
          "ENGLISH",
          "Walt Disney",
          Quote.Source.PERSON
      ),
      new Quote(
          "Wenn Sie Ihre Ziele lächerlich hoch setzen und es ein Misserfolg ist, werden Sie vor dem Erfolg aller anderen scheitern",
          "DEUTSCH",
          "Eleanor Roosevelt",
          Quote.Source.PERSON
      ),
      new Quote(
          "Незрозуміло що, незрозуміло від кого",
          "DEUTSCH",
          null,
          null
      )
  );

  @Override
  public Try<Quote> randomQuote() {
    return PREDEFINED_QUOTES.stream()
        .findAny()
        .map(Try::success)
        .orElseGet(() -> Try.failure(new IllegalStateException()));
  }

  @Override
  public Set<Quote> randomQuotes(int desiredNumber) {
    return PREDEFINED_QUOTES.stream()
        .limit(desiredNumber)
        .collect(toSet());
  }
}
