class DeleteNthNode {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node deleteNthNode(Node head, int n) {
        if (n == 1) {
            return head.next;
        }
        Node current = head;
        for (int i = 0; i < n - 2; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        return head;
    }

    static void print(Node head) {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
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
        Node list1 = build(10, 20, 30, 40);
        print(deleteNthNode(list1, 3));

        Node list2 = build(10, 20, 30, 40);
        print(deleteNthNode(list2, 1));

        System.out.println();
        System.out.println("Time complexity: O(n), walks to the node before the n-th position");
        System.out.println("Extra space: O(1)");
    }
}
