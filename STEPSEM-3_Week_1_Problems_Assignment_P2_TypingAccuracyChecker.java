class TypingAccuracyChecker {

    static void checkTypingAccuracy(String original, String typed) {
        int length = original.length();
        int matched = 0;
        int firstMismatchPosition = -1;

        for (int i = 0; i < length; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatchPosition == -1) {
                firstMismatchPosition = i;
            }
        }

        double accuracy = (matched / (double) length) * 100;
        String summary = "Matched: " + matched + "/" + length
                + " | Accuracy: " + String.format("%.2f", accuracy) + "%";

        if (firstMismatchPosition == -1) {
            System.out.println(summary + " | No Mismatches");
        } else {
            System.out.println(summary + " | First Mismatch at position " + (firstMismatchPosition + 1)
                    + " ('" + original.charAt(firstMismatchPosition) + "' vs '" + typed.charAt(firstMismatchPosition) + "')");
        }
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}
