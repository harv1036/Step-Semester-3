class Question_5 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node partition(Node head, int passMark) {
        Node belowHead = null, belowTail = null;
        Node restHead = null, restTail = null;

        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = null;
            if (current.data < passMark) {
                if (belowHead == null) {
                    belowHead = current;
                    belowTail = current;
                } else {
                    belowTail.next = current;
                    belowTail = current;
                }
            } else {
                if (restHead == null) {
                    restHead = current;
                    restTail = current;
                } else {
                    restTail.next = current;
                    restTail = current;
                }
            }
            current = next;
        }

        if (belowHead == null) {
            return restHead;
        }
        belowTail.next = restHead;
        return belowHead;
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
        Node marks1 = build(55, 32, 78, 20, 40, 35);
        print(partition(marks1, 40));

        Node marks2 = build(60, 70);
        print(partition(marks2, 40));

        System.out.println();
        System.out.println("Time complexity: O(n), one pass that relinks each node once");
        System.out.println("Extra space: O(1), only a few head and tail references are used");
    }
}
