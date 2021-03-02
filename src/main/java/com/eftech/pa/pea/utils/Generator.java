package com.eftech.pa.pea.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.UUID;

public class Generator {

    /**
     * Generate a random and unique uuid
     * @return generated uuid
     */
    public static String generateEfId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generate local date by parsing given date string
     * @param dateStr the date string to parse
     * @return local date
     */
    public static LocalDate generateLocalDate(String dateStr) {
        if (dateStr == null || dateStr.length() != 10) {
            return null;
        }
        String[] sections = dateStr.split("-");
        if (sections.length != 3 || sections[0].length() != 4 || sections[1].length() != 2 || sections[2].length() != 2) {
            return null;
        }
        try {
            int year = Integer.valueOf(sections[0]);
            int month = Integer.valueOf(sections[1]);
            int day = Integer.valueOf(sections[2]);
            return LocalDate.of(year, month, day);
        } catch (NumberFormatException number) {
            return null;
        } catch (DateTimeException dateTimeException) {
            return null;
        }
    }
}
