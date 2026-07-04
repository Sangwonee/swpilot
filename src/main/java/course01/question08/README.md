수행과제

[최종 결과물]

공백 없는 문장을 입력받아 단어기반으로 띄어쓰기한 문장을 출력하는 프로그램을 구현한다.

[과제 목표]

StringBuilder 클래스와 String 클래스의 차이를 이해하고, 이를 사용해 문자열을 효율적으로 처리하는 방법을 연습한다.

배열(Array) 데이터 구조를 이해하고, 이를 활용해 다양한 데이터를 저장하고, 검색하는법을 익힌다.

[기능 요구사항]

사용자로부터 공백없이 이어지는 메시지를 입력 받는다.

메시지는 공백 없이 영문 소문자만으로 구성되어 있다.

사용자가 입력할 수 있는 메시지 최대 길이는 100자이다.

제공되는 사전에 있는 단어를 찾아 메시지에 띄어쓰기를 추가한다.

단어 사전에 포함된 단어는 아래와 같다.
[ hello, where, this, biodome, help, tree, new, is, problem, please, need, we, isn’t,there, a, your, any, thanks, the, for, solution, can, ? ]

[구현 지침]

파일의 이름은 HelloBiodome08.java 이다.

사용자로부터 입력받은 메시지를 저장하는 변수를 생성한다.

제공되는 단어 사전을 배열 변수로 생성한다.

StringBuilder 를 이용해 메시지에 사전 단어가 포함되는지 확인하는 메서드를 구현한다.

메시지가 물음표로 종료되는 경우 이전 단어와 띄어쓰기를 하지 않는다.

메시지가 물음표로 종료되지 않는 경우 마침표(.)를 찍어준다.

사전 단어를 매칭할 때는 가장 긴 단어부터 우선적으로 매칭시킨다.

예) “any”는 a와 any 둘 중 any가 먼저 매칭된다.

단어 사전에 없는 단어는 그대로 출력하되, 앞 뒤의 사전 단어들과 띄어쓰기한다.

canwebelieveinyou?
-> can we believein you?

main 함수에서 띄어쓰기가 추가된 최종 문장을 출력한다.



[결과 예시]

helloisthereanyproblem?
→ hello is there any problem?

weneedyourhelpplease
→ we need your help please.

whereisthenewtree?
→ where is the new tree?

canwebelieveinyou?
-> can we believein you?

제약사항

사용자 입력은 Command Line Arguments 를 이용해 받는다.

Java 기본 제공 HashSet 을 사용하지 않는다.

보너스 과제

한글 단어장을 구성하고, 공백 없는 한글 메시지를 입력 받아 문장을 씌어쓰기하는 프로그램을 구현한다.

안녕하세요새로운나무를발견했습니다
→ 안녕하세요 새로운 나무를 발견했습니다.

신속한지원감사합니다 → 신속한 지원 감사합니다.

당신의신속한도움이필요합니다
→ 당신의 신속한 도움이 필요합니다.



---


평가 가이드

[기본 확인]

파일명이 HelloBiodome08.java 인지 확인한다.

에러나 예기치 않은 종료없이 프로그램이 실행되어야한다.

[프로그램 구현 확인]

사용자가 입력한 메시지를 저장하는 문자열 변수를 생성했는지 확인한다.

제공되는 사전을 배열 변수로 생성했는지 확인한다.

StringBuilder를 이용해 문자를 확인하는 메서드를 구현했는지 확인한다.

append(), chartAt(), length(), insert(), delete() 등 사용

메시지의 종결부분을 확인하여 물음표 또는 마침표를 붙이는 작업을 구현했는지 확인한다.

[프로그램 구동 확인]

아래 문장을 입력해 올바르게 띄어쓰기가 추가되어 출력되는지 확인한다.

helloisthereanyproblem?
→ hello is there any problem?

weneedyourhelpplease
→ we need your help please.

whereisthenewtree?
→ where is the new tree?

canwebelieveinyou?
-> can we believein you?

[개념 확인 질문]

StringBuilder 와 String 클래스의 주요 차이점은 무엇이며, 어떤 경우에 StringBuilder를 사용하는 것이 유리한지 질문한다.

한번 생성된 문자열의 불변 여부 차이를 설명하고, 문자열의 반복적인 조작이 필요한 경우 StringBuilder 를 사용하는 것이 유리함을 설명

입력받은 메시지에 사전에 등장하는 단어가 연속으로 두번 등장하는 경우 어떻게 출력될 것인지 질문하고, 구현한 코드로 테스트해 결과를 비교해본다.

hellohelloisthereanyproblem?


---


# [개념 확인] HelloBiodome08 개념 이해 질문 & 답변 정리

## Q1. StringBuilder와 String 클래스의 주요 차이점은 무엇이며, 어떤 경우에 StringBuilder를 사용하는 것이 유리한가요?
### **답변**
* **문자열의 가변 여부(Mutability) 차이**:
  * **`String`**: **불변(Immutable)** 객체입니다. 한 번 생성되면 값을 변경할 수 없으며, 문자열 연산(`+` 등) 시 기존 데이터가 수정되는 것이 아니라 새로운 메모리 공간에 새로운 String 객체가 계속해서 할당됩니다.
  * **`StringBuilder`**: **가변(Mutable)** 객체입니다. 객체 내부의 버퍼 공간에 문자열을 담고 있어 새로 할당하지 않고도 문자열의 내용을 직접 추가(`append`), 삭제(`delete`), 삽입(`insert`)할 수 있습니다.
* **StringBuilder가 유리한 경우**:
  * 루프 안에서 문자열을 여러 번 더하거나(반복적인 조작), 입력 스트림에서 조각조각 들어오는 텍스트 데이터를 하나로 연결해야 할 때처럼 **문자열의 빈번하고 반복적인 조작이 일어나는 경우**입니다. 불필요한 인스턴스 생성을 막아 가비지 컬렉터(GC) 성능과 메모리 효율을 대폭 향상시킵니다.

---

## Q2. 입력받은 메시지에 사전에 등장하는 단어가 연속으로 두 번 등장하는 경우(`hellohelloisthereanyproblem?`) 어떻게 출력되며, 그 이유는 무엇인가요?
### **답변**
* **출력 결과**: **`hello hello is there any problem?`**
* **이유**:
  1. 구현된 알고리즘은 현재 인덱스 위치에서 사전의 단어들을 긴 순서대로 매칭(Greedy Matching)합니다.
  2. 첫 번째 `"hello"`를 인덱스 0에서 발견하여 매칭한 뒤 인덱스를 5로 전진시키고, 그 뒤 인덱스 5에서 두 번째 `"hello"`를 다시 매칭 성공시킵니다.
  3. 사전에 등장하는 단어들이 연속으로 나열되어 있어도, 각 단어가 완전히 분석된 시점에서 자동으로 단어 사이에 공백 문자(` `)를 보정(삽입)해주기 때문에 문제없이 정상적으로 띄어쓰기가 반영됩니다.
  4. 테스트 확인 결과, 실제 프로그램 실행 값과 예상이 정확히 일치함을 검증하였습니다.


