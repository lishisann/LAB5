package LAB5;

import java.util.ArrayList;
import java.util.List;

public class OrderedList {
    private final List<Integer> elements;

    public OrderedList() {
        this.elements = new ArrayList<>(); // создаем пустой список
    }

    public OrderedList(int[] initialElements) {
        this();
        if (initialElements != null) {
            for (int value : initialElements) {
                insert(value); // вставляем элементы по порядку
            }
        }
    }

    public void insert(int value) {
        int i = 0;
        while (i < elements.size() && elements.get(i) < value) {
            i++; // ищем место для вставки
        }
        elements.add(i, value); // вставляем элемент в нужную позицию
    }

    public void insertAll(OrderedList other) {
        if (other != null) {
            for (int value : other.elements) {
                insert(value); // вставляем все элементы из другого списка
            }
        }
    }

    public List<Integer> getElements() {
        return new ArrayList<>(elements); // возвращаем копию списка
    }

    @Override
    public String toString() {
        return elements.toString(); // выводим список в виде строки
    }
}
