package com.waytoodanny.demo.vavr.service.impl;

import com.waytoodanny.demo.vavr.domain.Quote;
import com.waytoodanny.demo.vavr.domain.exception.QuoteValidationException;
import com.waytoodanny.demo.vavr.service.QuoteValidation;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Validation;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

public class StaticQuoteValidation implements QuoteValidation {

  @Override
  public Validation<Seq<QuoteValidationException>, Quote> validate(Quote quote) {
    return emptinessValidated(quote)
        .combine(wordCountValidated(quote))
        .combine(lexiconValidated(quote))
        .ap((x, y, z) -> x);
  }

  //TODO
  private Validation<QuoteValidationException, Quote> emptinessValidated(Quote quote) {
    return Option.of(quote)
        .filter(q -> isNotEmpty(q.getText()))
        .fold(
            () -> Validation.invalid(new QuoteValidationException("Quote " + quote + " is empty")),
            Validation::valid
        );
  }

  private Validation<QuoteValidationException, Quote> lexiconValidated(Quote quote) {
    return null;
  }

  private Validation<QuoteValidationException, Quote> wordCountValidated(Quote quote) {

    return null;
  }
}
