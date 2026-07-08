package course02.question02;

public class RoadToBiodome02_1 {

    public static void main(String[] args) {
        CustomStringStack fruits = new CustomStringStack(3);

        fruits.push("사과");
        fruits.push("바나나");
        fruits.push("체리");

        System.out.println(fruits.pop());
        System.out.println(fruits.peek());
    }

    private static class CustomStringStack {
        private final String[] elements;
        private int top;

        public CustomStringStack(int capacity) {
            this.elements = new String[capacity];
            this.top = -1;
        }

        public void push(String value) {
            elements[++top] = value;
        }

        public String pop() {
            String value = elements[top];
            elements[top] = null;
            top--;
            return value;
        }

        public String peek() {
            return elements[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }
    }
}
