class Question_1 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static int middle(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
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
        Node seats1 = build(11, 22, 33, 44, 55);
        System.out.println("Middle: " + middle(seats1));

        Node seats2 = build(11, 22, 33, 44, 55, 66);
        System.out.println("Middle: " + middle(seats2));

        System.out.println();
        System.out.println("Time complexity: O(n), a single pass with slow and fast pointers");
        System.out.println("Extra space: O(1)");
        System.out.println("The fast pointer visits about half the nodes (ceil(n/2)) before it runs off the end");
    }
}
