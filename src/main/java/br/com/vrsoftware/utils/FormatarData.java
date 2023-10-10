package br.com.vrsoftware.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatarData {

    public static String formataData(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    public static LocalDate parseData(String formattedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(formattedDate, formatter);
    }
}
