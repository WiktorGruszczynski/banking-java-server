package com.bank.server.tools;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;



public class ExpirationGenerator {
    public static Date getDateIn15Minutes() {
        // Get the current time
        LocalDateTime now = LocalDateTime.now();

        // Add 15 minutes to the current time
        LocalDateTime later = now.plusMinutes(15);

        // Convert LocalDateTime to Date
        return Date.from(later.atZone(ZoneId.systemDefault()).toInstant());
    }

     public static Date getDateIn60Minutes() {
        // Get the current time
        LocalDateTime now = LocalDateTime.now();

        // Add 60 minutes to the current time
        LocalDateTime later = now.plusMinutes(60);

        // Convert LocalDateTime to Date
        return Date.from(later.atZone(ZoneId.systemDefault()).toInstant());
    }
}
