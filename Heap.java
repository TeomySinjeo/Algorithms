package Heap;

import java.util.Arrays;

class Heap {
    private int[] heap;
    private int size;

    public Heap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    public Heap(int[] array) {
        heap = Arrays.copyOf(array, array.length);
        size = array.length;

        buildHeap();
    }

    private void buildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int index) {
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            int largest = index;

            if (left < size && heap[left] > heap[largest]) {
                largest = left;
            }

            if (right < size && heap[right] > heap[largest]) {
                largest = right;
            }

            if (largest == index) {
                break;
            }

            swap(index, largest);

            index = largest;
        }
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap[parent] >= heap[index]) {
                break;
            }

            swap(parent, index);

            index = parent;
        }
    }

    public void insert(int value) {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2 + 1);
        }

        heap[size] = value;
        size++;

        siftUp(size - 1);
    }

    public int removeMax() {
        if (size == 0) {
            throw new IllegalStateException("Куча пуста");
        }

        int max = heap[0];

        heap[0] = heap[size - 1];
        size--;

        if (size > 0) {
            siftDown(0);
        }

        return max;
    }

    public int getMax() {
        if (size == 0) {
            throw new IllegalStateException("Куча пуста");
        }

        return heap[0];
    }

    public void sort() {
        int oldSize = size;

        for (int i = size - 1; i > 0; i--) {
            swap(0, i);

            size = i;
            siftDown(0);
        }

        size = oldSize;
    }

    public static void heapSort(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);

            siftDown(array, i, 0);
        }
    }

    private static void siftDown(int[] array, int size, int index) {
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            int largest = index;

            if (left < size && array[left] > array[largest]) {
                largest = left;
            }

            if (right < size && array[right] > array[largest]) {
                largest = right;
            }

            if (largest == index) {
                break;
            }

            swap(array, index, largest);

            index = largest;
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }

        System.out.println();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public static void main(String[] args) {

        int[] array = {10, 4, 15, 20, 3, 8, 7};

        Heap heap = new Heap(array);

        System.out.println("Построенная куча:");
        heap.print();

        heap.insert(30);

        System.out.println("После добавления 30:");
        heap.print();

        System.out.println("Максимальный элемент: " + heap.getMax());

        int removed = heap.removeMax();

        System.out.println("Удалённый элемент: " + removed);

        System.out.println("Куча после удаления:");
        heap.print();

        heap.sort();

        System.out.println("Куча после сортировки:");
        heap.print();

        int[] numbers = {9, 5, 1, 8, 3, 7, 2, 6, 4};

        System.out.println("Массив до сортировки:");
        System.out.println(Arrays.toString(numbers));

        Heap.heapSort(numbers);

        System.out.println("Массив после сортировки:");
        System.out.println(Arrays.toString(numbers));
    }
}