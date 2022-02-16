package com.waytoodanny.demo.vavr.service;

import com.waytoodanny.demo.vavr.domain.QuoteCandidate;
import io.vavr.control.Try;

import java.util.Set;

public interface QuoteCandidateSource {

  Try<QuoteCandidate> randomQuote();


  Set<QuoteCandidate> randomQuotes(int desiredNumber);
}
