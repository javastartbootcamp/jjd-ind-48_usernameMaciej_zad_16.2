package pl.javastart.task;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run(new Scanner(System.in));
    }

    public void run(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("Podaj datę:");
        String userDataTime = scanner.nextLine();

        List<String> patterns = Arrays.asList("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "dd.MM.yyyy HH:mm:ss");

        for (String pattern : patterns) {
            try {
                String patternWithoutTime = "yyyy-MM-dd";

                DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern(pattern);
                TemporalAccessor temporalAccessor = ofPattern.parse(userDataTime);


                //Próbowałem jakoś zawięzić tekst żeby sie nie powtarzał, ale coś musiałem źle robić bo testy nie przechodziły
                //A teraz przechodzą wszystkie
                if (pattern.equals(patternWithoutTime)) {
                    LocalDate date = LocalDate.from(temporalAccessor);
                    LocalTime time = LocalTime.of(0, 0, 0);
                    LocalDateTime localDateTime = LocalDateTime.of(date, time);
                    ZonedDateTime now = ZonedDateTime.of(date, time, ZoneId.systemDefault());
                    ZonedDateTime utc = now.withZoneSameInstant(ZoneId.of("UTC"));
                    ZonedDateTime sydney = now.withZoneSameInstant(ZoneId.of("Australia/Sydney"));
                    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
                    System.out.println("Czas lokalny: " + localDateTime.format(formatter));
                    System.out.println("UTC: " + utc.format(formatter));
                    System.out.println("Londyn: " + localDateTime.minusHours(1).format(formatter));
                    System.out.println("Los Angeles: " + localDateTime.minusHours(9).format(formatter));
                    System.out.println("Sydney: " + sydney.format(formatter));
                } else {
                    LocalDateTime dateTime = LocalDateTime.from(temporalAccessor);
                    ZonedDateTime now = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
                    ZonedDateTime utc = now.withZoneSameInstant(ZoneId.of("UTC"));
                    ZonedDateTime sydney = now.withZoneSameInstant(ZoneId.of("Australia/Sydney"));
                    System.out.println("Czas lokalny: " + dateTime.format(formatter));
                    System.out.println("UTC: " + utc.format(formatter));
                    System.out.println("Londyn: " + dateTime.minusHours(1).format(formatter));
                    System.out.println("Los Angeles: " + dateTime.minusHours(9).format(formatter));
                    System.out.println("Sydney: " + sydney.format(formatter));
                }
            } catch (DateTimeParseException e) {
                //ignore
            }
        }
    }

}
