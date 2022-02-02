package com.waytoodanny.demo.vavr.service;

import com.waytoodanny.demo.vavr.domain.Quote;
import io.vavr.control.Try;

import java.util.Set;

public interface QuoteSource {

  Try<Quote> randomQuote();


  Set<Quote> randomQuotes(int desiredNumber);
}
