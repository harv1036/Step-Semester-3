class SecretNoticeShifter {

    static String shiftNotice(String text, int k) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append((char) ((c - 'A' + k) % 26 + 'A'));
            } else if (Character.isLowerCase(c)) {
                result.append((char) ((c - 'a' + k) % 26 + 'a'));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println("\"" + shiftNotice("Lab at 5 PM", 3) + "\"");
        System.out.println("\"" + shiftNotice("xyz", 2) + "\"");

        System.out.println();
        System.out.println("Time complexity: O(n), one pass over the text");
        System.out.println("Extra space: O(n), for the StringBuilder holding the result");
    }
}
