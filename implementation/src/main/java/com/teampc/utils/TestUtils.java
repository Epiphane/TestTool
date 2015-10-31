package com.teampc.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TestUtils {

   public static Date localDateToDate(LocalDate localDate) {
      return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
   }
}
