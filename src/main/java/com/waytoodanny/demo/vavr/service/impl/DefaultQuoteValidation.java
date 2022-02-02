package com.waytoodanny.demo.vavr.service.impl;

import com.waytoodanny.demo.vavr.domain.Quote;
import com.waytoodanny.demo.vavr.domain.QuoteCandidate;
import com.waytoodanny.demo.vavr.domain.exception.QuoteValidationException;
import com.waytoodanny.demo.vavr.service.QuoteValidation;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class DefaultQuoteValidation implements QuoteValidation {

  @Override
  public Validation<Seq<QuoteValidationException>, Quote> validate(QuoteCandidate quoteCandidate) {
    return textValidated(quoteCandidate.getText())
        .combine(supportedLanguageValidated(quoteCandidate.getLanguage()))
        .combine(authorValidated(quoteCandidate.getAuthor()))
        .combine(supportedQuoteSourceValidated(quoteCandidate.getSource()))
        .ap((text, lang, author, source) ->
            Quote.builder()
                .text(text)
                .supportedLanguage(lang)
                .author(author)
                .source(source)
                .build());
  }

  private Validation<QuoteValidationException, String> textValidated(
      String textCandidate
  ) {
    return validated(
        textCandidate,
        ObjectUtils::isNotEmpty,
        Function.identity(),
        text -> "Text is empty"
    );
  }

  private Validation<QuoteValidationException, String> authorValidated(
      String authorCandidate
  ) {
    return validated(
        authorCandidate,
        ObjectUtils::isNotEmpty,
        Function.identity(),
        text -> "Author is not specified"
    );
  }

  private Validation<QuoteValidationException, Quote.SupportedLanguage> supportedLanguageValidated(
      String languageCandidate
  ) {
    return validated(
        languageCandidate,
        language -> ObjectUtils.isNotEmpty(language) && Quote.SupportedLanguage.isSupported(language),
        Quote.SupportedLanguage::valueOf,
        language -> "Language is invalid or unsupported - " + language
    );
  }

  private static Validation<QuoteValidationException, Quote.Source> supportedQuoteSourceValidated(
      String sourceCandidate
  ) {
    return validated(
        sourceCandidate,
        source -> ObjectUtils.isNotEmpty(source) && Quote.Source.isSupported(source),
        Quote.Source::valueOf,
        source -> "Source is invalid or unsupported - " + source
    );
  }

  private static <I, O> Validation<QuoteValidationException, O> validated(
      I input,
      Predicate<I> validationPredicate,
      Function<I, O> mappingFunction,
      Function<I, String> exceptionMessageFunction
  ) {
    return Option.ofOptional(
        Optional.ofNullable(input)
            .filter(validationPredicate)
            .map(mappingFunction)
    )
        .fold(
            () -> Validation.invalid(new QuoteValidationException(exceptionMessageFunction.apply(input))),
            Validation::valid
        );
  }
}