package utils;


import com.github.javafaker.Faker;
import entities.RegistrationInfo;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class DataGenerator {
    @UtilityClass
    public static class Registration {
        public static RegistrationInfo generationInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationInfo(faker.address().city(), faker.name().lastName() + " " + faker.name().firstName(), faker.phoneNumber().phoneNumber().replaceAll("[^0-9+]", ""));
        }
    }

    @UtilityClass
    public static class NewDate {
        public static String newDatePlusFormat(int plusDay, String dateFormat) {
            return LocalDate.now().plusDays(plusDay).format(DateTimeFormatter.ofPattern(dateFormat));
        }
    }
}
