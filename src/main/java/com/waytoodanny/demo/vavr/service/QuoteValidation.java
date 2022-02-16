package com.waytoodanny.demo.vavr.service;

import com.waytoodanny.demo.vavr.domain.Quote;
import com.waytoodanny.demo.vavr.domain.QuoteCandidate;
import com.waytoodanny.demo.vavr.domain.exception.QuoteValidationException;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

public interface QuoteValidation {

  /**
   * @return validation result: either composed {@link Quote} or validation exceptions
   * */
  Validation<Seq<QuoteValidationException>, Quote> validationResult(QuoteCandidate quoteCandidate);
}
