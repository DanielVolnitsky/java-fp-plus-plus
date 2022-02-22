package com.waytoodanny.demo.vavr.service;

import com.waytoodanny.demo.vavr.domain.QuoteCandidate;

/**
 * Demonstrative interface that we don't know what to expect
 */
public interface QuoteCandidateSource {

  QuoteCandidate randomQuote();
}
