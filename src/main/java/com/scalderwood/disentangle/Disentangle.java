package com.scalderwood.disentangle;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class Disentangle {

  /**
   * This emulates the Spring Data JPA's findById method.
   *
   * @param repository The map that will pretend to be your repository
   * @return The item in the map if it exists otherwise whatever the Map's null handling is
   */
  public static <K, V> Function<K, Optional<V>> findById(Map<K, V> repository) {
    return id -> Optional.ofNullable(repository.get(id));
  }
}
