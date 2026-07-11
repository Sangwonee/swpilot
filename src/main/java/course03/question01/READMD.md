수행과제



[최종 결과물]

동식물을 추가/관리/조회할 수 있는 관리 시스템을 객체 지향적으로 설계하고 구현한다.

[과제 목표]

객체 지향 프로그래밍의 기본 개념을 이해한다.

클래스(Class)와 필드(Field), 객체(Instant)의 관계를 이해하고 코드로 구현할 수 있다.

생성자의 역할을 이해하고 구현할 수 있다.

[기능 요구사항]

동물과 식물을 정보를 객체 지향적으로 관리할 수 있다.

동식물을 추가/삭제/조회할 수 있다.

[구현 지침]

프로젝트와 메인 클래스의 이름은 BiodomeFamily01 이다.

동식물의 정보를 관리하는 Organism 클래스를 설계한다.

이름, 종, 주요 서식지 필드를 포함한다.

이름, 종, 주요 서식지를 입력받아 클래스의 객체를 초기화하는 생성자를 구현한다.

동식물의 정보를 출력하는 displayInfo 메서드를 구현한다.

동식물을 관리하는 LifeNest 클래스를 설계한다.

LifeNest 클래스는 Organism 객체를 저장하는 organismList 리스트 필드를 가지고있다.

리스트에 동식물을 추가하거나 삭제하고 전체 동식물을 출력하는 메서드를 포함한다.

동식물이 추가되거나 삭제 될때 수행 작업을 출력해야한다.

메인 클래스의 main 함수에서 동식물 관리 시스템을 시뮬레이션 한다.

2가지 동물 객체와 2가지 식물 객체를 생성한다.

생성된 동식물을 저장소 목록에 저장한다.

모든 동식물을 출력한다.

1가지 동물과 1가지 식물을 삭제한다.

동물의 서식지를 변경한다.

모든 동식물을 출력한다.



[결과 예시] (출력 형식은 아래와 다를 수 있음)

[LifeNest] 펭귄이 추가되었습니다.
[LifeNest] 코알라가 추가되었습니다.
[LifeNest] 선인장이 추가되었습니다.
[LifeNest] 페퍼민트가 추가되었습니다.
전체 동식물 목록 출력:

펭귄, 동물, 남극

코알라, 동물, 호주

선인장, 식물, 사막

페퍼민트, 식물, 정원

[LifeNest] 코알라가 삭제되었습니다.
[LifeNest] 선인장이 삭제되었습니다.

전체 동식물 목록 출력:

펭귄, 동물, 해변

페퍼민트, 식물, 정원

제약사항

새로운 클래스는 별도의 파일에 작성한다.

보너스 과제

LifeNest 클래스에 검색 기능을 추가한다.

searchOrganismByName(String name) 메서드를 추가한다.

메서드는 입력된 이름과 일치하는 동식물의 정보를 출력합니다.

고양이는 동물이며 모든 곳에 서식합니다.

---

평가 가이드

[기본 확인]

프로젝트와 메인 클래스 이름이 BiodomeFamily01 인지 확인한다.

에러나 예기치 않은 종료 없이 프로그램이 실행되어야 한다.

[프로그램 구현 확인]

Organism 클래스가 정의되어 있는지 확인한다.

필요한 필드(이름, 종, 특징, 주요 서식지)를 포함하는지 확인한다.

필드 값을 입력받아 클래스의 새로운 객체를 생성하는 생성자를 구현했는지 확인한다.

동식물의 정보를 출력하는 displayInfo 메서드가 구현되어 있는지 확인한다.

LifeNest 클래스가 정의되어 있는지 확인한다.

Organism 객체를 저장할 수 있는 organismList 리스트 필드를 가지고 있는지 확인한다.

동식물을 추가/삭제/조회할 수 있는 메서드가 모두 구현되어 있는지 확인한다.

[프로그램 동작 확인]

2가지 동물과 2가지 식물이 정상적으로 생성되는지 확인한다.

동식물이 LifeNest 저장소 목록에 추가될 때, 추가 메시지가 출력되는지 확인한다.

동식물이 저장소 목록에서 삭제될 때, 삭제 메시지가 출력되는지 확인한다.

