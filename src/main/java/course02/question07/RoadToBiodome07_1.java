package course02.question07;

public class RoadToBiodome07_1 {

    private static final String INVALID_INPUT_MESSAGE = "입력값이 올바르지 않습니다. 다시 확인해주세요.";

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
            String[] animalNames = parseAnimalNames(input);
            String[] uniqueAnimalNames = extractUniqueAnimalNames(animalNames);
            int[] observationCounts = countObservations(uniqueAnimalNames, animalNames);

            mergeSortByCountAndName(uniqueAnimalNames, observationCounts);

            System.out.println(formatAnimalNames(uniqueAnimalNames));
        } catch (IllegalArgumentException e) {
            System.out.println(INVALID_INPUT_MESSAGE);
        }
    }

    // 문자열 형태의 배열 입력을 동물 이름 배열로 변환한다.
    private static String[] parseAnimalNames(String input) {
        if (input.charAt(0) != '[' || input.charAt(input.length() - 1) != ']') {
            throw new IllegalArgumentException();
        }

        String arrayText = input.substring(1, input.length() - 1).trim();
        if (arrayText.length() == 0) {
            throw new IllegalArgumentException();
        }

        String[] values = arrayText.split(",", -1);
        String[] animalNames = new String[values.length];

        for (int i = 0; i < values.length; i++) {
            String animalName = removeQuotes(values[i].trim());
            if (!isValidAnimalName(animalName)) {
                throw new IllegalArgumentException();
            }
            animalNames[i] = animalName;
        }

        return animalNames;
    }

    // 동물 이름 양쪽의 따옴표를 제거한다.
    private static String removeQuotes(String text) {
        if (text.length() < 2) {
            return text;
        }

        char firstChar = text.charAt(0);
        char lastChar = text.charAt(text.length() - 1);
        if ((firstChar == '"' && lastChar == '"') || (firstChar == '\'' && lastChar == '\'')) {
            return text.substring(1, text.length() - 1).trim();
        }

        return text;
    }

    // 동물 이름이 비어 있지 않고 숫자나 배열 기호를 포함하지 않는지 확인한다.
    private static boolean isValidAnimalName(String animalName) {
        if (animalName.length() == 0) {
            return false;
        }

        for (int i = 0; i < animalName.length(); i++) {
            char currentChar = animalName.charAt(i);
            if (Character.isDigit(currentChar)
                    || currentChar == '['
                    || currentChar == ']'
                    || currentChar == '"'
                    || currentChar == '\'') {
                return false;
            }
        }

        return true;
    }

    // 입력된 동물 이름 중 중복을 제거한 고유 동물 이름 배열을 만든다.
    public static String[] extractUniqueAnimalNames(String[] animalNames) {
        String[] tempUniqueAnimalNames = new String[animalNames.length];
        int uniqueCount = 0;

        for (int i = 0; i < animalNames.length; i++) {
            if (!contains(tempUniqueAnimalNames, uniqueCount, animalNames[i])) {
                tempUniqueAnimalNames[uniqueCount] = animalNames[i];
                uniqueCount++;
            }
        }

        String[] uniqueAnimalNames = new String[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            uniqueAnimalNames[i] = tempUniqueAnimalNames[i];
        }

        return uniqueAnimalNames;
    }

    // 지정한 범위 안에 찾는 동물 이름이 있는지 확인한다.
    private static boolean contains(String[] animalNames, int length, String targetAnimalName) {
        for (int i = 0; i < length; i++) {
            if (animalNames[i].equals(targetAnimalName)) {
                return true;
            }
        }

        return false;
    }

    // 고유 동물 이름마다 전체 관찰 데이터에 몇 번 등장했는지 계산한다.
    public static int[] countObservations(String[] uniqueAnimalNames, String[] animalNames) {
        int[] observationCounts = new int[uniqueAnimalNames.length];

        for (int i = 0; i < uniqueAnimalNames.length; i++) {
            int count = 0;
            for (int j = 0; j < animalNames.length; j++) {
                if (uniqueAnimalNames[i].equals(animalNames[j])) {
                    count++;
                }
            }
            observationCounts[i] = count;
        }

        return observationCounts;
    }

    // 병합 정렬로 관찰 빈도 내림차순, 빈도수가 같으면 이름 가나다순으로 정렬한다.
    public static void mergeSortByCountAndName(String[] animalNames, int[] observationCounts) {
        if (animalNames.length <= 1) {
            return;
        }

        String[] tempAnimalNames = new String[animalNames.length];
        int[] tempObservationCounts = new int[observationCounts.length];
        mergeSortByCountAndName(animalNames, observationCounts, tempAnimalNames, tempObservationCounts,
                0, animalNames.length - 1);
    }

    // 지정한 구간을 반으로 나눈 뒤 각각 정렬하고 병합한다.
    private static void mergeSortByCountAndName(
            String[] animalNames,
            int[] observationCounts,
            String[] tempAnimalNames,
            int[] tempObservationCounts,
            int left,
            int right) {
        if (left >= right) {
            return;
        }

        int middle = (left + right) / 2;
        mergeSortByCountAndName(animalNames, observationCounts, tempAnimalNames, tempObservationCounts,
                left, middle);
        mergeSortByCountAndName(animalNames, observationCounts, tempAnimalNames, tempObservationCounts,
                middle + 1, right);
        merge(animalNames, observationCounts, tempAnimalNames, tempObservationCounts, left, middle, right);
    }

    // 정렬된 두 구간을 정렬 기준에 맞게 하나의 구간으로 합친다.
    private static void merge(
            String[] animalNames,
            int[] observationCounts,
            String[] tempAnimalNames,
            int[] tempObservationCounts,
            int left,
            int middle,
            int right) {
        int leftIndex = left;
        int rightIndex = middle + 1;
        int tempIndex = left;

        while (leftIndex <= middle && rightIndex <= right) {
            if (shouldComeBefore(animalNames[leftIndex], observationCounts[leftIndex],
                    animalNames[rightIndex], observationCounts[rightIndex])) {
                copyToTemp(animalNames, observationCounts, tempAnimalNames, tempObservationCounts,
                        leftIndex, tempIndex);
                leftIndex++;
            } else {
                copyToTemp(animalNames, observationCounts, tempAnimalNames, tempObservationCounts,
                        rightIndex, tempIndex);
                rightIndex++;
            }
            tempIndex++;
        }

        while (leftIndex <= middle) {
            copyToTemp(animalNames, observationCounts, tempAnimalNames, tempObservationCounts,
                    leftIndex, tempIndex);
            leftIndex++;
            tempIndex++;
        }

        while (rightIndex <= right) {
            copyToTemp(animalNames, observationCounts, tempAnimalNames, tempObservationCounts,
                    rightIndex, tempIndex);
            rightIndex++;
            tempIndex++;
        }

        for (int i = left; i <= right; i++) {
            animalNames[i] = tempAnimalNames[i];
            observationCounts[i] = tempObservationCounts[i];
        }
    }

    // 원본 배열의 한 위치 값을 임시 배열의 한 위치로 복사한다.
    private static void copyToTemp(
            String[] animalNames,
            int[] observationCounts,
            String[] tempAnimalNames,
            int[] tempObservationCounts,
            int sourceIndex,
            int targetIndex) {
        tempAnimalNames[targetIndex] = animalNames[sourceIndex];
        tempObservationCounts[targetIndex] = observationCounts[sourceIndex];
    }

    // 앞 동물이 뒤 동물보다 먼저 출력되어야 하는지 판단한다.
    private static boolean shouldComeBefore(
            String firstAnimalName,
            int firstObservationCount,
            String secondAnimalName,
            int secondObservationCount) {
        if (firstObservationCount != secondObservationCount) {
            return firstObservationCount > secondObservationCount;
        }

        return firstAnimalName.compareTo(secondAnimalName) < 0;
    }

    // 정렬된 동물 이름 배열을 출력 형식에 맞게 변환한다.
    private static String formatAnimalNames(String[] animalNames) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < animalNames.length; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(animalNames[i]);
        }

        return result.toString();
    }
}
