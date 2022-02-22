package com.waytoodanny.demo.vavr.controller;

import com.waytoodanny.demo.vavr.service.QuoteSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class RandomQuoteController {

  private final QuoteSource quoteSource;

  @GetMapping("/api/quote/random")
  public ResponseEntity<?> randomQuote() {
    log.info("Received 'GET random quote request'");
    return quoteSource.randomQuote()
        .fold(
            ResponseEntity::ok,
            ResponseEntity::ok
        );
  }
}
