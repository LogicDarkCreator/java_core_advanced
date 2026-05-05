package parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import model.StationProperty;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    public List<StationProperty> parseCsvFile(File file) throws IOException, CsvException {
        List<StationProperty> stations = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            List<String[]> rows = reader.readAll();

            if (rows.isEmpty()) {
                return stations;
            }

            // Определяем заголовки
            String[] headers = rows.get(0);

            // Определяем индексы колонок
            int nameIndex = findColumnIndex(headers, "name", "station", "название");
            int lineIndex = findColumnIndex(headers, "line", "линия");
            int dateIndex = findColumnIndex(headers, "date", "дата");
            int depthIndex = findColumnIndex(headers, "depth", "глубина");
            int connectionIndex = findColumnIndex(headers, "hasConnection", "connection", "переход");

            // Парсим данные (пропускаем заголовок)
            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);

                if (row.length <= nameIndex || row.length <= lineIndex) {
                    continue;
                }

                String name = row[nameIndex];
                String line = row[lineIndex];

                if (name == null || name.trim().isEmpty() ||
                        line == null || line.trim().isEmpty()) {
                    continue;
                }

                StationProperty station = new StationProperty(name.trim(), line.trim());

                // Парсим дату
                if (dateIndex >= 0 && dateIndex < row.length) {
                    String date = row[dateIndex];
                    if (date != null && !date.trim().isEmpty()) {
                        station.setDate(date.trim());
                    }
                }

                // Парсим глубину
                if (depthIndex >= 0 && depthIndex < row.length) {
                    String depth = row[depthIndex];
                    if (depth != null && !depth.trim().isEmpty()) {
                        try {
                            station.setDepth(Double.parseDouble(depth.trim()));
                        } catch (NumberFormatException e) {
                            // Игнорируем
                        }
                    }
                }

                // Парсим наличие перехода
                if (connectionIndex >= 0 && connectionIndex < row.length) {
                    String connection = row[connectionIndex];
                    if (connection != null && !connection.trim().isEmpty()) {
                        station.setHasConnection(
                                connection.equalsIgnoreCase("да") ||
                                        connection.equalsIgnoreCase("yes") ||
                                        connection.equalsIgnoreCase("true") ||
                                        connection.equals("1")
                        );
                    }
                }

                stations.add(station);
            }
        }

        return stations;
    }

    private int findColumnIndex(String[] headers, String... possibleNames) {
        for (int i = 0; i < headers.length; i++) {
            String header = headers[i].toLowerCase().trim();
            for (String name : possibleNames) {
                if (header.contains(name.toLowerCase())) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void printCsvContent(File file) {
        System.out.println("\n--- ПАРСИНГ CSV ФАЙЛА: " + file.getName() + " ---");
        try {
            List<StationProperty> stations = parseCsvFile(file);
            System.out.println("Найдено станций: " + stations.size());
            for (int i = 0; i < Math.min(5, stations.size()); i++) {
                System.out.println("  " + stations.get(i));
            }
            if (stations.size() > 5) {
                System.out.println("  ... и еще " + (stations.size() - 5));
            }
        } catch (IOException | CsvException e) {
            System.err.println("Ошибка чтения CSV файла: " + e.getMessage());
        }
    }
}