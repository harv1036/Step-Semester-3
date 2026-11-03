class ContestLeaderboard {

    static class Entry {
        String name;
        int marks;

        Entry(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }

        public String toString() {
            return name + " " + marks;
        }
    }

    static Entry[] leaderboard(Entry[] entries) {
        int n = entries.length;
        int[] count = new int[102];

        for (Entry e : entries) {
            count[100 - e.marks]++;
        }
        for (int key = 1; key <= 100; key++) {
            count[key] += count[key - 1];
        }

        Entry[] result = new Entry[n];
        for (int i = n - 1; i >= 0; i--) {
            int key = 100 - entries[i].marks;
            count[key]--;
            result[count[key]] = entries[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Entry[] entries = {
            new Entry("Asha", 90),
            new Entry("Ravi", 75),
            new Entry("Neha", 90),
            new Entry("Kiran", 100),
            new Entry("Meera", 75)
        };

        Entry[] result = leaderboard(entries);
        for (Entry e : result) {
            System.out.println(e);
        }

        System.out.println();
        System.out.println("Time complexity: O(n + 101), linear in the number of entries plus the fixed marks range");
        System.out.println("Extra space: O(n + 101), for the counting array and the output array");
        System.out.println("Stable: yes, entries are placed by walking the input right to left, so equal marks keep submission order");
    }
}
