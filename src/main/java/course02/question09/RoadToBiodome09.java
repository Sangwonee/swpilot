package course02.question09;

import java.util.ArrayDeque;
import java.util.Queue;

public class RoadToBiodome09 {

    private static final String INVALID_INPUT_MESSAGE = "입력값이 올바르지 않습니다. 다시 한번 확인해주세요.";
    private static final String NO_PATH_MESSAGE = "안전하게 이동할 수 있는 경로가 없습니다.";

    private static final int[] ROW_DIRECTIONS = {-1, 1, 0, 0};
    private static final int[] COLUMN_DIRECTIONS = {0, 0, -1, 1};

    public static void main(String[] args) {
        try {
            int[][] cave = parseCave(args);
            int shortestDistance = calculateShortestDistance(cave);

            if (shortestDistance == -1) {
                System.out.println(NO_PATH_MESSAGE);
                return;
            }

            System.out.println(shortestDistance);
        } catch (IllegalArgumentException e) {
            System.out.println(INVALID_INPUT_MESSAGE);
        }
    }

    // 명령행으로 입력된 각 행을 0과 1로 이루어진 직사각형 동굴 배열로 변환한다.
    private static int[][] parseCave(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }

        String input = String.join(" ", args).trim();
        if (input.length() == 0) {
            throw new IllegalArgumentException();
        }

        String[] rows = input.split("\\s+");
        int columnCount = rows[0].length();
        if (columnCount == 0) {
            throw new IllegalArgumentException();
        }

        int[][] cave = new int[rows.length][columnCount];

        for (int row = 0; row < rows.length; row++) {
            if (rows[row].length() != columnCount) {
                throw new IllegalArgumentException();
            }

            for (int column = 0; column < columnCount; column++) {
                char terrain = rows[row].charAt(column);
                if (terrain != '0' && terrain != '1') {
                    throw new IllegalArgumentException();
                }

                cave[row][column] = terrain - '0';
            }
        }

        return cave;
    }

    // BFS로 시작점에서 출구까지 이동하는 데 필요한 최소 이동 횟수를 계산한다.
    public static int calculateShortestDistance(int[][] cave) {
        int rowCount = cave.length;
        int columnCount = cave[0].length;
        int exitRow = rowCount - 1;
        int exitColumn = columnCount - 1;

        if (cave[0][0] == 0 || cave[exitRow][exitColumn] == 0) {
            return -1;
        }

        boolean[][] visited = new boolean[rowCount][columnCount];
        int[][] distances = new int[rowCount][columnCount];
        Queue<int[]> positions = new ArrayDeque<>();

        positions.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!positions.isEmpty()) {
            int[] currentPosition = positions.poll();
            int currentRow = currentPosition[0];
            int currentColumn = currentPosition[1];

            if (currentRow == exitRow && currentColumn == exitColumn) {
                return distances[currentRow][currentColumn];
            }

            for (int direction = 0; direction < ROW_DIRECTIONS.length; direction++) {
                int nextRow = currentRow + ROW_DIRECTIONS[direction];
                int nextColumn = currentColumn + COLUMN_DIRECTIONS[direction];

                if (!canMove(cave, visited, nextRow, nextColumn)) {
                    continue;
                }

                visited[nextRow][nextColumn] = true;
                distances[nextRow][nextColumn] = distances[currentRow][currentColumn] + 1;
                positions.offer(new int[]{nextRow, nextColumn});
            }
        }

        return -1;
    }

    // 다음 좌표가 동굴 안에 있고 이동 가능하며 아직 방문하지 않은 칸인지 확인한다.
    private static boolean canMove(int[][] cave, boolean[][] visited, int row, int column) {
        return row >= 0
                && row < cave.length
                && column >= 0
                && column < cave[0].length
                && cave[row][column] == 1
                && !visited[row][column];
    }
}
