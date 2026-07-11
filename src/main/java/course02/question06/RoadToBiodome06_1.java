package course02.question06;

import java.util.Arrays;

public class RoadToBiodome06_1 {

    private static final int MIN_WATER_HEIGHT = 0;
    private static final int MAX_WATER_HEIGHT = 100;
    private static final int BONUS_MIN_WATER_HEIGHT = 30;
    private static final String INVALID_INPUT_MESSAGE =
            "입력이 올바르지 않습니다. 물 높이를 다시 한번 확인해주세요.";

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
            int[] lakeWaterHeights = filterWaterHeights(arrays[0]);
            int[] riverWaterHeights = filterWaterHeights(arrays[1]);

            if (lakeWaterHeights.length + riverWaterHeights.length == 0) {
                throw new NumberFormatException();
            }

            double mean = calculateMean(lakeWaterHeights, riverWaterHeights);
            double median = calculateMedian(lakeWaterHeights, riverWaterHeights);

            System.out.println("Mean : " + formatOneDecimal(mean)
                    + ", Median : " + formatMedian(median));
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INPUT_MESSAGE);
        }
    }

    // 입력 문자열에서 두 배열을 찾아 각각 물 높이 정수 배열로 변환한다.
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
        if (input.indexOf('[', secondStart + 1) != -1
                || input.indexOf(']', secondEnd + 1) != -1) {
            throw new NumberFormatException();
        }

        int[] firstArray = parseArray(input.substring(firstStart + 1, firstEnd));
        int[] secondArray = parseArray(input.substring(secondStart + 1, secondEnd));

        if (firstArray.length == 0 || secondArray.length == 0) {
            throw new NumberFormatException();
        }

        return new int[][] { firstArray, secondArray };
    }

    // 배열 내부 문자열을 정수 배열로 변환하고 물 높이 범위를 확인한다.
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

    // 30 이상인 물 높이만 새 배열에 복사하고 오름차순으로 정렬한다.
    public static int[] filterWaterHeights(int[] waterHeights) {
        int filteredCount = 0;

        for (int i = 0; i < waterHeights.length; i++) {
            if (waterHeights[i] >= BONUS_MIN_WATER_HEIGHT) {
                filteredCount++;
            }
        }

        int[] filteredWaterHeights = new int[filteredCount];
        int filteredIndex = 0;

        for (int i = 0; i < waterHeights.length; i++) {
            if (waterHeights[i] >= BONUS_MIN_WATER_HEIGHT) {
                filteredWaterHeights[filteredIndex] = waterHeights[i];
                filteredIndex++;
            }
        }

        Arrays.sort(filteredWaterHeights);
        return filteredWaterHeights;
    }

    // 문자열이 공백 문자만 포함하는지 확인한다.
    private static boolean isBlank(String text) {
        return text.trim().length() == 0;
    }

    // 필터링된 두 배열 전체 데이터의 평균값을 계산한다.
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

    // 정렬된 두 배열을 합치지 않고 이진 탐색으로 중앙값을 계산한다.
    public static double calculateMedian(int[] firstArray, int[] secondArray) {
        if (firstArray.length > secondArray.length) {
            return calculateMedian(secondArray, firstArray);
        }

        int totalLength = firstArray.length + secondArray.length;
        int leftPartitionSize = (totalLength + 1) / 2;
        int low = 0;
        int high = firstArray.length;

        while (low <= high) {
            int firstPartitionSize = (low + high) / 2;
            int secondPartitionSize = leftPartitionSize - firstPartitionSize;

            int firstLeft = getLeftValue(firstArray, firstPartitionSize);
            int firstRight = getRightValue(firstArray, firstPartitionSize);
            int secondLeft = getLeftValue(secondArray, secondPartitionSize);
            int secondRight = getRightValue(secondArray, secondPartitionSize);

            if (firstLeft <= secondRight && secondLeft <= firstRight) {
                if (totalLength % 2 == 1) {
                    return max(firstLeft, secondLeft);
                }

                return ((long) max(firstLeft, secondLeft)
                        + min(firstRight, secondRight)) / 2.0;
            }

            if (firstLeft > secondRight) {
                high = firstPartitionSize - 1;
            } else {
                low = firstPartitionSize + 1;
            }
        }

        throw new NumberFormatException();
    }

    private static int getLeftValue(int[] numbers, int partitionSize) {
        if (partitionSize == 0) {
            return Integer.MIN_VALUE;
        }
        return numbers[partitionSize - 1];
    }

    private static int getRightValue(int[] numbers, int partitionSize) {
        if (partitionSize == numbers.length) {
            return Integer.MAX_VALUE;
        }
        return numbers[partitionSize];
    }

    private static int max(int firstNumber, int secondNumber) {
        if (firstNumber > secondNumber) {
            return firstNumber;
        }
        return secondNumber;
    }

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

    // 중앙값이 정수이면 정수로, 소수이면 소수점 첫째 자리까지 출력한다.
    private static String formatMedian(double value) {
        long integerValue = (long) value;
        if (value == integerValue) {
            return String.valueOf(integerValue);
        }
        return formatOneDecimal(value);
    }
}
