package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryProcessor {
    public static void main(String[] args) {
        try {
            // Чтение XML-файла
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(LibraryProcessor.class.getResourceAsStream("/library.xml"));

            // Получение списка книг
            NodeList bookNodes = document.getElementsByTagName("book");
            List<Book> books = new ArrayList<>();

            // Парсинг данных о книгах
            for (int i = 0; i < bookNodes.getLength(); i++) {
                Element bookElement = (Element) bookNodes.item(i);
                Book book = new Book(
                        bookElement.getAttribute("id"),
                        bookElement.getElementsByTagName("title").item(0).getTextContent(),
                        bookElement.getElementsByTagName("author").item(0).getTextContent(),
                        Integer.parseInt(bookElement.getElementsByTagName("year").item(0).getTextContent()),
                        bookElement.getElementsByTagName("genre").item(0).getTextContent(),
                        new BigDecimal(bookElement.getElementsByTagName("price").item(0).getTextContent())
                );
                books.add(book);
            }

            // Вывод всех книг
            System.out.println("Все книги в библиотеке:");
            books.forEach(System.out::println);

            // Вычисление средней цены
            BigDecimal averagePrice = books.stream()
                    .map(Book::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(new BigDecimal(books.size()), 2, BigDecimal.ROUND_HALF_UP);
            System.out.printf("\nСредняя цена книг: %.2f\n", averagePrice);

            // Фильтрация книг
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nФильтрация книг:");
            System.out.println("1 - По жанру");
            System.out.println("2 - По году издания");
            System.out.print("Выберите критерий фильтрации: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Введите жанр для фильтрации: ");
                String genre = scanner.nextLine();
                System.out.println("\nКниги жанра '" + genre + "':");
                books.stream()
                        .filter(b -> b.getGenre().equalsIgnoreCase(genre))
                        .forEach(System.out::println);
            } else if (choice == 2) {
                System.out.print("Введите год для фильтрации: ");
                int year = scanner.nextInt();
                System.out.println("\nКниги изданные в " + year + " году:");
                books.stream()
                        .filter(b -> b.getYear() == year)
                        .forEach(System.out::println);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
