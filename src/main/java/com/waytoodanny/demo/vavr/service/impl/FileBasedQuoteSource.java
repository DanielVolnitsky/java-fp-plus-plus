package com.waytoodanny.demo.vavr.service.impl;

import com.waytoodanny.demo.vavr.domain.Quote;
import com.waytoodanny.demo.vavr.service.QuoteSource;
import io.vavr.control.Try;
import lombok.Value;

import java.nio.file.Path;
import java.util.Set;

@Value
public class FileBasedQuoteSource implements QuoteSource {

  Path sourceFile;

  @Override
  public Try<Quote> randomQuote() {
    return null;
  }

  @Override
  public Set<Quote> randomQuotes(int amount) {
    return null;
  }
}
