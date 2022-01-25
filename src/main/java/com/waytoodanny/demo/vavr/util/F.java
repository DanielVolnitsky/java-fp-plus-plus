package com.waytoodanny.demo.vavr.util;

import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class F {

  public static <I, O> Function<I, O> suppressingException(ThrowingFunction<I, O> function) {
    return x -> {
      try {
        return function.apply(x);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
  }

  public static <I, O> Function<I, Try<O>> preservingException(ThrowingFunction<I, O> function) {
    return x -> {
      try {
        O result = function.apply(x);
        return Try.ofSupplier(() -> result);
      } catch (Exception e) {
        return Try.failure(e);
      }
    };
  }

  @FunctionalInterface
  public interface ThrowingFunction<I, O> {
    O apply(I i) throws Exception;
  }
}
