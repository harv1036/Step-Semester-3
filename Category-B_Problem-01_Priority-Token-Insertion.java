class PriorityTokenInsertion {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    Node head;

    void addFirst(int token) {
        Node newNode = new Node(token);
        newNode.next = head;
        head = newNode;
    }

    void addLast(int token) {
        Node newNode = new Node(token);
        if (head == null) {
            head = newNode;
            return;
        }
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
    }

    void insertAt(int index, int token) {
        if (index == 0) {
            addFirst(token);
            return;
        }
        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        Node newNode = new Node(token);
        newNode.next = prev.next;
        prev.next = newNode;
    }

    void print() {
        Node curr = head;
        StringBuilder sb = new StringBuilder();
        while (curr != null) {
            sb.append(curr.data).append(" -> ");
            curr = curr.next;
        }
        sb.append("null");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        PriorityTokenInsertion list = new PriorityTokenInsertion();
        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        list.print();

        System.out.println();
        System.out.println("Time complexity:");
        System.out.println("addFirst: O(1) - new node becomes head directly");
        System.out.println("addLast: O(n) - must walk to the last node first");
        System.out.println("insertAt: O(n) - must walk to the node just before the index");
    }
}
