package fmc.utils;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;
import java.util.UUID;

@Component
public class Utils {

    public String getMessageId() {
        return UUID.randomUUID().toString();
    }    

    public long converLocalDateTimeToLong(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    public LocalDateTime converIntToLocalDateTime(int localDateTime) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(localDateTime), TimeZone.getDefault().toZoneId());
    }
}