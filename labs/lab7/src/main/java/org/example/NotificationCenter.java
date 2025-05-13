package org.example;

import java.util.ArrayList;
import java.util.List;

// Центр оповещения
class NotificationCenter implements Subject {
    private List<Observer> stations = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        stations.add(observer);
        System.out.println(((GAIStation) observer).getName() + " зарегистрирован в системе оповещения");
    }

    @Override
    public void removeObserver(Observer observer) {
        stations.remove(observer);
        System.out.println(((GAIStation) observer).getName() + " удален из системы оповещения");
    }

    @Override
    public void notifyObservers(String message) {
        System.out.println("\nОтправка оповещения всем постам ГАИ...");
        for (Observer station : stations) {
            station.update(message);
        }
    }

    // Дополнительный метод для удобства отправки сообщений
    public void sendAlert(String message) {
        notifyObservers(message);
    }
}
