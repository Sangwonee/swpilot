package course04.question05;

import java.util.Objects;

public class PlantHashMap<K, V> {

    private static final int CAPACITY = 16;

    private final Entry<K, V>[] entries;
    private int size;

    @SuppressWarnings("unchecked")
    public PlantHashMap() {
        // Java에서는 제네릭 배열을 직접 생성할 수 없어 Entry 배열을 생성한 뒤 타입을 지정한다.
        entries = (Entry<K, V>[]) new Entry[CAPACITY];
    }

    public void put(K key, V value) {
        requireKey(key);
        Objects.requireNonNull(value, "식물 특징은 비어 있을 수 없습니다.");

        int index = getIndex(key);
        Entry<K, V> current = entries[index];

        if (current == null) {
            entries[index] = new Entry<>(key, value);
            size++;
            printAdded(key, value, index, false);
            return;
        }

        // 같은 키는 값을 수정하고, 다른 키의 해시 충돌은 체이닝으로 연결한다.
        while (true) {
            if (current.key.equals(key)) {
                current.value = value;
                System.out.printf("'%s' 수정: '%s'%n", key, value);
                return;
            }
            if (current.next == null) {
                current.next = new Entry<>(key, value);
                size++;
                printAdded(key, value, index, true);
                return;
            }
            current = current.next;
        }
    }

    public V get(K key) {
        requireKey(key);

        int index = getIndex(key);
        Entry<K, V> current = entries[index];

        // 같은 인덱스의 체인을 순회하면서 hashCode가 아닌 실제 키가 같은 항목을 찾는다.
        while (current != null) {
            if (current.key.equals(key)) {
                System.out.printf("'%s' 검색: '%s'%n", key, current.value);
                return current.value;
            }
            current = current.next;
        }

        System.out.printf("'%s'에 해당하는 식물 정보가 없습니다.%n", key);
        return null;
    }

    public V remove(K key) {
        requireKey(key);

        int index = getIndex(key);
        Entry<K, V> current = entries[index];
        Entry<K, V> previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                // 첫 항목 삭제와 체인 중간 항목 삭제의 연결 방식을 구분한다.
                if (previous == null) {
                    entries[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;
                System.out.printf("'%s' 삭제: '%s'와 그 특징이 삭제되었습니다.%n", key, key);
                return current.value;
            }

            previous = current;
            current = current.next;
        }

        System.out.printf("'%s'은(는) 삭제할 수 없습니다. 등록된 식물 정보가 없습니다.%n", key);
        return null;
    }

    public int getIndex(K key) {
        requireKey(key);
        return Math.floorMod(key.hashCode(), CAPACITY);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void printAdded(K key, V value, int index, boolean chained) {
        if (chained) {
            System.out.printf(
                    "'%s' 추가: '%s' (인덱스 %d에서 체이닝)%n", key, value, index);
            return;
        }
        System.out.printf("'%s' 추가: '%s'%n", key, value);
    }

    private void requireKey(K key) {
        Objects.requireNonNull(key, "식물 이름은 비어 있을 수 없습니다.");
    }

    private static class Entry<K, V> {

        private final K key;
        private V value;
        private Entry<K, V> next;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
