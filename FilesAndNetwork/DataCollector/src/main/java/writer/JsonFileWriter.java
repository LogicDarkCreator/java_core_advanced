package writer;

import model.Line;
import model.Station;
import model.StationProperty;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JsonFileWriter {

    public void writeMapJson(List<Line> lines, List<Station> stations, String outputPath) throws IOException {
        JSONObject rootObject = new JSONObject();

        // Создаем массив линий
        JSONArray linesArray = new JSONArray();
        for (Line line : lines) {
            JSONObject lineObject = new JSONObject();
            lineObject.put("number", line.getNumber());
            lineObject.put("name", line.getName());
            linesArray.put(lineObject);
        }
        rootObject.put("lines", linesArray);

        // Создаем объект со станциями по линиям
        JSONObject stationsByLineObject = new JSONObject();

        // Группируем станции по линиям
        Map<String, List<String>> stationsMap = new HashMap<>();
        for (Station station : stations) {
            String lineNumber = station.getLineNumber();
            if (!stationsMap.containsKey(lineNumber)) {
                stationsMap.put(lineNumber, new ArrayList<>());
            }
            stationsMap.get(lineNumber).add(station.getName());
        }

        // Добавляем станции в объект
        for (Map.Entry<String, List<String>> entry : stationsMap.entrySet()) {
            JSONArray stationNamesArray = new JSONArray();
            for (String name : entry.getValue()) {
                stationNamesArray.put(name);
            }
            stationsByLineObject.put(entry.getKey(), stationNamesArray);
        }

        rootObject.put("stations", stationsByLineObject);

        // Записываем в файл с красивым форматированием
        try (FileWriter fileWriter = new FileWriter(outputPath)) {
            fileWriter.write(rootObject.toString(4));
        }

        System.out.println("Файл map.json успешно создан: " + outputPath);
    }

    public void writeStationsJson(List<StationProperty> stationProperties, String outputPath) throws IOException {
        JSONObject rootObject = new JSONObject();
        JSONArray stationsArray = new JSONArray();

        for (StationProperty prop : stationProperties) {
            JSONObject stationObject = new JSONObject();

            stationObject.put("name", prop.getName());
            stationObject.put("line", prop.getLine());

            // Добавляем только не-null поля
            if (prop.getDate() != null) {
                stationObject.put("date", prop.getDate());
            }

            if (prop.getDepth() != null) {
                stationObject.put("depth", prop.getDepth());
            }

            if (prop.getHasConnection() != null) {
                stationObject.put("hasConnection", prop.getHasConnection());
            }

            stationsArray.put(stationObject);
        }

        rootObject.put("stations", stationsArray);

        // Записываем в файл с красивым форматированием
        try (FileWriter fileWriter = new FileWriter(outputPath)) {
            fileWriter.write(rootObject.toString(4));
        }

        System.out.println("Файл stations.json успешно создан: " + outputPath);
    }

    public void combineAndWriteData(List<Station> baseStations,
                                    List<StationProperty> properties,
                                    List<Line> lines,
                                    String mapOutputPath,
                                    String stationsOutputPath) throws IOException {

        // Создаем Map для быстрого поиска свойств станций по имени
        Map<String, StationProperty> propertyMap = new HashMap<>();
        for (StationProperty prop : properties) {
            propertyMap.put(prop.getName(), prop);
        }

        // Создаем Map линий по номеру
        Map<String, Line> linesMap = new HashMap<>();
        for (Line line : lines) {
            linesMap.put(line.getNumber(), line);
        }

        // Создаем полный список свойств станций
        List<StationProperty> fullProperties = new ArrayList<>();
        Set<String> processedStations = new HashSet<>();

        for (Station station : baseStations) {
            String stationName = station.getName();
            if (processedStations.contains(stationName)) {
                continue;
            }
            processedStations.add(stationName);

            Line line = linesMap.get(station.getLineNumber());
            String lineName = (line != null) ? line.getName() : "Неизвестная линия";

            StationProperty fullProp;
            if (propertyMap.containsKey(stationName)) {
                fullProp = propertyMap.get(stationName);
                // Обновляем название линии из нашего источника
                fullProp.setLine(lineName);
            } else {
                fullProp = new StationProperty(stationName, lineName);
            }

            fullProperties.add(fullProp);
        }

        // Добавляем станции из свойств, которых нет в базовом списке
        for (StationProperty prop : properties) {
            if (!processedStations.contains(prop.getName())) {
                fullProperties.add(prop);
                processedStations.add(prop.getName());
            }
        }

        // Записываем файлы
        writeMapJson(lines, baseStations, mapOutputPath);
        writeStationsJson(fullProperties, stationsOutputPath);
    }
}