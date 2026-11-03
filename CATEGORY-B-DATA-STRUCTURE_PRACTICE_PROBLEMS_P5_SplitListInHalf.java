class SplitListInHalf {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node splitListInHalf(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node secondHalf = slow.next;
        slow.next = null;
        return secondHalf;
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
        Node second1 = splitListInHalf(list1);
        System.out.print("First half: ");
        print(list1);
        System.out.print("Returned: ");
        print(second1);

        Node list2 = build(1, 2, 3, 4);
        Node second2 = splitListInHalf(list2);
        System.out.print("First half: ");
        print(list2);
        System.out.print("Returned: ");
        print(second2);

        System.out.println();
        System.out.println("Time complexity: O(n), the fast pointer traverses the list once");
        System.out.println("Extra space: O(1)");
    }
}
