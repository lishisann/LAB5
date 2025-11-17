package LAB5;

import java.util.*;

public class ListMerger {

    public static List<Integer> mergeAndSort(List<Integer> L1, List<Integer> L2) {
        List<Integer> result = new ArrayList<>(L1);
        result.addAll(L2);
        Collections.sort(result);
        return result;
    }
}
