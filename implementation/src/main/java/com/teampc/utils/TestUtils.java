package com.teampc.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class TestUtils {

   public static Date localDateToDate(LocalDateTime localDate) {
      return Date.from(localDate.toInstant(ZoneOffset.UTC));
   }
}
