package com.waytoodanny.demo.vavr.service.decorator;

import com.waytoodanny.demo.vavr.domain.Quote;
import com.waytoodanny.demo.vavr.service.QuoteSource;
import com.waytoodanny.demo.vavr.service.QuoteValidation;
import io.vavr.collection.Seq;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import lombok.Value;

import java.util.Set;

@Value
public class ValidatingQuoteSource implements QuoteSource {

  QuoteSource delegate;
  QuoteValidation validation;

  @Override
  public Try<Quote> randomQuote() {
    return delegate.randomQuote()
        .onFailure(ex -> System.err.println("Failed to get a random quote: " + ex))
        .map(validation::validate)
        .flatMap(this::handleValidationResult);
  }

  private Try<Quote> handleValidationResult(Validation<Seq<Exception>, Quote> validationResult) {
    validationResult
        .getError()
        .forEach(ex -> System.err.println());

    return null;
  }

  @Override
  public Set<Quote> randomQuotes(int amount) {
    Validation<Seq<Exception>, Seq<Quote>> validationResults = Validation.traverse(
        delegate.randomQuotes(amount),
        validation::validate
    );

    return null;
  }
}
