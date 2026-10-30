import java.util.*;

class SCRStudent {
    String rollNumber;
    String name;

    SCRStudent(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SCRStudent)) return false;
        return rollNumber.equals(((SCRStudent) o).rollNumber);
    }

    @Override
    public int hashCode() {
        return rollNumber.hashCode();
    }
}

class StudentClubRegistration {

    public static void main(String[] args) {
        String[] input = {
            "ADD 21CS01 Asha",
            "ADD 21CS01 Asha",
            "ADD 21CS02 Ravi",
            "CONTAINS 21CS02 Ravi",
            "CONTAINS 21CS03 Priya"
        };

        Set<SCRStudent> members = new LinkedHashSet<>();
        boolean countPrinted = false;

        for (String line : input) {
            String[] parts = line.trim().split("\\s+", 3);
            String op = parts[0];
            String rollNumber = parts[1];
            String name = parts.length > 2 ? parts[2] : "";
            SCRStudent student = new SCRStudent(rollNumber, name);

            if (op.equals("ADD")) {
                if (members.contains(student)) {
                    System.out.println("duplicate rejected");
                } else {
                    members.add(student);
                    System.out.println("Added");
                }
            } else if (op.equals("CONTAINS")) {
                if (!countPrinted) {
                    System.out.println("member count " + members.size());
                    countPrinted = true;
                }
                System.out.println("contains: " + members.contains(student));
            }
        }

        if (!countPrinted) {
            System.out.println("member count " + members.size());
        }
    }
}
