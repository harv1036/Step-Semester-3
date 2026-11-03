class ReverseFirstKNodes {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node reverseFirstKNodes(Node head, int k) {
        Node prev = null, current = head;
        for (int i = 0; i < k; i++) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head.next = current;
        return prev;
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
        Node list1 = build(1, 2, 3, 4, 5);
        print(reverseFirstKNodes(list1, 3));

        Node list2 = build(1, 2, 3, 4, 5);
        print(reverseFirstKNodes(list2, 5));

        System.out.println();
        System.out.println("Time complexity: O(k), reverses only the first k links");
        System.out.println("Extra space: O(1)");
    }
}
