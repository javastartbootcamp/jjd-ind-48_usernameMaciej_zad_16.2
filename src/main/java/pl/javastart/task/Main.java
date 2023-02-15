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
        //mam nadzieje ze kod jest teraz czytelny, probowalem zrobic ze sposobem na 2 patterny i dodac czas do userdatatime
        //ale jakos sie pogubilem w tym i mysle ze nie ma co tracic czasu tylko cisnac dalej z tematami
        System.out.println("Podaj datę:");
        String userDataTime = scanner.nextLine();

        List<String> patterns = Arrays.asList("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "dd.MM.yyyy HH:mm:ss");
        for (String pattern : patterns) {
            try {
                mainDateTimeFormatter(pattern, userDataTime);
            } catch (DateTimeParseException e) {
                //ignore
            }
        }
    }

    private static void mainDateTimeFormatter(String pattern, String userDataTime) {
        String patternWithoutTime = "yyyy-MM-dd";
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern(pattern);
        TemporalAccessor temporalAccessor = ofPattern.parse(userDataTime);
        LocalDate date = LocalDate.from(temporalAccessor);
        LocalTime time = LocalTime.of(0, 0, 0);
        LocalDateTime localDateTime = LocalDateTime.of(date, time);
        ZonedDateTime userInputDateTimeZones = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());

        if (!pattern.equals(patternWithoutTime)) {
            localDateTime = LocalDateTime.from(temporalAccessor);
            userInputDateTimeZones = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
            printDateWithDifferentTimeZones(userInputDateTimeZones);
        }
        printDateWithDifferentTimeZones(userInputDateTimeZones);
    }

    private static void printDateWithDifferentTimeZones(ZonedDateTime userInputDateTimeZones) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Czas lokalny: " + userInputDateTimeZones.format(formatter));
        System.out.println("UTC: " + userInputDateTimeZones.withZoneSameInstant(ZoneId.of("UTC")).format(formatter));
        System.out.println("Londyn: " + userInputDateTimeZones.withZoneSameInstant(ZoneId.of("Europe/London")).format(formatter));
        System.out.println("Los Angeles: " + userInputDateTimeZones.withZoneSameInstant(ZoneId.of("America/Los_Angeles")).format(formatter));
        System.out.println("Sydney: " + userInputDateTimeZones.withZoneSameInstant(ZoneId.of("Australia/Sydney")).format(formatter));
    }

}
