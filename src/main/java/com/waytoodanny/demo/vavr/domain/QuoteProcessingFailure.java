package com.waytoodanny.demo.vavr.domain;

import com.waytoodanny.demo.vavr.domain.exception.QuoteValidationException;
import io.vavr.collection.Seq;

public record QuoteProcessingFailure(String reason, QuoteCandidate quoteCandidate) {

  public static QuoteProcessingFailure ofThrowable(Throwable throwable) {
    return new QuoteProcessingFailure(throwable.getMessage(), null);
  }

  public static QuoteProcessingFailure of(QuoteCandidate candidate,
                                          Seq<QuoteValidationException> exceptions) {
    QuoteValidationException reducedException = exceptions.reduce(QuoteValidationException::fold);
    return new QuoteProcessingFailure(reducedException.getMessage(), candidate);
  }
}
