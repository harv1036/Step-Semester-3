class LeaderboardRanking {

    static class Participant {
        String name;
        int score;

        Participant(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    static void sortByScore(Participant[] participants) {
        for (int i = 1; i < participants.length; i++) {
            Participant key = participants[i];
            int j = i - 1;
            while (j >= 0 && participants[j].score < key.score) {
                participants[j + 1] = participants[j];
                j--;
            }
            participants[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Participant[] participants = {
            new Participant("Asha", 90),
            new Participant("Ravi", 85),
            new Participant("Neha", 90),
            new Participant("Kiran", 70)
        };

        sortByScore(participants);

        int rank = 1;
        for (int i = 0; i < participants.length; i++) {
            if (i == 0 || participants[i].score != participants[i - 1].score) {
                rank = i + 1;
            }
            System.out.println(rank + " " + participants[i].name + " " + participants[i].score);
        }

        System.out.println();
        System.out.println("Time complexity: O(n^2) worst/average case for the insertion sort, O(n) best case (already sorted)");
        System.out.println("Printing the ranks afterward is O(n), one pass over the sorted array");
    }
}
