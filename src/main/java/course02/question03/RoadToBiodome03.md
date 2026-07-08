# RoadToBiodome03 시간 복잡도 분석 보고서


## 목차

1. 시간 복잡도의 정의와 필요성
2. Big O 표기법의 의미와 중요성
3. 문제01 `RoadToBiodome01.java` 시간 복잡도 분석
4. 문제02 `RoadToBiodome02.java` 시간 복잡도 분석
5. 병목 현상 분석
6. 시간 복잡도 최적화 방안
7. 기존 코드와 최적화 코드의 Big O 비교
8. 개념 확인 질문 답변

---

## 1. 시간 복잡도의 정의와 필요성

시간 복잡도(Time Complexity)는 입력 데이터의 크기가 커질 때, 알고리즘의 실행 시간이 얼마나 증가하는지를 나타내는 개념이다.

예를 들어 입력 데이터가 10개일 때는 빠르게 실행되는 코드라도, 입력 데이터가 1,000,000개가 되면 매우 오래 걸릴 수 있다. 그래서 단순히 “현재 실행이 잘 된다”가 아니라, 입력 크기가 커졌을 때도 효율적으로 동작하는지 분석해야 한다.

시간 복잡도가 필요한 이유는 다음과 같다.

- 입력 크기가 커졌을 때 프로그램 성능을 예측할 수 있다.
- 여러 알고리즘 중 더 효율적인 방법을 선택할 수 있다.
- 실행 시간이 오래 걸리는 병목 구간을 찾을 수 있다.
- 불필요한 반복문이나 중복 계산을 줄이는 기준이 된다.

---

## 2. Big O 표기법의 의미와 중요성

Big O 표기법은 알고리즘의 실행 시간이 입력 크기 `n`에 따라 얼마나 빠르게 증가하는지를 표현하는 방법이다.

Big O는 정확한 실행 시간을 초 단위로 나타내는 것이 아니라, 입력 크기가 커질 때 실행 횟수가 증가하는 큰 흐름을 나타낸다. 그래서 상수나 작은 차이는 생략하고 가장 영향력이 큰 항만 표시한다.

예를 들어 다음 코드는 입력 크기 `n`만큼 한 번 반복한다.

```java
for (int i = 0; i < n; i++) {
    System.out.println(i);
}
```

이 경우 시간 복잡도는 `O(n)`이다.

대표적인 Big O 예시는 다음과 같다.

| 표기 | 의미 | 예시 |
| :--- | :--- | :--- |
| `O(1)` | 입력 크기와 관계없이 일정한 시간 | 배열의 특정 인덱스 접근 |
| `O(n)` | 입력 크기에 비례해서 증가 | 배열 전체를 한 번 순회 |
| `O(n^2)` | 입력 크기의 제곱에 비례해서 증가 | 중첩 반복문으로 모든 쌍 비교 |
| `O(log n)` | 입력 크기가 커져도 완만하게 증가 | 이진 탐색 |
| `O(n log n)` | 선형보다 크지만 제곱보다는 작음 | 효율적인 정렬 알고리즘 |

Big O 표기법이 중요한 이유는 입력 크기가 커질수록 알고리즘 간 성능 차이가 매우 커지기 때문이다. 예를 들어 `n = 1,000,000`일 때 `O(n)`은 약 백만 번의 연산으로 끝나지만, `O(n^2)`은 약 1조 번의 연산이 필요할 수 있다.

---

## 3. 문제01 `RoadToBiodome01.java` 시간 복잡도 분석

문제01은 여러 숫자 중에서 한 번만 등장하는 숫자를 찾는 프로그램이다.

입력 데이터 개수를 `n`이라고 하겠다.

### 3.1 입력 문자열 처리

```java
String input = String.join(" ", args).trim();
String normalizedInput = input.replace('[', ' ')
                              .replace(']', ' ')
                              .replace(',', ' ')
                              .trim();
String[] values = normalizedInput.split("\\s+");
```

입력 문자열 전체 길이를 `m`이라고 하면, 문자열을 합치고, 치환하고, 나누는 작업은 문자열 전체를 순회하므로 대략 `O(m)`이다.

숫자 개수를 기준으로 보면 입력 전체를 한 번씩 처리하는 것이므로 `O(n)`으로 볼 수 있다.

### 3.2 숫자 파싱 및 리스트 저장

```java
for (int i = 0; i < values.length; i++) {
    int waveNumber = Integer.parseInt(values[i]);
    waveNumbers.add(waveNumber);
}
```

`values` 배열에 들어 있는 숫자를 한 번씩 확인하므로 시간 복잡도는 `O(n)`이다.

### 3.3 특정 숫자의 등장 횟수 세기

