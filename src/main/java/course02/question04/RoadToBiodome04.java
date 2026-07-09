package course02.question04;

public class RoadToBiodome04 {

    private static final String INVALID_INPUT_MESSAGE = "입력된 값이 올바르지 않습니다.";

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
            int arrayCount = countArrays(input);

            if (arrayCount > 1) {
                printMediansForMultipleArrays(input, arrayCount);
                return;
            }

            int[] numbers = parseSingleArray(input);
            if (numbers.length == 0) {
                System.out.println(INVALID_INPUT_MESSAGE);
                return;
            }

            selectionSort(numbers);

            double average = calculateAverage(numbers);
            double median = calculateMedian(numbers);

            System.out.println("평균값 : " + formatNumber(average) + ", 중앙값 : " + formatNumber(median));
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INPUT_MESSAGE);
        }
    }

    // 여러 배열이 입력된 경우, 각 배열의 중앙값을 차례대로 출력한다.
    private static void printMediansForMultipleArrays(String input, int arrayCount) {
        int currentIndex = 0;
        int printedCount = 0;
        String result = "";

        while (printedCount < arrayCount) {
            int start = input.indexOf('[', currentIndex);
            int end = input.indexOf(']', start);

            if (start == -1 || end == -1 || start > end) {
                System.out.println(INVALID_INPUT_MESSAGE);
                return;
            }

            String arrayText = input.substring(start, end + 1);
            int[] numbers = parseSingleArray(arrayText);
            if (numbers.length == 0) {
                System.out.println(INVALID_INPUT_MESSAGE);
                return;
            }

            selectionSort(numbers);
            double median = calculateMedian(numbers);

            if (printedCount > 0) {
                result += ", ";
            }
            result += formatNumber(median);

            currentIndex = end + 1;
            printedCount++;
        }

        System.out.println(result);
    }

    // 입력 문자열 안에 배열이 몇 개 있는지 센다.
    private static int countArrays(String input) {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '[') {
                count++;
            }
        }
        return count;
    }

    // 문자열 형태의 배열을 int 배열로 변환한다.
    public static int[] parseSingleArray(String input) {
        String normalizedInput = input.replace('[', ' ')
                                      .replace(']', ' ')
                                      .trim();

        if (normalizedInput.length() == 0) {
            return new int[0];
        }

        String[] values = normalizedInput.split(",");
        int[] numbers = new int[values.length];

        for (int i = 0; i < values.length; i++) {
            String value = values[i].trim();
            if (value.length() == 0) {
                throw new NumberFormatException();
            }
            numbers[i] = Integer.parseInt(value);
        }

        return numbers;
    }

    // 선택 정렬로 배열을 오름차순 정렬한다.
    public static void selectionSort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = numbers[i];
            numbers[i] = numbers[minIndex];
            numbers[minIndex] = temp;
        }
    }

    // 배열의 평균값을 계산한다.
    public static double calculateAverage(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return (double) sum / numbers.length;
    }

    // 정렬된 배열의 중앙값을 계산한다.
    public static double calculateMedian(int[] sortedNumbers) {
        int length = sortedNumbers.length;
        int middle = length / 2;

        if (length % 2 == 1) {
            return sortedNumbers[middle];
        }

        return (sortedNumbers[middle - 1] + sortedNumbers[middle]) / 2.0;
    }

    // 출력할 숫자를 소수 둘째 자리까지 보기 좋게 변환한다.
    private static String formatNumber(double value) {
        long scaled = (long) (value * 100 + 0.5);
        long integerPart = scaled / 100;
        long decimalPart = scaled % 100;

        if (decimalPart == 0) {
            return String.valueOf(integerPart);
        }

        if (decimalPart % 10 == 0) {
            return integerPart + "." + (decimalPart / 10);
        }

        if (decimalPart < 10) {
            return integerPart + ".0" + decimalPart;
        }

        return integerPart + "." + decimalPart;
    }
}
