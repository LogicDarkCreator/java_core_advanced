package parser;

import model.StationProperty;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public List<StationProperty> parseJsonFile(File file) throws IOException {
        List<StationProperty> stations = new ArrayList<>();
        String content = new String(Files.readAllBytes(file.toPath()));

        try {
            JSONObject rootObject = new JSONObject(content);

            if (rootObject.has("stations")) {
                Object stationsObj = rootObject.get("stations");
                if (stationsObj instanceof JSONArray) {
                    parseStationsArray((JSONArray) stationsObj, stations);
                }
            } else {
                // Пробуем распарсить как массив
                try {
                    JSONArray jsonArray = new JSONArray(content);
                    parseStationsArray(jsonArray, stations);
                } catch (Exception e) {
                    // Если не массив, пробуем как объект со свойствами
                    parseObjectProperties(rootObject, stations);
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка парсинга JSON: " + e.getMessage());
        }

        return stations;
    }

    private void parseStationsArray(JSONArray stationsArray, List<StationProperty> stations) {
        for (int i = 0; i < stationsArray.length(); i++) {
            JSONObject stationObj = stationsArray.getJSONObject(i);
            StationProperty station = parseStationObject(stationObj);
            if (station != null) {
                stations.add(station);
            }
        }
    }

    private void parseObjectProperties(JSONObject object, List<StationProperty> stations) {
        for (String key : object.keySet()) {
            Object value = object.get(key);
            if (value instanceof JSONObject) {
                StationProperty station = parseStationObject((JSONObject) value);
                if (station != null && station.getName() == null) {
                    station.setName(key);
                }
                if (station != null) {
                    stations.add(station);
                }
            }
        }
    }

    private StationProperty parseStationObject(JSONObject obj) {
        try {
            String name = obj.has("name") ? obj.getString("name") : null;
            String line = obj.has("line") ? obj.getString("line") : null;

            if (name == null || line == null) {
                return null;
            }

            StationProperty station = new StationProperty(name, line);

            if (obj.has("date") && !obj.isNull("date")) {
                station.setDate(obj.getString("date"));
            }

            if (obj.has("depth") && !obj.isNull("depth")) {
                try {
                    station.setDepth(obj.getDouble("depth"));
                } catch (Exception e) {
                    try {
                        station.setDepth(Double.parseDouble(obj.getString("depth")));
                    } catch (Exception ignored) {}
                }
            }

            if (obj.has("hasConnection") && !obj.isNull("hasConnection")) {
                try {
                    station.setHasConnection(obj.getBoolean("hasConnection"));
                } catch (Exception e) {
                    String val = obj.getString("hasConnection");
                    station.setHasConnection(val.equalsIgnoreCase("true") ||
                            val.equalsIgnoreCase("да") ||
                            val.equals("1"));
                }
            }

            return station;
        } catch (Exception e) {
            return null;
        }
    }

    public void printJsonContent(File file) {
        System.out.println("\n--- ПАРСИНГ JSON ФАЙЛА: " + file.getName() + " ---");
        try {
            List<StationProperty> stations = parseJsonFile(file);
            System.out.println("Найдено станций: " + stations.size());
            for (int i = 0; i < Math.min(5, stations.size()); i++) {
                System.out.println("  " + stations.get(i));
            }
            if (stations.size() > 5) {
                System.out.println("  ... и еще " + (stations.size() - 5));
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения JSON файла: " + e.getMessage());
        }
    }
}