package com.waytoodanny.demo.vavr.service;

import com.waytoodanny.demo.vavr.domain.Quote;
import com.waytoodanny.demo.vavr.domain.QuoteProcessingFailure;
import io.vavr.control.Either;

public interface QuoteSource {

  Either<QuoteProcessingFailure, Quote> randomQuote();
}
