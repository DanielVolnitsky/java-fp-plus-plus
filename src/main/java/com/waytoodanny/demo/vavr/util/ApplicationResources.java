package com.waytoodanny.demo.vavr.util;

import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.waytoodanny.demo.vavr.util.F.suppressingException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class ApplicationResources {

  public static Try<Path> fileOfRelativePath(String relativePath) {
    return Option.of(ClassLoader.getSystemResource(relativePath))
        .map(suppressingException(URL::toURI))
        .map(Paths::get)
        .fold(
            () -> Try.failure(new RuntimeException("Couldn't locate file of relative path " + relativePath)),
            Try::success
        );
  }
}
