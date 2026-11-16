class EditorUndoHistory {

    static class Editor {
        String[] history;
        int top;

        Editor(int capacity) {
            history = new String[capacity];
            top = -1;
        }

        void type(String word) {
            if (top == history.length - 1) {
                System.out.println("rejected: history full");
                return;
            }
            history[++top] = word;
            print();
        }

        void undo() {
            if (top == -1) {
                System.out.println("rejected: nothing to undo");
                return;
            }
            top--;
            print();
        }

        void print() {
            if (top == -1) {
                System.out.println("(empty)");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= top; i++) {
                sb.append(history[i]);
                if (i < top) {
                    sb.append(" ");
                }
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        Editor editor = new Editor(3);

        editor.type("Hi");
        editor.type("there");
        editor.type("all");
        editor.type("now");
        editor.undo();
        editor.undo();
        editor.undo();
        editor.undo();

        System.out.println();
        System.out.println("Time complexity: O(1) for both TYPE and UNDO, each only touches the top index");
    }
}