```java
public static int countOccurrences(List<Integer> waveNumbers, int target) {
    int count = 0;
    for (int i = 0; i < waveNumbers.size(); i++) {
        if (waveNumbers.get(i) == target) {
            count++;
        }
    }
    return count;
}
```

`countOccurrences()`는 리스트 전체를 처음부터 끝까지 한 번 순회한다. 따라서 시간 복잡도는 `O(n)`이다.

### 3.4 한 번만 등장하는 숫자 찾기

```java
public static int findUniqueNumber(List<Integer> waveNumbers) {
    for (int i = 0; i < waveNumbers.size(); i++) {
        int current = waveNumbers.get(i);
        if (countOccurrences(waveNumbers, current) == 1) {
            return current;
        }
    }
    return -1;
}
```

`findUniqueNumber()`는 리스트를 순회하면서 각 숫자마다 `countOccurrences()`를 호출한다.

- 바깥 반복문: 최대 `n`번 반복
- 안쪽의 `countOccurrences()`: 호출될 때마다 `n`번 반복

따라서 최악의 경우 시간 복잡도는 다음과 같다.

```text
O(n) * O(n) = O(n^2)
```

즉, 문제01의 핵심 알고리즘은 최악의 경우 `O(n^2)`이다.

### 3.5 문제01 전체 시간 복잡도

입력 처리 자체는 `O(n)`이지만, 한 번만 등장하는 숫자를 찾는 과정이 `O(n^2)`이므로 전체 시간 복잡도는 더 큰 항인 `O(n^2)`이다.

```text
문제01 전체 시간 복잡도: O(n^2)
```

공간 복잡도는 입력 숫자를 `ArrayList`에 저장하므로 `O(n)`이다.

---

## 4. 문제02 `RoadToBiodome02.java` 시간 복잡도 분석

문제02는 입력 문자열을 직접 구현한 스택에 넣고, 다시 꺼내면서 문자열을 역순으로 출력하는 프로그램이다.

입력 문자열 길이를 `n`이라고 하겠다.

### 4.1 입력 문자열 생성

```java
String input = String.join(" ", args).trim();
```

입력 문자열을 합치는 과정은 입력 전체 길이만큼 처리하므로 `O(n)`이다.

### 4.2 입력값 유효성 검사

```java
private static boolean isValidInput(String input) {
    int len = input.length();
    for (int i = 0; i < len; i++) {
        char c = input.charAt(i);
        if (!isValidChar(c)) {
            return false;
        }
    }
    return true;
}
```

문자열의 각 문자를 한 번씩 검사한다. 따라서 시간 복잡도는 `O(n)`이다.

`isValidChar()` 내부에서는 영어, 숫자, 한글, 공백 여부를 비교한다. 이 비교는 입력 크기와 관계없이 일정한 횟수만 수행하므로 `O(1)`이다.

따라서 `isValidInput()` 전체는 `O(n)`이다.

### 4.3 회문 검사

```java
private static boolean isPalindrome(String str) {
    int len = str.length();
    for (int i = 0; i < len / 2; i++) {
        char left = Character.toLowerCase(str.charAt(i));
        char right = Character.toLowerCase(str.charAt(len - 1 - i));
        if (left != right) {
            return false;
        }
    }
    return true;
}
```

문자열의 앞과 뒤를 비교하면서 절반 정도만 순회한다. 절반만 순회해도 Big O 표기법에서는 상수를 생략하므로 시간 복잡도는 `O(n)`이다.

### 4.4 스택에 문자 push

```java
CustomCharStack stack = new CustomCharStack(input.length());
for (int i = 0; i < input.length(); i++) {
    stack.push(input.charAt(i));
}
```

입력 문자열의 각 문자를 한 번씩 스택에 넣으므로 시간 복잡도는 `O(n)`이다.

`push()` 자체는 배열의 특정 위치에 값을 넣는 작업이므로 `O(1)`이다.

```java
public void push(char c) {
    elements[++top] = c;
}
```

### 4.5 스택에서 문자 pop 후 역순 배열 생성

```java
char[] reversedChars = new char[input.length()];
int index = 0;
while (!stack.isEmpty()) {
    reversedChars[index++] = stack.pop();
}
```

스택에 들어 있는 문자 `n`개를 모두 꺼내므로 시간 복잡도는 `O(n)`이다.

`pop()`과 `isEmpty()`는 모두 배열 인덱스와 `top` 값만 사용하므로 각각 `O(1)`이다.

### 4.6 char 배열을 String으로 변환

```java
String reversedString = new String(reversedChars);
```

`char[]` 배열의 문자들을 사용해 새로운 문자열을 만들기 때문에 배열 길이만큼 처리한다. 따라서 시간 복잡도는 `O(n)`이다.

### 4.7 문제02 전체 시간 복잡도

