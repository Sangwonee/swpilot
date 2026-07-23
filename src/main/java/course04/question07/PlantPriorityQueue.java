package course04.question07;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PlantPriorityQueue<T extends Comparable<T>> {
    private final List<T> heap = new ArrayList<>();

    // 새 요소를 마지막에 넣고 부모와 비교하며 올려 최소 힙 순서를 복구한다.
    public boolean offer(T element) {
        validateElement(element);

        heap.add(element);
        siftUp(heap.size() - 1);
        return true;
    }

    public boolean add(T element) {
        return offer(element);
    }

    public T peek() {
        if (heap.isEmpty()) {
            return null;
        }

        return heap.get(0);
    }

    // 최우선 요소를 제거한 뒤 마지막 요소를 루트로 옮겨 최소 힙 순서를 복구한다.
    public T poll() {
        if (heap.isEmpty()) {
            return null;
        }

        return removeAt(0);
    }

    public T remove() {
        T removedElement = poll();
        if (removedElement == null) {
            throw new NoSuchElementException("관리 대상 식물이 없습니다.");
        }

        return removedElement;
    }

    public boolean remove(T element) {
        if (element == null) {
            return false;
        }

        int index = heap.indexOf(element);
        if (index < 0) {
            return false;
        }

        removeAt(index);
        return true;
    }

    public void clear() {
        heap.clear();
    }

    public boolean contains(T element) {
        return heap.contains(element);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    // 원본 큐를 변경하지 않고 우선순위 순서대로 요소를 확인할 수 있는 복사본을 만든다.
    public List<T> getElementsInPriorityOrder() {
        PlantPriorityQueue<T> copiedQueue = new PlantPriorityQueue<>();
        copiedQueue.heap.addAll(heap);

        List<T> orderedElements = new ArrayList<>();
        while (!copiedQueue.isEmpty()) {
            orderedElements.add(copiedQueue.poll());
        }

        return orderedElements;
    }

    private T removeAt(int index) {
        int lastIndex = heap.size() - 1;
        T removedElement = heap.get(index);
        T lastElement = heap.remove(lastIndex);

        if (index == lastIndex) {
            return removedElement;
        }

        heap.set(index, lastElement);

        int parentIndex = getParentIndex(index);
        if (index > 0 && compare(index, parentIndex) < 0) {
            siftUp(index);
        } else {
            siftDown(index);
        }

        return removedElement;
    }

    private void siftUp(int index) {
        int currentIndex = index;

        while (currentIndex > 0) {
            int parentIndex = getParentIndex(currentIndex);
            if (compare(currentIndex, parentIndex) >= 0) {
                return;
            }

            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
        }
    }

    private void siftDown(int index) {
        int currentIndex = index;

        while (true) {
            int leftChildIndex = getLeftChildIndex(currentIndex);
            if (leftChildIndex >= heap.size()) {
                return;
            }

            int rightChildIndex = getRightChildIndex(currentIndex);
            int higherPriorityChildIndex = leftChildIndex;

            if (rightChildIndex < heap.size()
                    && compare(rightChildIndex, leftChildIndex) < 0) {
                higherPriorityChildIndex = rightChildIndex;
            }

            if (compare(currentIndex, higherPriorityChildIndex) <= 0) {
                return;
            }

            swap(currentIndex, higherPriorityChildIndex);
            currentIndex = higherPriorityChildIndex;
        }
    }

    private int compare(int firstIndex, int secondIndex) {
        return heap.get(firstIndex).compareTo(heap.get(secondIndex));
    }

    private void swap(int firstIndex, int secondIndex) {
        T temporaryElement = heap.get(firstIndex);
        heap.set(firstIndex, heap.get(secondIndex));
        heap.set(secondIndex, temporaryElement);
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private int getLeftChildIndex(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return parentIndex * 2 + 2;
    }

    private void validateElement(T element) {
        if (element == null) {
            throw new IllegalArgumentException("관리 대상 식물은 null일 수 없습니다.");
        }
    }
}
