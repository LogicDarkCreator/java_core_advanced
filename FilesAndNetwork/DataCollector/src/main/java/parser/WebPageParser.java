package parser;

import model.Line;
import model.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebPageParser {
    private static final String URL = "https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D1%81%D1%82%D0%B0%D0%BD%D1%86%D0%B8%D0%B9_%D0%9C%D0%BE%D1%81%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_%D0%BC%D0%B5%D1%82%D1%80%D0%BE%D0%BF%D0%BE%D0%BB%D0%B8%D1%82%D0%B5%D0%BD%D0%B0";

    private Document document;
    private List<Line> lines;
    private List<Station> stations;

    public void fetchHtml() throws IOException {
        System.out.println("Загрузка HTML-страницы...");
        document = Jsoup.connect(URL).get();
        System.out.println("HTML-страница загружена успешно.");
    }

    public void parseLines() {
        lines = new ArrayList<>();
        Elements lineElements = document.select("span.sortkey");

        for (Element element : lineElements) {
            Element parent = element.parent();
            if (parent != null && parent.tagName().equals("td")) {
                Element spanWithTitle = parent.select("span[title]").first();
                if (spanWithTitle != null) {
                    String lineNumber = spanWithTitle.attr("title");
                    String lineName = parent.text().replace(lineNumber, "").trim();

                    // Очистка номера линии от лишних символов
                    lineNumber = lineNumber.replaceAll("[^0-9А-Яа-я]", "");

                    if (!lineNumber.isEmpty() && !lineName.isEmpty()) {
                        lines.add(new Line(lineNumber, lineName));
                    }
                }
            }
        }

        // Если не удалось получить линии через первый метод, используем альтернативный
        if (lines.isEmpty()) {
            parseLinesAlternative();
        }

        System.out.println("Найдено линий: " + lines.size());
    }

    private void parseLinesAlternative() {
        Elements links = document.select("a[href^='/wiki/']");
        for (Element link : links) {
            String href = link.attr("href");
            if (href.contains("линия") || href.contains("line")) {
                String lineNumber = href.replaceAll(".*?(\\d+).*", "$1");
                String lineName = link.text();

                if (!lineNumber.isEmpty() && !lineName.isEmpty()) {
                    lines.add(new Line(lineNumber, lineName));
                }
            }
        }
    }

    public void parseStations() {
        stations = new ArrayList<>();
        Elements stationLinks = document.select("a[href^='/wiki/']");

        for (Element link : stationLinks) {
            String stationName = link.text();
            if (isStationName(stationName)) {
                // Пытаемся найти номер линии для станции
                Element parent = link.parent();
                String lineNumber = findLineNumber(parent);

                if (!stationName.isEmpty() && lineNumber != null && !lineNumber.isEmpty()) {
                    stations.add(new Station(stationName, lineNumber));
                }
            }
        }

        // Если первый метод не сработал, парсим таблицы
        if (stations.isEmpty()) {
            parseStationsFromTables();
        }

        System.out.println("Найдено станций: " + stations.size());
    }

    private boolean isStationName(String text) {
        return text.contains("станция") ||
                text.contains("метро") ||
                text.endsWith("ская") ||
                text.endsWith("во") ||
                text.endsWith("но");
    }

    private String findLineNumber(Element element) {
        if (element == null) return null;

        // Ищем в родительских элементах
        Element parent = element;
        for (int i = 0; i < 5; i++) {
            if (parent == null) break;

            String text = parent.text();
            String[] parts = text.split(" ");
            for (String part : parts) {
                if (part.matches("\\d+") && Integer.parseInt(part) <= 15) {
                    return part;
                }
            }
            parent = parent.parent();
        }
        return null;
    }

    private void parseStationsFromTables() {
        Elements tables = document.select("table.wikitable");
        for (Element table : tables) {
            Elements rows = table.select("tr");
            for (Element row : rows) {
                Elements cells = row.select("td");
                if (cells.size() >= 2) {
                    String stationName = cells.get(0).text();
                    String lineNumber = cells.get(1).text();

                    if (!stationName.isEmpty() && !lineNumber.isEmpty()) {
                        stations.add(new Station(stationName, lineNumber));
                    }
                }
            }
        }
    }

    public List<Line> getLines() {
        return lines;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void printLines() {
        System.out.println("\n--- ЛИНИИ МЕТРО ---");
        for (Line line : lines) {
            System.out.println(line);
        }
    }

    public void printStations() {
        System.out.println("\n--- СТАНЦИИ МЕТРО (первые 20) ---");
        int count = 0;
        for (Station station : stations) {
            System.out.println(station);
            count++;
            if (count >= 20) {
                System.out.println("... и еще " + (stations.size() - 20));
                break;
            }
        }
    }
}