전체 동식물 목록 출력 기능이 올바르게 동작하는지 확인한다.

[개념 확인 질문]

Organism 클래스와 LifeNest 클래스의 각각의 역할은 무엇이며 왜 별도의 클래스가 필요한지 질문한다.=

클래스(Class)와 필드(Field), 객체(Instant)가 무엇인지 질문하고, 작성한 코드에서 각각이 어떻게 사용되었는지 설명 요청한다.

[보너스 문제 확인]

searchOrganismByName(String name) 메서드가 구현되었는지 확인한다..

이름과 일치하는 동식물의 정보를 출력하는지 확인한다.

출력하는 정보의 형식이 아래 예시와 동일한지 확인한다.

고양이은/는 동물이며 모든 곳에 서식합니다.

---

## 개념 확인 질문 정리

### 1. `Organism` 클래스와 `LifeNest` 클래스는 각각 어떤 역할을 하는가?

`Organism` 클래스는 하나의 동물 또는 식물에 대한 정보를 표현한다. 이름, 종, 특징, 주요 서식지를 필드로 저장하며, `displayInfo()`로 자신의 정보를 출력하고 `setHabitat()`으로 서식지를 변경한다.

```java
Organism penguin = new Organism("펭귄", "동물", "추위에 강함", "남극");
```

위 코드에서 `penguin` 객체는 펭귄 한 개체의 정보를 가진다.

`LifeNest` 클래스는 여러 `Organism` 객체를 목록으로 관리한다. `organismList`에 객체를 저장하고 추가, 삭제, 전체 조회, 이름 검색 기능을 제공한다.

```java
lifeNest.addOrganism(penguin);
lifeNest.removeOrganism(koala);
lifeNest.displayAllOrganisms();
lifeNest.searchOrganismByName("펭귄");
```

두 클래스를 분리하면 동식물 한 개체의 정보와 여러 개체를 관리하는 기능의 책임이 섞이지 않는다. 동식물 정보가 변경되면 `Organism`을 수정하고, 목록 관리 방식이 변경되면 `LifeNest`를 수정하면 되므로 코드를 이해하고 확장하기 쉽다.

### 2. 클래스(Class), 필드(Field), 객체(Object 또는 Instance)는 무엇인가?

클래스는 객체를 만들기 위한 설계도이다. 어떤 데이터를 저장하고 어떤 기능을 수행할지 정의한다.

```java
public class Organism {
    // 동식물 객체의 설계 내용
}
```

필드는 객체가 가지는 데이터를 저장하는 변수이다. `Organism`에서는 다음 값들이 필드이다.

```java
private final String name;
private final String species;
private final String characteristic;
private String habitat;
```

객체 또는 인스턴스는 클래스를 바탕으로 메모리에 실제로 생성된 대상이다.

```java
Organism penguin = new Organism("펭귄", "동물", "추위에 강함", "남극");
Organism koala = new Organism("코알라", "동물", "나무 위에서 생활함", "호주");
```

`penguin`과 `koala`는 같은 `Organism` 클래스로 만들었지만 서로 다른 필드 값을 가진 별도의 객체이다.

### 3. 생성자는 어떤 역할을 하는가?

생성자는 `new`로 객체를 만들 때 호출되어 객체의 필드를 처음 설정한다. 생성자 이름은 클래스 이름과 같고 반환형을 작성하지 않는다.

```java
public Organism(String name, String species, String characteristic, String habitat) {
    this.name = name;
    this.species = species;
    this.characteristic = characteristic;
    this.habitat = habitat;
}
```

`this.name`은 현재 생성되는 객체의 필드이고, 오른쪽의 `name`은 생성자로 전달받은 값이다.

### 4. 보너스 검색 기능은 어떻게 동작하는가?

`searchOrganismByName(String name)`은 `organismList`를 처음부터 순회하면서 각 객체의 이름과 검색할 이름을 `equals()`로 비교한다.

```java
if (organism.getName().equals(name)) {
    // 일치하는 동식물 정보 출력
}
```

이름이 일치하면 해당 객체의 종과 서식지를 출력하고 `return`으로 검색을 끝낸다. 목록 끝까지 일치하는 객체를 찾지 못하면 검색 결과가 없다는 문구를 출력한다.
