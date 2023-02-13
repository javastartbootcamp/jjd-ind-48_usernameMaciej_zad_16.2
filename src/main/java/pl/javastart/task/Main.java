package pl.javastart.task;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

                mainDateTimeFormatter(formatter, pattern, patternWithoutTime, temporalAccessor);
            } catch (DateTimeParseException e) {
                //ignore
            }
        }
    }

    private static void mainDateTimeFormatter(DateTimeFormatter formatter, String pattern, String patternWithoutTime, TemporalAccessor temporalAccessor) {
        LocalDate date = LocalDate.from(temporalAccessor);
        LocalTime time = LocalTime.of(0, 0, 0);
        LocalDateTime localDateTime = LocalDateTime.of(date, time);
        ZonedDateTime now = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());

        if (pattern.equals(patternWithoutTime)) {
            formattedDateTime(formatter, localDateTime, now);
        } else {
            localDateTime = LocalDateTime.from(temporalAccessor);
            now = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
            formattedDateTime(formatter, localDateTime, now);
        }
    }

    private static void formattedDateTime(DateTimeFormatter formatter, LocalDateTime localDateTime, ZonedDateTime now) {
        System.out.println("Czas lokalny: " + localDateTime.format(formatter));
        System.out.println("UTC: " + now.withZoneSameInstant(ZoneId.of("UTC")).format(formatter));
        System.out.println("Londyn: " + now.withZoneSameInstant(ZoneId.of("Europe/London")).format(formatter));
        System.out.println("Los Angeles: " + now.withZoneSameInstant(ZoneId.of("America/Los_Angeles")).format(formatter));
        System.out.println("Sydney: " + now.withZoneSameInstant(ZoneId.of("Australia/Sydney")).format(formatter));
    }

}
