package com.teampc.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * TestUtil is a utility class with methods that may be used in various parts of the application.
 *
 * @author Jameson Li (jrli@calpoly.edu)
 */
public class TestUtils {

   /**
    * Returns a Date given a LocalDateTime
    */
   public static Date localDateToDate(LocalDateTime localDate) {
      return Date.from(localDate.toInstant(ZoneOffset.UTC));
   }
}
