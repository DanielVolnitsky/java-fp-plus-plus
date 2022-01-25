package com.waytoodanny.demo.vavr.service.impl;

import com.waytoodanny.demo.vavr.domain.Quote;
import com.waytoodanny.demo.vavr.service.QuoteValidation;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

import static java.util.Collections.singleton;

public class StaticQuoteValidation implements QuoteValidation {

  @Override
  public Validation<Seq<Exception>, Quote> validate(Quote quote) {
    Validation<Exception, Quote> v1 = lexiconValidated(quote);
    Validation<Exception, Quote> v2 = emptinessValidated(quote);
    Validation<Exception, Quote> v3 = wordCountValidated(quote);

    return emptinessValidated(quote)
        .combine(wordCountValidated(quote))
        .combine(lexiconValidated(quote))
        .ap((x, y, z) -> x);
  }

  private Validation<Exception, Quote> lexiconValidated(Quote quote) {

    return null;
  }

  private Validation<Exception, Quote> emptinessValidated(Quote quote) {

    return null;
  }

  private Validation<Exception, Quote> wordCountValidated(Quote quote) {

    return null;
  }
}
