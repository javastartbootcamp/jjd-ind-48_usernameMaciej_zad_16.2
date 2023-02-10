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
                if (pattern.equals(patternWithoutTime)) {
                    LocalDate date = LocalDate.from(temporalAccessor);
                    LocalTime time = LocalTime.of(0, 0, 0);
                    System.out.println("Czas lokalny: " + date + " " + time);
                    System.out.println("UTC: " + date + " " + time.minusHours(1));
                    System.out.println("Londyn: " + date + " " + time.minusHours(1));
                    System.out.println("Los Angeles: " + date + " " + time.minusHours(9));
                    System.out.println("Sydney: " + date + " " + time.plusHours(10));
                } else {
                    LocalDateTime dateTime = LocalDateTime.from(temporalAccessor);
                    System.out.println("Czas lokalny: " + dateTime.format(formatter));
                    System.out.println("UTC: " + dateTime.minusHours(1).format(formatter));
                    System.out.println("Londyn: " + dateTime.minusHours(1).format(formatter));
                    System.out.println("Los Angeles: " + dateTime.minusHours(9).format(formatter));
                    System.out.println("Sydney: " + dateTime.plusHours(10).format(formatter));
                }
            } catch (DateTimeParseException e) {
                //ignore
            }
        }
    }

}
