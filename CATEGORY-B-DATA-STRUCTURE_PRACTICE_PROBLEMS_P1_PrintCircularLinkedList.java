class PrintCircularLinkedList {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static void printCircular(Node head) {
        StringBuilder sb = new StringBuilder();
        if (head != null) {
            Node current = head;
            do {
                sb.append(current.data).append(" ");
                current = current.next;
            } while (current != head);
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;

        printCircular(n1);
        printCircular(null);

        System.out.println();
        System.out.println("Time complexity: O(n), visits each node exactly once");
        System.out.println("Extra space: O(1), aside from the output buffer");
    }
}
