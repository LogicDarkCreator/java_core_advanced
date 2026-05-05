package util;

import java.util.*;

public class UserManager {
    private final Map<String, User> usersByPassport = new HashMap<>();
    private final TreeSet<User> usersByAge = new TreeSet<>(Comparator.comparingInt(User::getAge)
            .thenComparing(User::getPassportNumber));

    // O(1) complexity
    public boolean addUser(String passportNumber, String name, int age) {
        if (usersByPassport.containsKey(passportNumber)) {
            return false;
        }

        User user = new User(passportNumber, name, age);
        usersByPassport.put(passportNumber, user);
        usersByAge.add(user);
        return true;
    }

    // O(1) complexity
    public boolean removeUser(String passportNumber) {
        User user = usersByPassport.remove(passportNumber);
        if (user != null) {
            usersByAge.remove(user);
            return true;
        }
        return false;
    }

    // O(1) complexity
    public int getCount() {
        return usersByPassport.size();
    }

    // O(n) complexity
    public double getAverageAge() {
        if (usersByPassport.isEmpty()) {
            return 0;
        }

        int sum = usersByPassport.values().stream()
                .mapToInt(User::getAge)
                .sum();
        return (double) sum / usersByPassport.size();
    }

    // O(n) complexity
    public double getMedianAge() {
        if (usersByPassport.isEmpty()) {
            return 0;
        }

        List<User> sortedUsers = new ArrayList<>(usersByAge);
        int size = sortedUsers.size();

        if (size % 2 == 1) {
            return sortedUsers.get(size / 2).getAge();
        } else {
            int mid1 = sortedUsers.get(size / 2 - 1).getAge();
            int mid2 = sortedUsers.get(size / 2).getAge();
            return (mid1 + mid2) / 2.0;
        }
    }

    // O(log n) complexity
    public User getYoungest() {
        return usersByAge.isEmpty() ? null : usersByAge.first();
    }

    // O(log n) complexity
    public User getOldest() {
        return usersByAge.isEmpty() ? null : usersByAge.last();
    }

    // O(n) complexity
    public List<User> getAllUsersSortedByAge() {
        return new ArrayList<>(usersByAge);
    }

    public boolean hasUser(String passportNumber) {
        return usersByPassport.containsKey(passportNumber);
    }

    public boolean isEmpty() {
        return usersByPassport.isEmpty();
    }
}