문제02는 여러 단계가 있지만, 각 단계가 모두 `O(n)`이다.

```text
입력 생성: O(n)
유효성 검사: O(n)
회문 검사: O(n)
push: O(n)
pop: O(n)
String 생성: O(n)
```

이를 모두 더하면 다음과 같다.

```text
O(n) + O(n) + O(n) + O(n) + O(n) + O(n) = O(6n)
```

Big O 표기법에서는 상수를 제거하므로 최종 시간 복잡도는 `O(n)`이다.

```text
문제02 전체 시간 복잡도: O(n)
```

공간 복잡도는 스택 배열 `char[] elements`와 결과 배열 `char[] reversedChars`를 사용하므로 `O(n)`이다.

---

## 5. 병목 현상 분석

### 5.1 문제01의 병목

문제01의 병목은 `findUniqueNumber()`에서 발생한다.

```java
for (int i = 0; i < waveNumbers.size(); i++) {
    int current = waveNumbers.get(i);
    if (countOccurrences(waveNumbers, current) == 1) {
        return current;
    }
}
```

이 코드는 각 숫자마다 리스트 전체를 다시 순회한다. 즉, 이미 확인한 숫자의 등장 횟수도 반복해서 다시 계산한다.

예를 들어 리스트 크기가 10,000개라면, 최악의 경우 다음과 같은 형태가 된다.

```text
10,000개 숫자 각각에 대해 10,000개 전체를 다시 확인
```

따라서 최대 약 100,000,000번의 비교가 발생할 수 있다. 이것이 문제01의 가장 큰 병목이다.

### 5.2 문제02의 병목

문제02는 전체 시간 복잡도가 `O(n)`이므로 문제01처럼 큰 병목은 없다.

다만 입력 문자열을 여러 번 순회한다.

1. 유효성 검사에서 한 번 순회
2. 회문 검사에서 한 번 순회
3. 스택에 push하면서 한 번 순회
4. pop하면서 한 번 순회
5. `String` 생성 시 한 번 순회

각 단계는 모두 `O(n)`이므로 전체 Big O는 여전히 `O(n)`이다. 하지만 입력 문자열 최대 길이가 1,000,000까지 가능하므로 실제 실행 시간과 메모리 사용량을 줄이려면 불필요한 순회를 줄이는 방식으로 개선할 수 있다.

---

## 6. 시간 복잡도 최적화 방안

### 6.1 문제01 최적화 방안

문제01은 파동수의 범위가 `0`부터 `1000`까지로 제한되어 있다. 따라서 `HashMap`이나 `HashSet`을 사용하지 않고도 `int[]` 배열을 이용해 등장 횟수를 기록할 수 있다.

기존 방식은 숫자 하나마다 전체 리스트를 다시 확인했다.

```text
기존 방식: 각 숫자마다 전체 리스트 순회 → O(n^2)
```

최적화 방식은 다음과 같다.

1. 크기가 1001인 `int[] counts` 배열을 만든다.
2. 입력 숫자를 한 번 순회하면서 `counts[숫자]++`를 수행한다.
3. 입력 숫자를 다시 한 번 순회하면서 `counts[숫자] == 1`인 값을 찾는다.

예시 코드는 다음과 같다.

```java
public static int findUniqueNumberOptimized(List<Integer> waveNumbers) {
    int[] counts = new int[1001];

    for (int i = 0; i < waveNumbers.size(); i++) {
        counts[waveNumbers.get(i)]++;
    }

    for (int i = 0; i < waveNumbers.size(); i++) {
        int current = waveNumbers.get(i);
        if (counts[current] == 1) {
            return current;
        }
    }

    return -1;
}
```

이 방식은 리스트를 두 번만 순회한다.

```text
O(n) + O(n) = O(2n) → O(n)
```

따라서 문제01의 시간 복잡도를 `O(n^2)`에서 `O(n)`으로 줄일 수 있다.

공간은 `counts` 배열을 추가로 사용한다. 하지만 배열 크기가 항상 1001로 고정되어 있으므로 입력 크기 `n`에 비례하지 않는다. 따라서 추가 공간은 Big O 기준으로 `O(1)`이다.

### 6.2 문제02 최적화 방안

문제02는 이미 전체 시간 복잡도가 `O(n)`이므로 Big O 자체를 더 낮추기는 어렵다. 문자열 전체를 역순으로 만들려면 최소한 각 문자를 한 번은 확인해야 하기 때문이다.

다만 실제 실행 횟수는 줄일 수 있다.

개선 가능한 부분은 다음과 같다.

- 유효성 검사와 회문 검사를 일부 통합한다.
- 스택을 사용해야 한다는 요구사항이 없다면, 뒤에서부터 직접 `char[]`에 넣어 역순 문자열을 만들 수 있다.
- 그러나 문제 요구사항에서 직접 구현한 스택을 사용하라고 했으므로, 스택 기반 구조를 유지하는 것이 과제 목적에 더 적합하다.

