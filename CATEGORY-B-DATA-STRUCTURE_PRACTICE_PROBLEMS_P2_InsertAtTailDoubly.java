class InsertAtTailDoubly {

    static class DNode {
        int data;
        DNode prev;
        DNode next;

        DNode(int data) {
            this.data = data;
        }
    }

    static DNode insertAtTailDoubly(DNode head, int value) {
        DNode newNode = new DNode(value);
        if (head == null) {
            return newNode;
        }
        DNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = newNode;
        newNode.prev = tail;
        return head;
    }

    static void print(DNode head) {
        StringBuilder sb = new StringBuilder();
        DNode current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" <-> ");
            }
            current = current.next;
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        DNode head = new DNode(1);
        head.next = new DNode(2);
        head.next.prev = head;
        head.next.next = new DNode(3);
        head.next.next.prev = head.next;

        head = insertAtTailDoubly(head, 4);
        print(head);

        DNode empty = null;
        empty = insertAtTailDoubly(empty, 7);
        print(empty);

        System.out.println();
        System.out.println("Time complexity: O(n), walks to the current tail");
        System.out.println("Extra space: O(1)");
    }
}
