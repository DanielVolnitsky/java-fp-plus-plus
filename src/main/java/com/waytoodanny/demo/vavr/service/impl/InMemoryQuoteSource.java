package com.waytoodanny.demo.vavr.service.impl;

import com.waytoodanny.demo.vavr.domain.QuoteCandidate;
import com.waytoodanny.demo.vavr.service.QuoteCandidateSource;
import io.vavr.control.Try;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Value
@Component("inMemoryQuoteSource")
public class InMemoryQuoteSource implements QuoteCandidateSource {

  private static final Set<QuoteCandidate> PREDEFINED_QUOTES = Set.of(
      new QuoteCandidate(
          "The greatest glory in living lies not in never falling, but in rising every time we fall.",
          "ENGLISH",
          "Nelson Mandela",
          "PERSON"
      ),
      new QuoteCandidate(
          "The way to get started is to quit talking and begin doing.",
          "ENGLISH",
          "Walt Disney",
          "PERSON"
      ),
      new QuoteCandidate(
          "Wenn Sie Ihre Ziele lächerlich hoch setzen und es ein Misserfolg ist, werden Sie vor dem Erfolg aller anderen scheitern",
          "DEUTSCH",
          "Eleanor Roosevelt",
          "PERSON"
      ),
      new QuoteCandidate(
          "Незрозуміло що, незрозуміло від кого",
          "DEUTSCH",
          null,
          null
      )
  );

  @Override
  public Try<QuoteCandidate> randomQuote() {
    return PREDEFINED_QUOTES.stream()
        .findAny()
        .map(Try::success)
        .orElseGet(() -> Try.failure(new IllegalStateException()));
  }

  @Override
  public Set<QuoteCandidate> randomQuotes(int desiredNumber) {
    return PREDEFINED_QUOTES.stream()
        .limit(desiredNumber)
        .collect(toSet());
  }
}
