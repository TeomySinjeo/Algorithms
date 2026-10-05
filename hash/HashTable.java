package hash;

public class HashTable {

    private String[] table;
    private int size;
    private int count;
    private int collisions;

    private static final String DELETED = "DELETED";

    public HashTable(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер должен быть > 0");
        }

        this.size = size;
        this.table = new String[size];
        this.count = 0;
        this.collisions = 0;
    }

    public boolean put(String key) {
        if (key == null || key.isEmpty()) {
            return false;
        }

        if (contains(key)) {
            return false;
        }

        int index = HashFunction.hash(key, size);
        int startIndex = index;

        while (table[index] != null && !table[index].equals(DELETED)) {
            collisions++;

            index = (index + 1) % size;

            if (index == startIndex) {
                return false;
            }
        }

        table[index] = key;
        count++;

        return true;
    }

    public String get(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }

        int index = HashFunction.hash(key, size);
        int startIndex = index;

        while (table[index] != null) {

            if (!table[index].equals(DELETED)
                    && table[index].equals(key)) {
                return table[index];
            }

            index = (index + 1) % size;

            if (index == startIndex) {
                break;
            }
        }

        return null;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public boolean remove(String key) {
        if (key == null || key.isEmpty()) {
            return false;
        }

        int index = HashFunction.hash(key, size);
        int startIndex = index;

        while (table[index] != null) {

            if (!table[index].equals(DELETED)
                    && table[index].equals(key)) {

                table[index] = DELETED;
                count--;

                return true;
            }

            index = (index + 1) % size;

            if (index == startIndex) {
                break;
            }
        }

        return false;
    }

    public void clear() {
        table = new String[size];
        count = 0;
        collisions = 0;
    }

    public void print() {
        System.out.println("\nХэш таблица:");

        for (int i = 0; i < size; i++) {
            System.out.println("[" + i + "] = " + table[i]);
        }

    }

    public void printStatistics() {
        int freeCells = size - count;
        double loadFactor = (double) count / size * 100;

        System.out.println("\nСтатистика:");
        System.out.println("Размер таблицы: " + size);
        System.out.println("Количество элементов: " + count);
        System.out.println("Свободных полей: " + freeCells);
        System.out.println("Количество коллизий: " + collisions);
        System.out.printf("Коэффициент заполнения: %.2f%%%n", loadFactor);
    }

    public int getSize() {
        return size;
    }

    public int getCount() {
        return count;
    }
}