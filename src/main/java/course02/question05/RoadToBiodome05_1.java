package course02.question05;

public class RoadToBiodome05_1 {

    private static final int MIN_WATER_HEIGHT = 0;
    private static final int MAX_WATER_HEIGHT = 100;
    private static final String INVALID_INPUT_MESSAGE = "입력된 값이 올바르지 않습니다. 다시 한번 물 높이를 확인해주세요.";

    // 입력받은 두 배열을 합치고 버블 정렬한 결과를 출력한다.
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
            int[] mergedWaterHeights = mergeArrays(arrays[0], arrays[1]);

            bubbleSort(mergedWaterHeights);

            System.out.println(formatArray(mergedWaterHeights));
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
            throw new NumberFormatException("배열이 없습니다.");
        }
        if (!isBlank(input.substring(0, firstStart))
                || !isBlank(input.substring(firstEnd + 1, secondStart))
                || !isBlank(input.substring(secondEnd + 1))) {
            throw new NumberFormatException("배열이 올바르지 않습니다.");
        }
        if (input.indexOf('[', secondStart + 1) != -1 || input.indexOf(']', secondEnd + 1) != -1) {
            throw new NumberFormatException("배열이 올바르지 않습니다.");
        }

        int[] firstArray = parseArray(input.substring(firstStart + 1, firstEnd));
        int[] secondArray = parseArray(input.substring(secondStart + 1, secondEnd));

        if (firstArray.length == 0 || secondArray.length == 0) {
            throw new NumberFormatException("배열이 비어있습니다.");
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

            long waterHeight = Long.parseLong(value);
            if (waterHeight < MIN_WATER_HEIGHT || waterHeight > MAX_WATER_HEIGHT) {
                throw new NumberFormatException();
            }

            waterHeights[i] = (int) waterHeight;
        }

        return waterHeights;
    }

    // 문자열이 공백 문자만 포함하는지 확인한다.
    private static boolean isBlank(String text) {
        return text.trim().length() == 0;
    }

    // 두 정수 배열을 순서대로 이어 붙인 새 배열을 만든다.
    public static int[] mergeArrays(int[] firstArray, int[] secondArray) {
        int[] mergedArray = new int[firstArray.length + secondArray.length];

        for (int i = 0; i < firstArray.length; i++) {
            mergedArray[i] = firstArray[i];
        }
        for (int i = 0; i < secondArray.length; i++) {
            mergedArray[firstArray.length + i] = secondArray[i];
        }

        return mergedArray;
    }

    // 인접한 두 값을 비교해 큰 값을 뒤로 보내며 배열을 오름차순 정렬한다.
    public static void bubbleSort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    swap(numbers, j, j + 1);
                    swapped = true;
                }
            }

            if (!swapped) {
                return;
            }
        }
    }

    // 배열에서 두 인덱스의 값을 서로 바꾼다.
    private static void swap(int[] numbers, int firstIndex, int secondIndex) {
        int temp = numbers[firstIndex];
        numbers[firstIndex] = numbers[secondIndex];
        numbers[secondIndex] = temp;
    }

    // 정수 배열을 출력 형식에 맞는 문자열로 변환한다.
    private static String formatArray(int[] numbers) {
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < numbers.length; i++) {
            if (i > 0) {
                result.append(",");
            }
            result.append(numbers[i]);
        }

        result.append("]");
        return result.toString();
    }
}
