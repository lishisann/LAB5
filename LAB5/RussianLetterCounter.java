package LAB5;

import java.util.Set;
import java.util.stream.Collectors;

public class RussianLetterCounter {

    private RussianLetterCounter() {
    }

    public static int countUniqueLetters(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        Set<Character> uniqueLetters = text.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .filter(RussianLetterCounter::isRussianLetter)
                .collect(Collectors.toSet());

        return uniqueLetters.size();
    }

    private static boolean isRussianLetter(char c) {
        return (c >= 'а' && c <= 'я') || (c >= 'А' && c <= 'Я') || c == 'ё' || c == 'Ё';
    }
}
