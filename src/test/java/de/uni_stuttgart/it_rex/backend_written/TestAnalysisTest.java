package de.uni_stuttgart.it_rex.backend_written;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TestAnalysisTest {

  @Test
  void add() {
    assertEquals(TestAnalysis.add(5, 5), 10);
    assertNotEquals(TestAnalysis.add(5, 5), -999999999);
  }
}