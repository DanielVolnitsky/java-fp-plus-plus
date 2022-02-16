package com.waytoodanny.demo.vavr.service.impl;

import com.waytoodanny.demo.vavr.domain.Quote;
import com.waytoodanny.demo.vavr.domain.exception.QuoteValidationException;
import com.waytoodanny.demo.vavr.service.QuoteCandidateSource;
import com.waytoodanny.demo.vavr.service.QuoteSource;
import com.waytoodanny.demo.vavr.service.QuoteValidation;
import io.vavr.collection.Seq;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Component
@AllArgsConstructor
@Slf4j
public class QuoteSourceImpl implements QuoteSource {

  private final QuoteCandidateSource quoteCandidateSource;
  private final QuoteValidation quoteValidation;

  //TODO add retry
  @Override
  public Try<Quote> randomQuote() {
    return quoteCandidateSource.randomQuote()
        .map(quoteValidation::validationResult)
        .flatMap(this::asQuoteMaybe);
  }

  //TODO handle retries
  @Override
  public Set<Quote> randomQuotes(int desiredNumber) {
    return quoteCandidateSource.randomQuotes(desiredNumber)
        .stream()
        .map(quoteValidation::validationResult)
        .map(this::asQuoteMaybe)
        .filter(Try::isSuccess)
        .map(Try::get)
        .collect(toSet());
  }

  private Try<Quote> asQuoteMaybe(
      Validation<Seq<QuoteValidationException>, Quote> validationResult
  ) {
    return validationResult.fold(
        exceptions -> {
          var foldedException = exceptions.reduce(QuoteValidationException::fold);
          log.warn("Quote candidate validation failed", foldedException);
          return Try.failure(foldedException);
        },
        Try::success
    );
  }
}
