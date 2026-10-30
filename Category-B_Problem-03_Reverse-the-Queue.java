class ReverseTheQueue {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node reverse(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
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
        Node queue1 = build(new int[]{1, 2, 3, 4});
        System.out.print("Sample 1 output: ");
        print(reverse(queue1));

        Node queue2 = build(new int[]{9});
        System.out.print("Sample 2 output: ");
        print(reverse(queue2));
    }
}
