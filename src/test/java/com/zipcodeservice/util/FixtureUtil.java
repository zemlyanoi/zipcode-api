package com.zipcodeservice.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import com.google.common.io.Resources;

/**
 * A set of helper method for fixture files.
 */
public class FixtureUtil {
  private FixtureUtil() { /* singleton */ }

  /**
   * Reads the given fixture file from the classpath (e. g. {@code src/test/resources})
   * and returns its contents as a UTF-8 string.
   *
   * @param filename the filename of the fixture file
   * @return the contents of {@code src/test/resources/{filename}}
   * @throws IllegalArgumentException if an I/O error occurs.
   */
  public static String fixture(String filename) {
    return fixture(filename, StandardCharsets.UTF_8);
  }

  /**
   * Reads the given fixture file from the classpath (e. g. {@code src/test/resources})
   * and returns its contents as a string.
   *
   * @param filename the filename of the fixture file
   * @param charset  the character set of {@code filename}
   * @return the contents of {@code src/test/resources/{filename}}
   * @throws IllegalArgumentException if an I/O error occurs.
   */
  private static String fixture(String filename, Charset charset) {
    try {
      return Resources.toString(Resources.getResource(filename), charset).trim();
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }
}