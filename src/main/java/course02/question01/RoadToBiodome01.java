package course02.question01;

import java.util.ArrayList;
import java.util.List;

public class RoadToBiodome01 {

    private static final int MIN_WAVE_NUMBER = 0;
    private static final int MAX_WAVE_NUMBER = 1000;
    private static final String INVALID_INPUT_MESSAGE = "입력된 값이 올바르지 않습니다. 숫자를 입력해주세요.";
    private static final String OUT_OF_RANGE_MESSAGE = "입력된 값의 범위가 올바르지 않습니다. 0에서 1000까지의 값을 입력해주세요.";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(INVALID_INPUT_MESSAGE);
            return;
        }

        List<Integer> waveNumbers = new ArrayList<>();
        String input = String.join(" ", args).trim();

        if (input.length() == 0) {
            System.out.println(INVALID_INPUT_MESSAGE);
            return;
        }

        String normalizedInput = input.replace('[', ' ')
                                      .replace(']', ' ')
                                      .replace(',', ' ')
                                      .trim();

        if (normalizedInput.length() == 0) {
            System.out.println(INVALID_INPUT_MESSAGE);
            return;
        }

        String[] values = normalizedInput.split("\\s+");
        for (int i = 0; i < values.length; i++) {
            int waveNumber;
            try {
                waveNumber = Integer.parseInt(values[i]);
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT_MESSAGE);
                return;
            }

            if (waveNumber < MIN_WAVE_NUMBER || waveNumber > MAX_WAVE_NUMBER) {
                System.out.println(OUT_OF_RANGE_MESSAGE);
                return;
            }

            waveNumbers.add(waveNumber);
        }

        int uniqueNumber = findUniqueNumber(waveNumbers);
        if (uniqueNumber == -1) {
            System.out.println("한 번만 등장하는 값이 없습니다.");
            return;
        }

        System.out.println(uniqueNumber);
    }

    // 리스트를 순회하면서 특정 숫자의 등장 횟수를 파악하는 메서드
    public static int countOccurrences(List<Integer> waveNumbers, int target) {
        int count = 0;
        for (int i = 0; i < waveNumbers.size(); i++) {
            if (waveNumbers.get(i) == target) {
                count++;
            }
        }
        return count;
    }

    // 반복되지 않고 1번만 등장하는 숫자를 찾는 메서드
    public static int findUniqueNumber(List<Integer> waveNumbers) {
        for (int i = 0; i < waveNumbers.size(); i++) {
            int current = waveNumbers.get(i);
            if (countOccurrences(waveNumbers, current) == 1) {
                return current;
            }
        }
        return -1;
    }
}
