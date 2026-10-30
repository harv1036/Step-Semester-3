import java.util.ArrayList;
import java.util.List;

class CampusNoticeBroadcaster {

    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        Student asha = new Student("Asha", "CSE");
        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());
        board.registerStudent(asha);

        Student ravi = new Student("Ravi", "ECE");
        ravi.addChannel(new SmsChannel());
        board.registerStudent(ravi);

        board.postNotice(new Notice("Lab Closed Tomorrow", List.of("CSE")));
        board.postNotice(new Notice("Fee Deadline Extended", List.of("CSE", "ECE")));
        board.postNotice(new Notice("Sports Day", List.of()));
    }
}

interface NotificationChannel {
    void send(String studentName, String message);
}

class EmailChannel implements NotificationChannel {
    public void send(String studentName, String message) {
        System.out.println("[Email -> " + studentName + "] " + message);
    }
}

class SmsChannel implements NotificationChannel {
    public void send(String studentName, String message) {
        System.out.println("[SMS -> " + studentName + "] " + message);
    }
}

class AppChannel implements NotificationChannel {
    public void send(String studentName, String message) {
        System.out.println("[App -> " + studentName + "] " + message);
    }
}

class Student {
    private final String name;
    private final String department;
    private final List<NotificationChannel> channels;

    Student(String name, String department) {
        this.name = name;
        this.department = department;
        this.channels = new ArrayList<>();
    }

    String getName() { return name; }
    String getDepartment() { return department; }

    void addChannel(NotificationChannel channel) {
        channels.add(channel);
    }

    void receive(String message) {
        for (NotificationChannel channel : channels) {
            channel.send(name, message);
        }
    }
}

class Notice {
    private final String title;
    private final List<String> targetDepartments;

    Notice(String title, List<String> targetDepartments) {
        this.title = title;
        this.targetDepartments = targetDepartments;
    }

    String getTitle() { return title; }
    List<String> getTargetDepartments() { return targetDepartments; }

    boolean isValid() {
        return title != null && !title.isEmpty() && !targetDepartments.isEmpty();
    }
}

class NoticeBoard {
    private final List<Student> students;

    NoticeBoard() {
        this.students = new ArrayList<>();
    }

    void registerStudent(Student student) {
        students.add(student);
    }

    void postNotice(Notice notice) {
        if (!notice.isValid()) {
            System.out.println("Cannot post notice: At least one target department is required.");
            return;
        }
        System.out.println("Notice '" + notice.getTitle() + "' posted to "
                + String.join(", ", notice.getTargetDepartments()) + ".");
        for (Student student : students) {
            if (notice.getTargetDepartments().contains(student.getDepartment())) {
                student.receive(notice.getTitle());
            }
        }
    }
}
