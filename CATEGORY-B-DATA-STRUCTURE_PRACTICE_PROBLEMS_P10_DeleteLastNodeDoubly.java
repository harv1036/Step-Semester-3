class DeleteLastNodeDoubly {

    static class DNode {
        int data;
        DNode prev;
        DNode next;

        DNode(int data) {
            this.data = data;
        }
    }

    static DNode deleteLastNodeDoubly(DNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return null;
        }
        DNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.prev.next = null;
        current.prev = null;
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
        DNode head1 = new DNode(1);
        head1.next = new DNode(2);
        head1.next.prev = head1;
        head1.next.next = new DNode(3);
        head1.next.next.prev = head1.next;

        print(deleteLastNodeDoubly(head1));

        DNode head2 = new DNode(5);
        print(deleteLastNodeDoubly(head2));

        System.out.println();
        System.out.println("Time complexity: O(n), walks to the last node");
        System.out.println("Extra space: O(1)");
    }
}
