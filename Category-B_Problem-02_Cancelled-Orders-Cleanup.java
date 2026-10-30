class CancelledOrdersCleanup {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node removeAll(Node head, int code) {
        while (head != null && head.data == code) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        Node prev = head;
        Node curr = head.next;
        while (curr != null) {
            if (curr.data == code) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }

        return head;
    }

    static Node build(int[] values) {
        Node head = null, tail = null;
        for (int v : values) {
            Node node = new Node(v);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }
        return head;
    }

    static void print(Node head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.data).append(" -> ");
            head = head.next;
        }
        sb.append("null");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Node orders1 = build(new int[]{5, 3, 5, 8, 5});
        System.out.print("Sample 1 output: ");
        print(removeAll(orders1, 5));

        Node orders2 = build(new int[]{5, 5, 5});
        System.out.print("Sample 2 output: ");
        print(removeAll(orders2, 5));
    }
}
