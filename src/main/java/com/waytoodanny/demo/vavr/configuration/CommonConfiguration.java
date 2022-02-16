package com.waytoodanny.demo.vavr.configuration;

import com.waytoodanny.demo.vavr.util.ApplicationResources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
@Slf4j
public class CommonConfiguration {

  public Path predefinedCsvQuoteFile() throws Throwable {
    return ApplicationResources.fileOfRelativePath("./source.csv")
        .onFailure(ex -> log.error("Failed to locate predefined quotes file", ex))
        .getOrElseThrow(ex -> ex);
  }
}
