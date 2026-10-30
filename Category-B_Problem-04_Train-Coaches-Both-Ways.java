class TrainCoachesBothWays {

    static class Node {
        String name;
        Node prev;
        Node next;

        Node(String name) {
            this.name = name;
        }
    }

    Node head;
    Node tail;

    void addLast(String name) {
        Node node = new Node(name);
        if (head == null) {
            head = node;
            tail = node;
            return;
        }
        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    void remove(String name) {
        Node curr = head;
        while (curr != null && !curr.name.equals(name)) {
            curr = curr.next;
        }
        if (curr == null) {
            return;
        }

        if (curr.prev != null) {
            curr.prev.next = curr.next;
        } else {
            head = curr.next;
        }

        if (curr.next != null) {
            curr.next.prev = curr.prev;
        } else {
            tail = curr.prev;
        }
    }

    void printForward() {
        StringBuilder sb = new StringBuilder("Forward: ");
        Node curr = head;
        while (curr != null) {
            sb.append(curr.name);
            if (curr.next != null) {
                sb.append(" <-> ");
            }
            curr = curr.next;
        }
        System.out.println(sb);
    }

    void printBackward() {
        StringBuilder sb = new StringBuilder("Backward: ");
        Node curr = tail;
        while (curr != null) {
            sb.append(curr.name);
            if (curr.prev != null) {
                sb.append(" <-> ");
            }
            curr = curr.prev;
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        TrainCoachesBothWays train = new TrainCoachesBothWays();
        train.addLast("Engine");
        train.addLast("A1");
        train.addLast("B1");
        train.addLast("B2");
        train.addLast("Guard");

        train.remove("B1");

        train.printForward();
        train.printBackward();
    }
}
