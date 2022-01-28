package com.waytoodanny.demo.vavr.domain.exception;

import com.waytoodanny.demo.vavr.util.Foldable;

public class QuoteValidationException
    extends RuntimeException
    implements Foldable<QuoteValidationException> {

  public QuoteValidationException(String message) {
    super(message);
  }

  @Override
  public QuoteValidationException fold(QuoteValidationException other) {
    return new QuoteValidationException(
        this.getMessage() + "; " + other.getMessage()
    );
  }
}
