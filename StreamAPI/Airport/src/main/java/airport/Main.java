package airport;

import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Flight.Type;
import com.skillbox.airport.Terminal;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static long findCountAircraftWithModelAirbus(Airport airport, String model) {
        // Получаем все самолеты из всех источников
        Stream<com.skillbox.airport.Aircraft> arrivalsAircraft = airport.getTerminals().stream()
                .flatMap(terminal -> terminal.getFlights().stream())
                .filter(flight -> flight.getType() == Type.ARRIVAL)
                .map(Flight::getAircraft);

        Stream<com.skillbox.airport.Aircraft> departuresAircraft = airport.getTerminals().stream()
                .flatMap(terminal -> terminal.getFlights().stream())
                .filter(flight -> flight.getType() == Type.DEPARTURE)
                .map(Flight::getAircraft);

        Stream<com.skillbox.airport.Aircraft> parkedAircraft = airport.getTerminals().stream()
                .flatMap(terminal -> terminal.getParkedAircrafts().stream());

        return Stream.of(arrivalsAircraft, departuresAircraft, parkedAircraft)
                .flatMap(stream -> stream)
                .filter(aircraft -> {
                    String modelName = aircraft.getModel(); // Используем getModel() вместо getName()
                    return modelName != null && modelName.startsWith(model);
                })
                .count();
    }

    public static Map<String, Integer> findMapCountParkedAircraftByTerminalName(Airport airport) {
        return airport.getTerminals().stream()
                .collect(Collectors.toMap(
                        Terminal::getName,
                        terminal -> terminal.getParkedAircrafts().size()
                ));
    }

    public static List<Flight> findFlightsLeavingInTheNextHours(Airport airport, int hours) {
        Instant now = Instant.now();
        Instant futureTime = now.plusSeconds(hours * 3600L);

        return airport.getTerminals().stream()
                .flatMap(terminal -> terminal.getFlights().stream())
                .filter(flight -> flight.getType() == Type.DEPARTURE)
                .filter(flight -> {
                    Instant flightTime = flight.getDate();
                    return flightTime != null &&
                            !flightTime.isBefore(now) &&
                            !flightTime.isAfter(futureTime);
                })
                .collect(Collectors.toList());
    }

    public static Optional<Flight> findFirstFlightArriveToTerminal(Airport airport, String terminalName) {
        return airport.getTerminals().stream()
                .filter(terminal -> terminal.getName().equals(terminalName))
                .flatMap(terminal -> terminal.getFlights().stream())
                .filter(flight -> flight.getType() == Type.ARRIVAL)
                .min(Comparator.comparing(Flight::getDate));
    }

    // Добавим метод main для тестирования
    public static void main(String[] args) {
        // Получаем экземпляр аэропорта (библиотека предоставляет этот метод)
        Airport airport = Airport.getInstance();

        System.out.println("=== Тестирование методов ===");
        System.out.println("Аэропорт инициализирован: " + airport);

        // Тест 1: поиск самолетов Airbus
        long airbusCount = Main.findCountAircraftWithModelAirbus(airport, "Airbus");
        System.out.println("\n1. Количество самолетов Airbus: " + airbusCount);

        // Тест 2: припаркованные самолеты по терминалам
        Map<String, Integer> parkedAircraft = Main.findMapCountParkedAircraftByTerminalName(airport);
        System.out.println("\n2. Припаркованные самолеты по терминалам:");
        if (parkedAircraft.isEmpty()) {
            System.out.println("   Нет данных о терминалах");
        } else {
            parkedAircraft.forEach((terminal, count) ->
                    System.out.println("   Терминал " + terminal + ": " + count + " самолетов")
            );
        }

        // Тест 3: вылетающие рейсы
        List<Flight> departingFlights = Main.findFlightsLeavingInTheNextHours(airport, 3);
        System.out.println("\n3. Рейсы, вылетающие в ближайшие 3 часа: " + departingFlights.size());
        if (!departingFlights.isEmpty()) {
            departingFlights.stream()
                    .limit(5)
                    .forEach(flight -> System.out.println("   " + flight));
        }

        // Тест 4: ближайший прилетающий рейс
        Optional<Flight> firstArrival = Main.findFirstFlightArriveToTerminal(airport, "A");
        System.out.println("\n4. Ближайший рейс в терминал A:");
        firstArrival.ifPresentOrElse(
                flight -> System.out.println("   " + flight),
                () -> System.out.println("   Рейсов не найдено")
        );
    }
}