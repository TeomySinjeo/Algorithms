package hash;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер хэш таблицы: ");
        int size = scanner.nextInt();
        scanner.nextLine();

        HashTable hashTable = new HashTable(size);

        boolean running = true;

        while (running) {

            System.out.println("Хэш таблица:");
            System.out.println("1) Добавить элемент");
            System.out.println("2) Найти элемент");
            System.out.println("3) Удалить элемент");
            System.out.println("4) Проверить существует ли");
            System.out.println("5) Вывести таблицу");
            System.out.println("6) Очистить таблицу");
            System.out.println("7) Показать статистику");
            System.out.println("0) Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Введите элемент: ");
                    String valueToAdd = scanner.nextLine();

                    if (hashTable.put(valueToAdd)) {
                        System.out.println(
                                "Элемент \"" + valueToAdd + "\" добавлен"
                        );
                    } else {
                        System.out.println(
                                "Не получилось добавить"
                        );
                    }
                    break;

                case 2:
                    System.out.print("Введите элемент для поиска: ");
                    String valueToFind = scanner.nextLine();

                    String result = hashTable.get(valueToFind);

                    if (result != null) {
                        System.out.println(
                                "Элемент \"" + result + "\" найден"
                        );
                    } else {
                        System.out.println(
                                "Элемент \"" + valueToFind + "\" не найден"
                        );
                    }
                    break;

                case 3:
                    System.out.print("Введите элемент для удаления: ");
                    String valueToRemove = scanner.nextLine();

                    if (hashTable.remove(valueToRemove)) {
                        System.out.println(
                                "Элемент \"" + valueToRemove + "\" удален"
                        );
                    } else {
                        System.out.println(
                                "Элемент \"" + valueToRemove +
                                        "\" не найден"
                        );
                    }
                    break;

                case 4:
                    System.out.print("Введите элемент: ");
                    String valueToCheck = scanner.nextLine();

                    if (hashTable.contains(valueToCheck)) {
                        System.out.println("Результат: true");
                    } else {
                        System.out.println("Результат: false");
                    }
                    break;

                case 5:
                    hashTable.print();
                    break;

                case 6:
                    hashTable.clear();
                    System.out.println("Хэш таблица очищена\n");
                    break;

                case 7:
                    hashTable.printStatistics();
                    break;

                case 0:
                    running = false;
                    System.out.println("Программа завершена");
                    break;

                default:
                    System.out.println(
                            "Ошибка: 1можно выбрать только доступные функции от 0 до 7\n"
                    );
            }
        }

        scanner.close();
    }
}
