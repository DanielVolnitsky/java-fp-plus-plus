package com.waytoodanny.demo.vavr.util;

public interface Foldable<T> {

  T fold(T other);
}
