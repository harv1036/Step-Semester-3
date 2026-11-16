class Question_2 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static String kthFromEnd(Node head, int k) {
        Node lead = head;
        for (int i = 0; i < k; i++) {
            if (lead == null) {
                return "Invalid k";
            }
            lead = lead.next;
        }

        Node trail = head;
        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }
        return k + " from the end: " + trail.data;
    }

    static Node build(int... values) {
        Node head = null, tail = null;
        for (int v : values) {
            Node node = new Node(v);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
        }
        return head;
    }

    public static void main(String[] args) {
        Node list1 = build(301, 302, 303, 304, 305, 306);
        System.out.println(kthFromEnd(list1, 2));

        Node list2 = build(301, 302, 303);
        System.out.println(kthFromEnd(list2, 5));

        System.out.println();
        System.out.println("Time complexity: O(n), a single pass using a lead and a trail pointer");
        System.out.println("Extra space: O(1)");
        System.out.println("A two-pass solution is also O(n) overall but walks the list twice; this does it in one walk");
    }
}
