package com.waytoodanny.demo.vavr.service.decorator;

import com.waytoodanny.demo.vavr.domain.Quote;
import com.waytoodanny.demo.vavr.domain.exception.QuoteValidationException;
import com.waytoodanny.demo.vavr.service.QuoteSource;
import com.waytoodanny.demo.vavr.service.QuoteValidation;
import io.vavr.collection.Seq;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Value
@Slf4j
public class ValidatingQuoteSource implements QuoteSource {

  QuoteSource delegate;
  QuoteValidation validation;

  @Override
  public Try<Quote> randomQuote() {
    return delegate.randomQuote()
        .onFailure(ex -> log.error("Failed to get a random quote: " + ex))
        .map(validation::validate)
        .flatMap(this::validatedQuoteMaybe);
  }

  private Try<Quote> validatedQuoteMaybe(Validation<Seq<QuoteValidationException>, Quote> validationResult) {
    return validationResult.fold(
        exceptions -> {
          QuoteValidationException foldedException = exceptions.reduce(QuoteValidationException::fold);
          log.warn("Validation failed. Details: " + foldedException.getMessage());
          return Try.failure(foldedException);
        },
        Try::success
    );
  }
}
