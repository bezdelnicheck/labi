package org.example;

// Конкретный пост ГАИ
class GAIStation implements Observer {
    private String name;

    public GAIStation(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("Пост ГАИ '" + name + "' получил оповещение: " + message);
    }

    public String getName() {
        return name;
    }
}
