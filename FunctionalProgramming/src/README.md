## Notification Service
### 📋 Описание проекта
Проект представляет собой систему для отправки различных типов уведомлений. Приложение демонстрирует использование принципов объектно-ориентированного программирования, дженериков и модульной архитектуры на Java.

### 🏗 Архитектура проекта
Проект разделен на логические модули:
```text
src/main/java/com/notification/
├── model/           # Модуль моделей данных
├── service/         # Модуль сервисов отправки
├── factory/         # Модуль фабрик для создания объектов
├── utils/           # Модуль утилит
└── Main.java        # Точка входа в приложение
```
## 📦 Модули и их назначение
### 1. Модуль моделей (model)
   Содержит классы, представляющие различные типы уведомлений:

- `Notification.java` - базовый интерфейс для всех уведомлений

- `EmailNotification.java` - уведомление для электронной почты

- `SmsNotification.java` - SMS-уведомление

- `PushNotification.java` - Push-уведомление для мобильных приложений

### 2. Модуль сервисов (service)
   Содержит классы для отправки уведомлений:

- `NotificationSender.java` - базовый интерфейс с методами отправки

- `EmailNotificationSender.java` - сервис отправки email-уведомлений

- `SmsNotificationSender.java` - сервис отправки SMS

- `PushNotificationSender.java` - сервис отправки push-уведомлений

### 3. Модуль фабрик (factory)
   Содержит классы для создания объектов:

- `NotificationFactory.java` - фабрика для создания тестовых уведомлений

### 4. Модуль утилит (utils)
   Содержит вспомогательные классы:

- `NotificationFormatter.java` - утилиты для форматирования вывода

## ✨ Особенности уведомлений
### EmailNotification
- Текст сообщения оборачивается в HTML-теги `<p>`

- Содержит тему письма и список получателей

Пример: `<p>Спасибо за регистрацию на сервисе!</p>`

### SmsNotification
- Текст сообщения не изменяется

- Содержит список телефонных номеров получателей

- Пример: `Спасибо за регистрацию на сервисе!`

### PushNotification
- В начало сообщения добавляется эмодзи 👋 (`\ud83d\udc4b`)

- Содержит заголовок и аккаунт пользователя

- Пример: 👋 Спасибо за регистрацию на сервисе!

## 🔧 Технические детали
### Интерфейс Notification
```java
public interface Notification {
    String getFormattedMessage();
}
```

### Интерфейс NotificationSender с дженериками
```java
public interface NotificationSender<T extends Notification> {
    void send(T notification);
    void send(List<T> notifications);
}
```

## 🚀 Запуск приложения
### Требования
- Java 11 или выше

- Любая IDE (IntelliJ IDEA, Eclipse и т.д.)

### Инструкция по запуску
#### 1. Клонируйте репозиторий:
```bash
git clone <url-репозитория>
cd FunctionalProgramming
```
#### 2. Откройте проект в IDE:

   - Импортируйте как Maven/Gradle проект (если используется)

   - Или откройте как обычный Java-проект

#### 3. Скомпилируйте проект:
```bash
javac -d out src/main/java/com/notification/**/*.java src/main/java/com/notification/Main.java
```

#### 4. Запустите приложение:
```bash
java -cp out com.notification.Main
```
## 📊 Пример вывода
```text
=== Демонстрация с использованием сервисов ===
==========================================
Отправка отдельных уведомлений
==========================================
EMAIL
subject: Успешная регистрация!
receivers: oleg@java.skillbox.ru, masha@java.skillbox.ru, yan@java.skillbox.ru
message: <p>Спасибо за регистрацию на сервисе!</p>

SMS
receivers: +70001234567
message: Спасибо за регистрацию на сервисе!

PUSH
title: Успешная регистрация!
receiver: o.yanovich
message: 👋 Спасибо за регистрацию на сервисе!
```
## 📁 Структура проекта с описанием
```text
FunctionalProgramming/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── notification/
│                   ├── model/
│                   │   ├── Notification.java           # Интерфейс уведомления
│                   │   ├── EmailNotification.java      # Email уведомление
│                   │   ├── SmsNotification.java        # SMS уведомление
│                   │   └── PushNotification.java       # Push уведомление
│                   │
│                   ├── service/
│                   │   ├── NotificationSender.java     # Интерфейс отправителя
│                   │   ├── EmailNotificationSender.java # Отправитель Email
│                   │   ├── SmsNotificationSender.java   # Отправитель SMS
│                   │   └── PushNotificationSender.java  # Отправитель Push
│                   │
│                   ├── factory/
│                   │   └── NotificationFactory.java    # Фабрика уведомлений
│                   │
│                   ├── utils/
│                   │   └── NotificationFormatter.java   # Утилиты форматирования
│                   │
│                   └── Main.java                        # Точка входа
│
├── README.md         # Документация проекта
└── .gitignore        # Игнорируемые файлы Git
```
## 🎯 Ключевые особенности реализации
1. **Модульность** - код разделен на логические модули с четкими ответственностями

2. **Дженерики** - использование параметризованных типов для типобезопасности

3. **Полиморфизм** - единый интерфейс для разных типов уведомлений

4. **Принцип единственной ответственности** - каждый класс отвечает за свою функциональность

5. **Расширяемость** - легко добавить новый тип уведомления

## 🔜 Возможные улучшения
- Добавить логирование вместо System.out

- Реализовать сохранение уведомлений в базу данных

- Добавить возможность отправки через реальные сервисы (email, SMS, push)

- Создать конфигурационный файл для настроек

- Добавить Unit-тесты

- Реализовать асинхронную отправку уведомлений

## 📄 Лицензия
Этот проект является учебным и создан в рамках курса по Java Core Advanced.

## 👥 Авторы
Skillbox - Java Core Advanced course