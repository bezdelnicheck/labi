package org.example;

// Демонстрационный класс
public class GAINotificationSystem {
    public static void main(String[] args) {
        // Создаем центр оповещения
        NotificationCenter center = new NotificationCenter();

        // Создаем посты ГАИ
        GAIStation station1 = new GAIStation("Пост №1 (Центральный)");
        GAIStation station2 = new GAIStation("Пост №2 (Северный)");
        GAIStation station3 = new GAIStation("Пост №3 (Южный)");

        // Регистрируем посты в системе
        center.registerObserver(station1);
        center.registerObserver(station2);
        center.registerObserver(station3);

        // Отправляем оповещение
        center.sendAlert("Внимание! На трассе М1 замечено нарушение ПДД. Требуется перехват.");

        // Удаляем один пост
        center.removeObserver(station2);

        // Отправляем второе оповещение
        center.sendAlert("Внимание! Объявлен план 'Перехват' в районе кольцевой дороги.");
    }
}
