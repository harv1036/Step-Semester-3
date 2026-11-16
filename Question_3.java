class Question_3 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node merge(Node first, Node second) {
        Node dummy = new Node(0);
        Node tail = dummy;

        while (first != null && second != null) {
            if (first.data <= second.data) {
                tail.next = first;
                first = first.next;
            } else {
                tail.next = second;
                second = second.next;
            }
            tail = tail.next;
        }
        tail.next = (first != null) ? first : second;

        return dummy.next;
    }

    static void print(Node head) {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.data).append(" -> ");
            current = current.next;
        }
        sb.append("null");
        System.out.println(sb);
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
        Node first1 = build(3, 8, 15);
        Node second1 = build(5, 8, 20, 25);
        print(merge(first1, second1));

        Node first2 = null;
        Node second2 = build(4, 9);
        print(merge(first2, second2));

        System.out.println();
        System.out.println("Time complexity: O(m + n), where m and n are the lengths of the two lists");
        System.out.println("Extra space: O(1) beyond the one dummy node, the existing nodes are relinked");
    }
}