문제02의 핵심은 스택 학습이므로 시간 복잡도 최적화보다는 `push()`, `pop()`, `isEmpty()`가 모두 `O(1)`로 동작하도록 구현한 점이 중요하다.

---

## 7. 기존 코드와 최적화 코드의 Big O 비교

| 문제 | 기존 시간 복잡도 | 병목 | 최적화 방법 | 최적화 후 시간 복잡도 |
| :--- | :--- | :--- | :--- | :--- |
| 문제01 | `O(n^2)` | 각 숫자마다 전체 리스트를 다시 순회 | `int[] counts` 배열로 등장 횟수 저장 | `O(n)` |
| 문제02 | `O(n)` | 큰 병목 없음. 문자열을 여러 번 순회 | 일부 순회 통합 가능 | `O(n)` |

문제01은 알고리즘 구조를 바꾸면 시간 복잡도를 크게 줄일 수 있다. 반면 문제02는 이미 선형 시간인 `O(n)`이므로 Big O 관점에서는 충분히 효율적이다.

---

## 8. 개념 확인 질문 답변

### 8.1 Big O 표기법의 의미와 중요성

Big O 표기법은 입력 크기 `n`이 커질 때 알고리즘의 실행 시간이 얼마나 증가하는지를 표현하는 표기법이다.

중요한 이유는 다음과 같다.

- 코드의 성능을 입력 크기 기준으로 예측할 수 있다.
- 더 효율적인 알고리즘을 선택할 수 있다.
- 입력이 커질 때 문제가 되는 병목 구간을 찾을 수 있다.
- 단순히 현재 실행되는지가 아니라, 큰 데이터에서도 실행 가능한지를 판단할 수 있다.

예를 들어 `O(n)` 알고리즘은 입력이 2배가 되면 실행 시간도 대략 2배가 된다. 하지만 `O(n^2)` 알고리즘은 입력이 2배가 되면 실행 시간이 대략 4배가 된다. 따라서 입력 크기가 커질수록 두 알고리즘의 차이는 매우 커진다.

### 8.2 문제01 코드에서 병목이 발생하는 부분

문제01의 병목은 `RoadToBiodome01.java`의 `findUniqueNumber()`와 `countOccurrences()` 조합이다.

```java
public static int findUniqueNumber(List<Integer> waveNumbers) {
    for (int i = 0; i < waveNumbers.size(); i++) {
        int current = waveNumbers.get(i);
        if (countOccurrences(waveNumbers, current) == 1) {
            return current;
        }
    }
    return -1;
}
```

`findUniqueNumber()`가 리스트를 순회하면서 매번 `countOccurrences()`를 호출한다.

```java
public static int countOccurrences(List<Integer> waveNumbers, int target) {
    int count = 0;
    for (int i = 0; i < waveNumbers.size(); i++) {
        if (waveNumbers.get(i) == target) {
            count++;
        }
    }
    return count;
}
```

`countOccurrences()`도 리스트 전체를 다시 순회한다. 따라서 반복문 안에서 다시 반복문이 실행되는 구조가 되어 최악의 경우 `O(n^2)`이 된다.

이를 줄이려면 파동수 범위가 `0~1000`이라는 조건을 활용해 `int[] counts = new int[1001]` 배열에 등장 횟수를 미리 저장하면 된다. 그러면 전체 리스트를 두 번만 순회하면 되므로 `O(n)`으로 줄일 수 있다.

### 8.3 문제02 코드에서 병목이 발생하는 부분

문제02는 전체 시간 복잡도가 `O(n)`이므로 심각한 병목은 없다.

각 핵심 함수의 시간 복잡도는 다음과 같다.

- `isValidInput()` : `O(n)`
- `isPalindrome()` : `O(n)`
- 스택 `push()` 반복 : `O(n)`
- 스택 `pop()` 반복 : `O(n)`
- `new String(reversedChars)` : `O(n)`

전체적으로 여러 번 순회하지만 모두 선형 시간이므로 최종 시간 복잡도는 `O(n)`이다.

다만 입력 문자열이 매우 길 경우 실제 실행 시간을 조금 줄이고 싶다면 유효성 검사와 회문 검사를 함께 수행하거나, 스택 요구사항이 없는 상황에서는 뒤에서부터 직접 배열에 문자를 저장하는 방식으로 개선할 수 있다.

하지만 이 문제의 목표가 스택 구조를 직접 구현하는 것이므로, 현재처럼 `push()`와 `pop()`을 이용해 역순 문자열을 만드는 방식이 과제 목적에 잘 맞는다.
