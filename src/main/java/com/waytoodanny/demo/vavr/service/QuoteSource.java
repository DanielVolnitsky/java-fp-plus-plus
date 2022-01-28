package com.waytoodanny.demo.vavr.service;

import com.waytoodanny.demo.vavr.domain.Quote;
import io.vavr.control.Try;

public interface QuoteSource {

  Try<Quote> randomQuote();
}
