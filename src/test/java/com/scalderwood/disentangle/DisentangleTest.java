package com.scalderwood.disentangle;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class DisentangleTest {

  @Test
  public void findIdInMap() {
    Map<String, Integer> map = new HashMap<>(2);
    map.put("hello", 5);
    map.put("cat", 3);
    assertThat(Disentangle.findById(map).apply("hello").get())
        .isEqualTo(map.get("hello"));
  }
}