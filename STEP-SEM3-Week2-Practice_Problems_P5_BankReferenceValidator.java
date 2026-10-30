class BankReferenceValidator {

    static String normalizeReference(String raw) {
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed;
        }
        String prefix = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);
        return prefix + rest;
    }

    static String validateAndFormat(String reference) {
        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }

        String bankCode = reference.substring(0, 3);
        for (int i = 0; i < bankCode.length(); i++) {
            if (!Character.isLetter(bankCode.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        String body = reference.substring(3);
        for (int i = 0; i < body.length(); i++) {
            if (!Character.isDigit(body.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }

        String dd = reference.substring(3, 5);
        String mm = reference.substring(5, 7);
        String yy = reference.substring(7, 9);
        String seq = reference.substring(9, 14);

        StringBuilder display = new StringBuilder();
        display.append("[").append(bankCode).append("] DATE: ").append(dd).append("/").append(mm)
               .append("/").append(yy).append(" | SEQ: ").append(seq);
        return display.toString();
    }

    public static void main(String[] args) {
        String ref1 = normalizeReference(" hdf03022600042 ");
        System.out.println(validateAndFormat(ref1));

        String ref2 = normalizeReference("12F03022600042");
        System.out.println(validateAndFormat(ref2));
    }
}
