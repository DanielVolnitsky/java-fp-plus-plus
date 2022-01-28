package com.waytoodanny.demo.vavr.service;

import com.waytoodanny.demo.vavr.domain.Quote;
import com.waytoodanny.demo.vavr.domain.exception.QuoteValidationException;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

public interface QuoteValidation {

  Validation<Seq<QuoteValidationException>, Quote> validate(Quote quote);
}
