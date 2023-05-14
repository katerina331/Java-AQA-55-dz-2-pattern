package utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateDay {
    public String NewDatePlusFormat(int plusDay, String dateFormat) {
        return LocalDate.now().plusDays(plusDay).format(DateTimeFormatter.ofPattern(dateFormat));
    }
}
