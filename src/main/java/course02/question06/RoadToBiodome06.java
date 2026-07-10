package course02.question06;

public class RoadToBiodome06 {

    private static final int MIN_WATER_HEIGHT = 0;
    private static final int MAX_WATER_HEIGHT = 100;
    private static final String INVALID_INPUT_MESSAGE = "입력이 올바르지 않습니다. 물 높이를 다시 한번 확인해주세요.";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(INVALID_INPUT_MESSAGE);
            return;
        }

        String input = String.join(" ", args).trim();
        if (input.length() == 0) {
            System.out.println(INVALID_INPUT_MESSAGE);
            return;
        }

        try {
            int[][] arrays = parseTwoArrays(input);
            int[] lakeWaterHeights = arrays[0];
            int[] riverWaterHeights = arrays[1];

            double mean = calculateMean(lakeWaterHeights, riverWaterHeights);
            double median = calculateMedian(lakeWaterHeights, riverWaterHeights);

            System.out.println("Mean : " + formatOneDecimal(mean) + ", Median : " + formatOneDecimal(median));
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INPUT_MESSAGE);
        }
    }

    // 입력 문자열에서 두 개의 배열 구간을 찾아 각각 정수 배열로 변환한다.
    private static int[][] parseTwoArrays(String input) {
        int firstStart = input.indexOf('[');
        int firstEnd = input.indexOf(']', firstStart + 1);
        int secondStart = input.indexOf('[', firstEnd + 1);
        int secondEnd = input.indexOf(']', secondStart + 1);

        if (firstStart == -1 || firstEnd == -1 || secondStart == -1 || secondEnd == -1) {
            throw new NumberFormatException();
        }
        if (!isBlank(input.substring(0, firstStart))
                || !isBlank(input.substring(firstEnd + 1, secondStart))
                || !isBlank(input.substring(secondEnd + 1))) {
            throw new NumberFormatException();
        }
        if (input.indexOf('[', secondStart + 1) != -1 || input.indexOf(']', secondEnd + 1) != -1) {
            throw new NumberFormatException();
        }

        int[] firstArray = parseArray(input.substring(firstStart + 1, firstEnd));
        int[] secondArray = parseArray(input.substring(secondStart + 1, secondEnd));

        if (firstArray.length == 0 || secondArray.length == 0) {
            throw new NumberFormatException();
        }

        return new int[][] { firstArray, secondArray };
    }

    // 배열 내부 문자열을 물 높이 정수 배열로 변환하고 값의 유효성을 검사한다.
    private static int[] parseArray(String arrayText) {
        String trimmedArrayText = arrayText.trim();
        if (trimmedArrayText.length() == 0) {
            return new int[0];
        }

        String[] values = trimmedArrayText.split(",", -1);
        int[] waterHeights = new int[values.length];

        for (int i = 0; i < values.length; i++) {
            String value = values[i].trim();
            if (value.length() == 0) {
                throw new NumberFormatException();
            }

            int waterHeight = Integer.parseInt(value);
            if (waterHeight < MIN_WATER_HEIGHT || waterHeight > MAX_WATER_HEIGHT) {
                throw new NumberFormatException();
            }

            waterHeights[i] = waterHeight;
        }

        return waterHeights;
    }

    // 문자열이 공백 문자만 포함하는지 확인한다.
    private static boolean isBlank(String text) {
        return text.trim().length() == 0;
    }

    // 두 배열 전체 데이터의 평균값을 계산한다.
    public static double calculateMean(int[] firstArray, int[] secondArray) {
        long sum = 0;

        for (int i = 0; i < firstArray.length; i++) {
            sum += firstArray[i];
        }
        for (int i = 0; i < secondArray.length; i++) {
            sum += secondArray[i];
        }

        return (double) sum / (firstArray.length + secondArray.length);
    }

    // 정렬된 두 배열을 합치지 않고 이진 탐색으로 전체 중앙값을 계산한다.
    public static double calculateMedian(int[] firstArray, int[] secondArray) {
        // 이진 탐색 범위를 줄이기 위해 더 짧은 배열을 firstArray로 맞춘다.
        if (firstArray.length > secondArray.length) {
            return calculateMedian(secondArray, firstArray);
        }

        int totalLength = firstArray.length + secondArray.length;
        // 전체 데이터를 반으로 나눴을 때 왼쪽 구간에 들어가야 하는 개수이다.
        // 홀수 개수일 때는 가운데 값을 왼쪽 구간에 포함하기 위해 1을 더한다.
        int leftPartitionSize = (totalLength + 1) / 2;

        // firstArray에서 왼쪽 구간으로 몇 개를 둘지 이 범위 안에서 찾는다.
        int low = 0;
        int high = firstArray.length;

        while (low <= high) {
            // firstArray에서 왼쪽 구간으로 둘 개수를 중간값으로 정한다.
            int firstPartitionSize = (low + high) / 2;
            // 왼쪽 구간 전체 개수를 맞추기 위해 secondArray에서 둘 개수를 계산한다.
            int secondPartitionSize = leftPartitionSize - firstPartitionSize;

            // 각 배열을 나누는 선 주변의 값 4개만 확인한다.
            int firstLeft = getLeftValue(firstArray, firstPartitionSize);
            int firstRight = getRightValue(firstArray, firstPartitionSize);
            int secondLeft = getLeftValue(secondArray, secondPartitionSize);
            int secondRight = getRightValue(secondArray, secondPartitionSize);

            // 왼쪽 구간의 값들이 오른쪽 구간의 값들보다 작거나 같으면 올바른 분할이다.
            if (firstLeft <= secondRight && secondLeft <= firstRight) {
                // 전체 개수가 홀수이면 왼쪽 구간의 가장 큰 값이 중앙값이다.
                if (totalLength % 2 == 1) {
                    return max(firstLeft, secondLeft);
                }

                // 전체 개수가 짝수이면 왼쪽 최대값과 오른쪽 최소값의 평균이 중앙값이다.
                return (max(firstLeft, secondLeft) + min(firstRight, secondRight)) / 2.0;
            }

            // firstArray의 왼쪽 값이 너무 크면 firstArray에서 왼쪽으로 둔 개수를 줄인다.
            if (firstLeft > secondRight) {
                high = firstPartitionSize - 1;
            } else {
                // secondArray의 왼쪽 값이 너무 크면 firstArray에서 왼쪽으로 둔 개수를 늘린다.
                low = firstPartitionSize + 1;
            }
        }

        // 정렬된 배열이라는 전제가 깨지면 올바른 분할을 찾지 못할 수 있다.
        throw new NumberFormatException();
    }

    // 분할 위치 왼쪽의 마지막 값을 반환하고, 값이 없으면 가장 작은 정수로 처리한다.
    private static int getLeftValue(int[] numbers, int partitionSize) {
        if (partitionSize == 0) {
            return Integer.MIN_VALUE;
        }
        return numbers[partitionSize - 1];
    }

    // 분할 위치 오른쪽의 첫 값을 반환하고, 값이 없으면 가장 큰 정수로 처리한다.
    private static int getRightValue(int[] numbers, int partitionSize) {
        if (partitionSize == numbers.length) {
            return Integer.MAX_VALUE;
        }
        return numbers[partitionSize];
    }

    // 두 정수 중 더 큰 값을 반환한다.
    private static int max(int firstNumber, int secondNumber) {
        if (firstNumber > secondNumber) {
            return firstNumber;
        }
        return secondNumber;
    }

    // 두 정수 중 더 작은 값을 반환한다.
    private static int min(int firstNumber, int secondNumber) {
        if (firstNumber < secondNumber) {
            return firstNumber;
        }
        return secondNumber;
    }

    // 숫자를 소수점 첫째 자리까지 반올림한 문자열로 변환한다.
    private static String formatOneDecimal(double value) {
        long scaled = (long) (value * 10 + 0.5);
        return (scaled / 10) + "." + (scaled % 10);
    }
}
