package com.waytoodanny.demo.vavr.service.impl;

import com.waytoodanny.demo.vavr.domain.Quote;
import com.waytoodanny.demo.vavr.domain.QuoteCandidate;
import com.waytoodanny.demo.vavr.domain.QuoteProcessingFailure;
import com.waytoodanny.demo.vavr.domain.exception.QuoteValidationException;
import com.waytoodanny.demo.vavr.service.QuoteCandidateSource;
import com.waytoodanny.demo.vavr.service.QuoteSource;
import com.waytoodanny.demo.vavr.service.QuoteValidation;
import io.vavr.Tuple2;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class QuoteSourceImpl implements QuoteSource {

  private final QuoteCandidateSource quoteCandidateSource;
  private final QuoteValidation quoteValidation;

  @Override
  public Either<QuoteProcessingFailure, Quote> randomQuote() {
    return Try.ofSupplier(quoteCandidateSource::randomQuote)
        .map(this::quoteCandidateWithValidationResult)
        .map(this::quoteCandidateProcessingResult)
        .fold(
            ex -> Either.left(QuoteProcessingFailure.ofThrowable(ex)),
            processingResult -> processingResult
        );
  }

  private Tuple2<QuoteCandidate, Validation<Seq<QuoteValidationException>, Quote>>
  quoteCandidateWithValidationResult(QuoteCandidate quoteCandidate) {
    return new Tuple2<>(
        quoteCandidate,
        quoteValidation.validationResult(quoteCandidate)
    );
  }

  private Either<QuoteProcessingFailure, Quote> quoteCandidateProcessingResult(
      Tuple2<QuoteCandidate, Validation<Seq<QuoteValidationException>, Quote>> candidateWithValidationResult
  ) {
    var quoteCandidate = candidateWithValidationResult._1;
    var validationResult = candidateWithValidationResult._2;
    return validationResult.fold(
        validationExceptions -> Either.left(QuoteProcessingFailure.of(quoteCandidate, validationExceptions)),
        Either::right
    );
  }
}