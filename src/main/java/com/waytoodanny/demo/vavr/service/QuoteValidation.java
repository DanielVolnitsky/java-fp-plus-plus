package com.waytoodanny.demo.vavr.service;

import com.waytoodanny.demo.vavr.domain.Quote;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

public interface QuoteValidation {

  Validation<Seq<Exception>, Quote> validate(Quote quote);
}
