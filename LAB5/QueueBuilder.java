package LAB5;

import java.util.*;

public class QueueBuilder {

    public static Queue<Integer> buildQueue(List<Integer> list) {
        Queue<Integer> queue = new LinkedList<>();

        for (Integer elem : list) {
            queue.add(elem);
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            queue.add(list.get(i));
        }

        return queue;
    }
}
