package LAB5;

import java.util.LinkedList;
import java.util.Queue;

public class QueueBuilder {

    private int[] sourceList;   // Исходный массив чисел
    private Queue<Integer> resultQueue;     // Рез. очередь

    public QueueBuilder(int[] sourceList) {
        if (sourceList == null || sourceList.length == 0) {
            throw new IllegalArgumentException("Список не может быть пустым.");
        }
        this.sourceList = sourceList;
        this.resultQueue = new LinkedList<>();
    }

    public void buildQueue() {
        resultQueue.clear(); // Очищаем очередь перед построением

        for (int num : sourceList) {
            resultQueue.add(num); // Добавляем элементы массива в очередь в прямом порядке
        }

        for (int i = sourceList.length - 1; i >= 0; i--) {
            resultQueue.add(sourceList[i]); // В обратном порядке
        }
    }

    public Queue<Integer> getResultQueue() {
        return resultQueue;
    }

    @Override
    public String toString() {
        return "QueueBuilder{sourceList=" + arrayToString(sourceList)
                + ", resultQueue=" + resultQueue + '}';
    }

    private String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("["); // Начинаем со собки
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]); // Добавляем число
            if (i < arr.length - 1) {
                sb.append(", "); // Запятые между числами
            }
        }
        return sb.append("]").toString(); // Закрываем скобку и преобразуем в строку
    }
}
