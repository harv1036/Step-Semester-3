class Question_4 {

    static class Tab {
        String name;
        Tab prev;
        Tab next;

        Tab(String name) {
            this.name = name;
        }
    }

    Tab head;
    Tab cur;

    void open(String name) {
        Tab tab = new Tab(name);
        if (cur == null) {
            head = tab;
            cur = tab;
            return;
        }
        tab.prev = cur;
        tab.next = cur.next;
        if (cur.next != null) {
            cur.next.prev = tab;
        }
        cur.next = tab;
        cur = tab;
    }

    String close() {
        String closedName = cur.name;
        Tab prev = cur.prev;
        Tab next = cur.next;
        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
        if (cur == head) {
            head = (next != null) ? next : prev;
        }
        cur = (next != null) ? next : prev;
        return closedName;
    }

    boolean back() {
        if (cur.prev == null) {
            return false;
        }
        cur = cur.prev;
        return true;
    }

    boolean forward() {
        if (cur.next == null) {
            return false;
        }
        cur = cur.next;
        return true;
    }

    String strip() {
        StringBuilder sb = new StringBuilder();
        Tab t = head;
        while (t != null) {
            sb.append(t == cur ? "[" + t.name + "]" : t.name);
            if (t.next != null) {
                sb.append(" <-> ");
            }
            t = t.next;
        }
        return sb.toString();
    }

    String printBackward() {
        Tab t = head;
        while (t != null && t.next != null) {
            t = t.next;
        }
        StringBuilder sb = new StringBuilder();
        while (t != null) {
            sb.append(t.name);
            if (t.prev != null) {
                sb.append(" <-> ");
            }
            t = t.prev;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Question_4 browser = new Question_4();

        browser.open("Mail");
        System.out.println("OPEN Mail: " + browser.strip());

        browser.open("Docs");
        System.out.println("OPEN Docs: " + browser.strip());

        browser.open("Maps");
        System.out.println("OPEN Maps: " + browser.strip());

        System.out.println(browser.back() ? "BACK: " + browser.strip() : "BACK: already at first tab");
        System.out.println(browser.back() ? "BACK: " + browser.strip() : "BACK: already at first tab");
        System.out.println(browser.back() ? "BACK: " + browser.strip() : "BACK: already at first tab");

        browser.open("Drive");
        System.out.println("OPEN Drive: " + browser.strip());

        String closed = browser.close();
        System.out.println("CLOSE " + closed + ": " + browser.strip());

        System.out.println(browser.forward() ? "FORWARD: " + browser.strip() : "FORWARD: already at last tab");

        closed = browser.close();
        System.out.println("CLOSE " + closed + ": " + browser.strip());

        System.out.println("Backward: " + browser.printBackward());

        System.out.println();
        System.out.println("Time complexity: open, close, back and forward are all O(1), each one only touches the current tab and its immediate neighbours");
    }
}
