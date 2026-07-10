package course02.question08;

public class RoadToBiodome08_1 {

    private static final int INITIAL_QUEUE_SIZE = 10;
    private static final int QUEUE_GROWTH_SIZE = 10;
    private static final String INVALID_INPUT_MESSAGE = "입력값이 올바르지 않습니다. 다시 확인해주세요.";

    private static int[] queue = new int[INITIAL_QUEUE_SIZE];
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
                System.out.println("자원 제공: " + resource);
            }

            System.out.println("모든 요청이 처리되었습니다.");
        }
        catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(INVALID_INPUT_MESSAGE);
        }
    }

    // 명령행으로 입력된 자원 요청을 정수 배열로 변환한다.
    private static int[] parseResourceRequests(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }

        String input = String.join(" ", args).trim();
        if (input.length() == 0) {
            throw new IllegalArgumentException();
        }

        String[] values = input.split("\\s+");
        int[] resourceRequests = new int[values.length];

        for (int i = 0; i < values.length; i++) {
            resourceRequests[i] = Integer.parseInt(values[i]);
        }

        return resourceRequests;
    }

    // 큐가 가득 차면 크기를 늘린 뒤 마지막 위치에 자원 요청을 추가한다.
    public static void enqueue(int resource) {
        if (size == queue.length) {
            expandQueue();
        }

        rear = (rear + 1) % queue.length;
        queue[rear] = resource;
        size++;
    }

    // 큐의 첫 번째 자원 요청을 제거하고 그 값을 반환한다.
    public static int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        int resource = queue[front];
        front = (front + 1) % queue.length;
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

    // 기존 요청 순서를 유지하면서 큐 배열의 크기를 10만큼 늘린다.
    private static void expandQueue() {
        int[] expandedQueue = new int[queue.length + QUEUE_GROWTH_SIZE];

        for (int i = 0; i < size; i++) {
            expandedQueue[i] = queue[(front + i) % queue.length];
        }

        queue = expandedQueue;
        front = 0;
        rear = size - 1;

        System.out.println("Queue의 크기가 " + queue.length + "으로 늘어났습니다.");
    }

    // 큐를 기본 크기 10의 빈 상태로 초기화한다.
    private static void resetQueue() {
        queue = new int[INITIAL_QUEUE_SIZE];
        front = 0;
        rear = -1;
        size = 0;
    }
}
