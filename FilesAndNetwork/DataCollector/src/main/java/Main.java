import model.Line;
import model.Station;
import model.StationProperty;
import parser.CsvParser;
import parser.JsonParser;
import parser.WebPageParser;
import searcher.FileSearcher;
import writer.JsonFileWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("   СБОР ДАННЫХ О МОСКОВСКОМ МЕТРОПОЛИТЕНЕ");
        System.out.println("=========================================\n");

        // Создаем необходимые директории
        createDirectories();

        // Шаг 1: Парсинг веб-страницы
        System.out.println("ШАГ 1: Парсинг веб-страницы");
        System.out.println("-----------------------------------------");
        WebPageParser webParser = new WebPageParser();
        List<Line> lines = new ArrayList<>();
        List<Station> stations = new ArrayList<>();

        try {
            webParser.fetchHtml();
            webParser.parseLines();
            webParser.parseStations();

            lines = webParser.getLines();
            stations = webParser.getStations();

            webParser.printLines();
            webParser.printStations();
        } catch (IOException e) {
            System.err.println("Ошибка при парсинге веб-страницы: " + e.getMessage());
        }

        // Шаг 2: Поиск файлов
        System.out.println("\n\nШАГ 2: Поиск файлов в папках");
        System.out.println("-----------------------------------------");
        FileSearcher fileSearcher = new FileSearcher();

        // Путь к папке с данными (укажите свой путь после разархивирования)
        String dataPath = "data";
        fileSearcher.printFoundFiles(dataPath);

        // Шаг 3: Парсинг JSON файлов
        System.out.println("\n\nШАГ 3: Парсинг JSON файлов");
        System.out.println("-----------------------------------------");
        JsonParser jsonParser = new JsonParser();
        List<File> jsonFiles = fileSearcher.findFiles(dataPath, ".json");
        List<StationProperty> jsonProperties = new ArrayList<>();

        for (File jsonFile : jsonFiles) {
            jsonParser.printJsonContent(jsonFile);
            try {
                jsonProperties.addAll(jsonParser.parseJsonFile(jsonFile));
            } catch (IOException e) {
                System.err.println("Ошибка при парсинге JSON: " + e.getMessage());
            }
        }

        System.out.println("\nВсего получено записей из JSON: " + jsonProperties.size());

        // Шаг 4: Парсинг CSV файлов
        System.out.println("\n\nШАГ 4: Парсинг CSV файлов");
        System.out.println("-----------------------------------------");
        CsvParser csvParser = new CsvParser();
        List<File> csvFiles = fileSearcher.findFiles(dataPath, ".csv");
        List<StationProperty> csvProperties = new ArrayList<>();

        for (File csvFile : csvFiles) {
            csvParser.printCsvContent(csvFile);
            try {
                csvProperties.addAll(csvParser.parseCsvFile(csvFile));
            } catch (Exception e) {
                System.err.println("Ошибка при парсинге CSV: " + e.getMessage());
            }
        }

        System.out.println("\nВсего получено записей из CSV: " + csvProperties.size());

        // Объединяем все свойства
        List<StationProperty> allProperties = new ArrayList<>();
        allProperties.addAll(jsonProperties);
        allProperties.addAll(csvProperties);

        // Шаг 5: Создание и запись JSON файлов
        System.out.println("\n\nШАГ 5: Создание выходных JSON файлов");
        System.out.println("-----------------------------------------");
        JsonFileWriter writer = new JsonFileWriter();

        try {
            writer.combineAndWriteData(
                    stations,
                    allProperties,
                    lines,
                    "data/map.json",
                    "data/stations.json"
            );
            System.out.println("\n✅ Файлы успешно созданы!");
        } catch (IOException e) {
            System.err.println("❌ Ошибка при записи файлов: " + e.getMessage());
        }

        System.out.println("\n=========================================");
        System.out.println("   РАБОТА ПРОГРАММЫ ЗАВЕРШЕНА");
        System.out.println("=========================================");
    }

    private static void createDirectories() {
        File dataDir = new File("FilesAndNetwork/DataCollector/data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
            System.out.println("Создана директория: data");
        }
    }
}