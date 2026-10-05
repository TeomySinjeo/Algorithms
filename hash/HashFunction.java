package hash;

public class HashFunction {

    public static int hash(String key, int tableSize) {
        int hash = 0;

        for (int i = 0; i < key.length(); i++) {
            hash = hash * 31 + key.charAt(i);
        }

        return Math.abs(hash) % tableSize;
    }
}