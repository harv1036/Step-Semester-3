class InsertAtEndCircular {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node insertAtEndCircular(Node head, int value) {
        Node newNode = new Node(value);
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }
        last.next = newNode;
        newNode.next = head;
        return head;
    }

    static void printCircular(Node head) {
        StringBuilder sb = new StringBuilder();
        if (head != null) {
            Node current = head;
            do {
                sb.append(current.data).append(" -> ");
                current = current.next;
            } while (current != head);
        }
        sb.append("(back to ").append(head == null ? "" : head.data).append(")");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;

        Node head1 = insertAtEndCircular(n1, 4);
        printCircular(head1);

        Node head2 = insertAtEndCircular(null, 9);
        printCircular(head2);

        System.out.println();
        System.out.println("Time complexity: O(n), walks the list once to find the current last node");
        System.out.println("Extra space: O(1)");
    }
}
