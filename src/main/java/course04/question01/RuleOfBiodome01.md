# 도서관 시스템으로 살펴보는 SOLID 원칙

## 목차

1. [SOLID 원칙의 등장과 필요성](#1-solid-원칙의-등장과-필요성)
2. [SOLID 다섯 가지 원칙](#2-solid-다섯-가지-원칙)
3. [도서관 시스템의 SOLID 위반 분석](#3-도서관-시스템의-solid-위반-분석)
4. [개선 방향](#4-개선-방향)
5. [보너스: 과정 2 프로그램 분석](#5-보너스-과정-2-프로그램-분석)
6. [결론](#6-결론)

## 1. SOLID 원칙의 등장과 필요성

객체 지향 프로그램은 기능이 늘어날수록 클래스 사이의 관계가 복잡해진다. 한 클래스가 너무 많은 일을 맡거나 구체적인 구현에 강하게 의존하면 작은 요구사항 하나를 바꿀 때 여러 클래스도 함께 수정해야 한다. 이 과정에서 기존 기능이 고장날 가능성이 커지고 테스트와 재사용도 어려워진다.

SOLID는 이런 문제를 줄이기 위한 다섯 가지 객체 지향 설계 원칙이다. SOLID를 지키면 각 객체의 역할과 변경 이유가 분명해지고, 서로의 내부 구현을 적게 알면서 협력할 수 있다. 그 결과 다음과 같은 이점을 얻을 수 있다.

- **유지보수성:** 변경이 필요한 범위를 좁힐 수 있다.
- **확장성:** 기존 코드를 크게 수정하지 않고 새 기능을 추가하기 쉬워진다.
- **재사용성:** 역할이 잘 분리된 객체를 다른 환경에서도 활용할 수 있다.
- **테스트 용이성:** 작은 단위와 추상화된 의존성을 독립적으로 검증할 수 있다.
- **안정성:** 한 부분의 변경이 다른 부분에 미치는 영향을 줄일 수 있다.

SOLID는 반드시 지켜야 하는 문법 규칙이 아니라 변경에 유연한 구조를 판단하기 위한 설계 지침이다. 따라서 클래스 수를 무조건 늘리기보다 프로그램의 규모와 예상되는 변경 방향에 맞게 적용해야 한다.

## 2. SOLID 다섯 가지 원칙

### 2.1 SRP: 단일 책임 원칙

**Single Responsibility Principle**은 클래스가 하나의 책임만 가져야 한다는 원칙이다. 여기서 책임은 단순히 메서드가 하나여야 한다는 뜻이 아니라, 클래스가 변경되어야 하는 이유가 하나여야 한다는 의미이다.

예를 들어 도서 정보 관리와 도서 집필은 서로 다른 이유로 변경된다. 두 기능이 한 클래스에 있으면 한쪽 요구사항의 변경이 다른 기능에도 영향을 줄 수 있으므로 별도의 역할로 분리하는 편이 좋다.

### 2.2 OCP: 개방-폐쇄 원칙

**Open-Closed Principle**은 소프트웨어 요소가 확장에는 열려 있고 수정에는 닫혀 있어야 한다는 원칙이다. 새 기능을 추가할 때 안정적으로 동작하던 기존 코드의 조건문이나 메서드를 계속 고치는 대신, 인터페이스 구현이나 새로운 클래스를 추가하여 동작을 확장할 수 있어야 한다.

예를 들어 도형별 넓이를 구할 때 계산기가 모든 도형을 `instanceof`로 구분하면 새 도형마다 계산기를 수정해야 한다. 각 도형이 공통 `Shape` 인터페이스의 `calculateArea()`를 구현하게 하면 계산기는 그대로 두고 새 도형을 추가할 수 있다.

### 2.3 LSP: 리스코프 치환 원칙

**Liskov Substitution Principle**은 하위 타입의 객체를 상위 타입의 객체 대신 사용해도 프로그램의 올바른 동작이 유지되어야 한다는 원칙이다. 하위 타입은 상위 타입이 약속한 행위를 정상적으로 제공해야 하며, 기능을 무시하거나 더 강한 사전 조건을 요구하거나 예상하지 못한 예외를 발생시켜서는 안 된다.

### 2.4 ISP: 인터페이스 분리 원칙

**Interface Segregation Principle**은 클라이언트가 자신이 사용하지 않는 메서드에 의존하도록 강요받지 않아야 한다는 원칙이다. 하나의 큰 인터페이스나 추상 클래스를 역할별로 작은 인터페이스로 나누면 구현 클래스는 실제로 필요한 기능만 구현할 수 있다.

이 원칙이 중요한 이유는 불필요한 빈 구현과 예외 처리를 없애고, 한 역할의 변경이 관계없는 구현체까지 전달되는 것을 막아 결합도를 낮추기 때문이다.

### 2.5 DIP: 의존 역전 원칙

**Dependency Inversion Principle**은 고수준 모듈과 저수준 모듈이 모두 구체 클래스가 아닌 추상화에 의존해야 한다는 원칙이다. 또한 추상화가 세부 구현에 의존하는 것이 아니라 세부 구현이 추상화에 의존해야 한다.

예를 들어 관리자가 특정 `Library` 구현을 직접 사용하기보다 `BookCatalog` 같은 인터페이스에 의존하면, 메모리 기반 도서관을 데이터베이스 기반 구현으로 바꾸더라도 관리자 코드를 수정하지 않아도 된다. 의존 객체는 생성자나 메서드 인자로 외부에서 전달할 수 있다.

## 3. 도서관 시스템의 SOLID 위반 분석

분석 대상은 과제 폴더에 제공된 `BiodomeFamily08_Before.java`이다. README에는 제공 파일이 `RuleOfBodome01_before.java`라고 적혀 있으나 실제 저장소의 파일명을 기준으로 분석하였다.

### 3.1 `Library`가 도서 관리와 집필을 함께 담당함 — SRP 위반

```java
class Library {
    private List<Book> books = new ArrayList<>();

    public Book writeBook(String isbn, String title, String author) {
        Book book = new Book(isbn, title, author);
        books.add(book);
        return book;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }
}
```

`Library`는 소장 도서를 추가·삭제하는 **도서 관리 책임**과 새로운 책을 만드는 **집필 책임**을 동시에 가진다. 도서 생성 규칙이 바뀌는 경우와 도서 보관 방식이 바뀌는 경우는 서로 다른 변경 이유이다. 따라서 `writeBook()`은 `Author`, `BookFactory`와 같은 별도의 객체로 옮기고, `Library`는 완성된 `Book`을 관리하도록 분리해야 한다.

### 3.2 `User`가 이용자 기능과 관리자 기능을 함께 정의함 — SRP 위반

```java
abstract class User {
    public void borrowBook(Book book) { /* ... */ }
    public void returnBook(Book book) { /* ... */ }

    abstract void addBook(Book book, Library library);
    abstract void removeBook(Book book, Library library);
}
```

`User`에는 대여·반납이라는 일반 이용자 책임과 도서 추가·삭제라는 관리자 책임이 함께 들어 있다. 두 책임은 권한 정책과 업무 규칙이 서로 다르게 변경될 수 있다. 공통 사용자 정보만 `User`에 두고, 대여 역할과 도서 관리 역할을 별도의 인터페이스로 나누는 것이 적절하다.

### 3.3 `Member`가 상위 타입의 관리 기능을 제공하지 못함 — LSP 위반

```java
class Member extends User {
    public void addBook(Book book, Library library) {
        System.out.println("Member can't add book");
    }

    public void removeBook(Book book, Library library) {
        System.out.println("Member can't remove book");
    }
}
```

`User` 타입은 `addBook()`과 `removeBook()`을 실행할 수 있다고 약속하지만 `Member`로 치환하면 실제 작업이 수행되지 않는다. 호출자는 `User`가 실제로 관리 기능을 수행하는지, 단지 실패 문구만 출력하는지 알 수 없다. 즉 `Member`는 상위 타입의 계약을 만족하지 못하므로 `User`를 안전하게 대체할 수 없다.

이 문제는 `Member`가 잘못된 상속 관계에 놓였기 때문에 발생한다. 모든 사용자가 할 수 있는 기능만 공통 타입에 남기고 관리자 기능은 `Manager`만 구현하도록 해야 한다.

### 3.4 `Member`가 필요 없는 메서드 구현을 강요받음 — ISP 위반

```java
abstract void addBook(Book book, Library library);
abstract void removeBook(Book book, Library library);
```

관리 권한이 없는 `Member`도 `User`를 상속하기 위해 `addBook()`과 `removeBook()`을 억지로 구현해야 한다. 그 결과 아무 일도 하지 않고 메시지만 출력하는 메서드가 생겼다. 이는 클라이언트가 사용하지 않는 기능에 의존하게 만든 명백한 ISP 위반이다.

대여 기능은 `BorrowableUser`, 도서 관리 기능은 `BookManager`처럼 역할별 인터페이스로 분리할 수 있다. 그러면 `Member`는 대여 역할만, `Manager`는 필요한 역할만 선택하여 구현한다.

### 3.5 `Manager`가 구체 클래스 `Library`에 직접 의존함 — DIP 위반

```java
class Manager extends User {
    public void addBook(Book book, Library library) {
        library.addBook(book);
    }

    public void removeBook(Book book, Library library) {
        library.removeBook(book);
    }
}
```

관리 업무를 표현하는 고수준 모듈 `Manager`가 저장 방식까지 가진 구체 클래스 `Library`를 메서드 인자 타입으로 직접 참조한다. 도서 저장소가 데이터베이스나 외부 서비스로 바뀌면 `Manager`도 함께 수정될 수 있다.

`BookCatalog` 또는 `BookRepository` 인터페이스를 정의하고 `Manager`와 `Library`가 모두 그 추상화에 의존하게 해야 한다. 실제 구현은 생성자 주입 등으로 전달하면 구현 교체와 단위 테스트가 쉬워진다.

### 3.6 사용자 종류가 늘 때마다 `Library`를 수정해야 함 — OCP 위반

```java
public void addMember(Member member) {
    users.add(member);
}

public void addManager(Manager manager) {
    users.add(manager);
}
```

`Library`는 `Member`와 `Manager`를 각각 등록하는 메서드를 제공한다. 사서, 임시 회원 등 새로운 `User` 하위 타입이 추가되면 `Library`에도 타입별 등록 메서드를 추가해야 한다. 확장할 때 기존 클래스가 계속 수정되므로 OCP에 어긋난다.

두 메서드를 `addUser(User user)`와 같이 상위 타입을 받는 하나의 메서드로 일반화하면 새 사용자 타입을 추가해도 `Library`는 변경되지 않는다.

## 4. 개선 방향

역할을 다음과 같이 나누면 앞에서 확인한 위반을 함께 개선할 수 있다.

```java
interface BookCatalog {
    void addBook(Book book);
    void removeBook(Book book);
}

interface BookLoanService {
    void borrowBook(Book book, User user);
    void returnBook(Book book, User user);
}

abstract class User {
    private final String userId;
    private final String name;

    protected User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}

class Member extends User {
    public Member(String userId, String name) {
        super(userId, name);
    }
}

class Manager extends User {
    private final BookCatalog catalog;

    public Manager(String userId, String name, BookCatalog catalog) {
        super(userId, name);
        this.catalog = catalog;
    }

    public void addBook(Book book) {
        catalog.addBook(book);
    }
}

class Library implements BookCatalog {
    private final List<Book> books = new ArrayList<>();
    private final List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public void removeBook(Book book) {
        books.remove(book);
    }
}
```

이 구조에서는 `Member`가 관리 메서드를 억지로 구현하지 않으므로 LSP와 ISP 문제가 사라진다. `Manager`는 `BookCatalog`라는 추상화에 의존하므로 DIP를 따르고, `Library.addUser(User)`는 새로운 사용자 타입에도 열려 있으므로 OCP를 만족한다. 대여 정책은 `BookLoanService`, 책 생성은 별도의 `BookFactory`로 분리하여 각 클래스의 변경 이유도 줄일 수 있다.

## 5. 보너스: 과정 2 프로그램 분석

과정 2의 `course02/question09/RoadToBiodome09.java`를 분석하였다.

### 5.1 입력·출력과 경로 탐색이 한 클래스에 모임 — SRP 위반

```java
public class RoadToBiodome09 {
    public static void main(String[] args) { /* 입력 및 출력 */ }
    private static int[][] parseCave(String[] args) { /* 파싱 및 검증 */ }
    public static int calculateShortestDistance(int[][] cave) { /* BFS */ }
    private static boolean canMove(...) { /* 이동 규칙 */ }
}
```

`RoadToBiodome09`는 명령행 입출력, 문자열 파싱과 검증, 동굴 이동 규칙, BFS 최단 거리 계산을 모두 담당한다. 출력 형식이 바뀌거나 입력 형식이 바뀌거나 탐색 알고리즘이 바뀔 때 같은 클래스가 수정되므로 변경 이유가 여러 개이다.

`CaveParser`, `PathFinder`, `MovementPolicy` 등으로 역할을 나누고 `main()`은 객체를 조립해 실행 결과만 출력하도록 만들면 각 기능을 독립적으로 테스트하고 변경할 수 있다.

### 5.2 지형 규칙이 조건문에 고정됨 — OCP 위반

```java
if (terrain != '0' && terrain != '1') {
    throw new IllegalArgumentException();
}

return cave[row][column] == 1 && !visited[row][column];
```

현재 코드는 허용 지형과 이동 가능 여부를 숫자 비교로 고정한다. 보너스 요구사항처럼 동물 서식지 `2`가 추가되거나 새로운 지형과 이동 규칙이 생길 때 `parseCave()`와 `canMove()`를 직접 수정해야 한다.

`MovementPolicy`에 `isValidTerrain(int)`과 `canEnter(int)`를 정의하고 경로 탐색기가 이 추상화에 의존하도록 만들면, 기존 BFS 코드를 바꾸지 않고 새로운 정책 구현을 추가할 수 있다. 이는 OCP뿐 아니라 구체적인 이동 규칙에 대한 결합도도 낮춘다.

## 6. 결론

제공된 도서관 시스템에서는 SRP, OCP, LSP, ISP, DIP 위반을 모두 확인할 수 있었다. 특히 `User`에 모든 권한을 넣은 설계는 `Member`의 무의미한 구현을 만들면서 SRP, LSP, ISP를 동시에 해친다. 역할별 인터페이스를 작게 정의하고, 구체 클래스 대신 추상화에 의존하며, 타입별 조건이나 메서드 대신 다형성을 활용하면 변경의 영향 범위를 줄일 수 있다.

SOLID의 목적은 원칙 자체를 형식적으로 적용하는 데 있지 않다. 서로 다른 변경 이유를 분리하고 객체 사이의 계약을 명확히 하여, 요구사항이 달라져도 기존 기능을 안전하게 유지할 수 있는 구조를 만드는 데 의의가 있다.
