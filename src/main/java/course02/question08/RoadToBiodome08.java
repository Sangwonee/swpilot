package course02.question08;

public class RoadToBiodome08 {

    private static final int MAX_QUEUE_SIZE = 100;
    private static final String INVALID_INPUT_MESSAGE = "입력값이 올바르지 않습니다. 다시 확인해주세요.";

    private static final int[] queue = new int[MAX_QUEUE_SIZE];
    private static int front = 0;
    private static int rear = -1;
    private static int size = 0;

    public static void main(String[] args) {
        try {
            int[] resourceRequests = parseResourceRequests(args);
            resetQueue();

            for (int i = 0; i < resourceRequests.length; i++) {
                enqueue(resourceRequests[i]);
            }

            while (!isEmpty()) {
                int resource = peek();
                dequeue();
                System.out.println("자원 " + resource + "을 제공했습니다.");
            }

            System.out.println("모든 요청이 처리되었습니다.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(INVALID_INPUT_MESSAGE);
        }
    }

    // 명령행으로 입력된 자원 요청을 최대 100개의 정수 배열로 변환한다.
    private static int[] parseResourceRequests(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }

        String input = String.join(" ", args).trim();
        if (input.length() == 0) {
            throw new IllegalArgumentException();
        }

        String[] values = input.split("\\s+");
        if (values.length > MAX_QUEUE_SIZE) {
            throw new IllegalArgumentException();
        }

        int[] resourceRequests = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            resourceRequests[i] = Integer.parseInt(values[i]);
        }

        return resourceRequests;
    }

    // 큐의 마지막 위치에 새로운 자원 요청을 추가한다.
    public static void enqueue(int resource) {
        if (size == MAX_QUEUE_SIZE) {
            throw new IllegalStateException();
        }

        rear = (rear + 1) % MAX_QUEUE_SIZE;
        queue[rear] = resource;
        size++;
    }

    // 큐의 첫 번째 자원 요청을 제거하고 그 값을 반환한다.
    public static int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        int resource = queue[front];
        front = (front + 1) % MAX_QUEUE_SIZE;
        size--;

        if (isEmpty()) {
            front = 0;
            rear = -1;
        }

        return resource;
    }

    // 큐에서 제거하지 않고 첫 번째 자원 요청을 확인한다.
    public static int peek() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        return queue[front];
    }

    // 큐에 처리할 자원 요청이 남아 있는지 확인한다.
    public static boolean isEmpty() {
        return size == 0;
    }

    // 프로그램을 다시 실행하거나 테스트할 때 큐의 위치와 크기를 초기화한다.
    private static void resetQueue() {
        front = 0;
        rear = -1;
        size = 0;
    }
}
