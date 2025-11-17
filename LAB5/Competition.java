package LAB5;

import java.util.*;

class Competition {
    private Map<String, Integer> participants;

    public Competition() {
        participants = new HashMap<>();
    }

    public void addParticipant(String lastName, String firstName, int[] scores) {
        int totalScore = 0;
        for (int score : scores) {
            totalScore += score;
        }
        String fullName = lastName + " " + firstName;
        participants.put(fullName, totalScore);
    }

    public List<String> getTopParticipants() {
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(participants.entrySet());
        sortedList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        Set<Integer> topScores = new LinkedHashSet<>();
        for (Map.Entry<String, Integer> entry : sortedList) {
            topScores.add(entry.getValue());
            if (topScores.size() == 3) break;
        }

        List<String> topParticipants = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedList) {
            if (topScores.contains(entry.getValue())) {
                topParticipants.add(entry.getKey());
            }
        }
        return topParticipants;
    }
}
