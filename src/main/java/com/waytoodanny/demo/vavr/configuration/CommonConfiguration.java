package com.waytoodanny.demo.vavr.configuration;

import com.waytoodanny.demo.vavr.service.QuoteSource;
import com.waytoodanny.demo.vavr.service.impl.FileBasedQuoteSource;
import com.waytoodanny.demo.vavr.util.ApplicationResources;

import java.nio.file.Path;

public class CommonConfiguration {

  public QuoteSource fileBasedQuoteSource() {
    return new FileBasedQuoteSource(
        applicationResourcesCsvSourceFile()
    );
  }

  public Path applicationResourcesCsvSourceFile() {
    return ApplicationResources.fileOfRelativePath("./source.csv")
        .onFailure(ex -> System.err.println(ex))
        .getOrElseThrow(ex -> new RuntimeException(ex));
  }
}
