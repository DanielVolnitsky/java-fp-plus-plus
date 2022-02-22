package com.waytoodanny.demo.vavr.service.impl;

import com.waytoodanny.demo.vavr.domain.QuoteCandidate;
import com.waytoodanny.demo.vavr.service.QuoteCandidateSource;
import io.vavr.Lazy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class InMemoryQuoteSource implements QuoteCandidateSource {

  private static final Lazy<Set<QuoteCandidate>> PREDEFINED_QUOTES =
      Lazy.of(() ->
          Set.of(
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
          )
      );

  @Override
  public QuoteCandidate randomQuote() {
    log.info("Are predefined quotes initialized? " + PREDEFINED_QUOTES.isEvaluated());
    Set<QuoteCandidate> quotes = PREDEFINED_QUOTES.get();
    return quotes.stream()
        .skip((int) (quotes.size() * Math.random()))
        .findFirst()
        .get();
  }
}
