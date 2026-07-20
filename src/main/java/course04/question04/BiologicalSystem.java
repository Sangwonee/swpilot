package course04.question04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BiologicalSystem<T> {

    private final List<BiologicalEntity<? extends T>> entities = new ArrayList<>();

    public BiologicalSystem() {
        System.out.println("생물정보 시스템이 생성되었습니다.");
    }

    public void add(BiologicalEntity<? extends T> entity) {
        if (entity == null) {
            System.out.println("등록할 생물 정보가 없습니다.");
            return;
        }

        // T의 하위 특징 타입을 허용해 동물, 식물, 미생물을 한 시스템에서 관리한다.
        entities.add(entity);
        System.out.printf("새로운 생물이 등록되었습니다 : %s%n", entity.getName());
    }

    public BiologicalEntity<? extends T> delete() {
        if (entities.isEmpty()) {
            System.out.println("삭제할 생물 정보가 없습니다.");
            return null;
        }

        BiologicalEntity<? extends T> deletedEntity = entities.remove(entities.size() - 1);
        System.out.printf("생물이 삭제되었습니다 : %s%n", deletedEntity.getName());
        return deletedEntity;
    }

    public void clear() {
        if (entities.isEmpty()) {
            System.out.println("삭제할 생물 정보가 없습니다.");
            return;
        }

        entities.clear();
        System.out.println("모든 정보를 삭제했습니다.");
    }

    public BiologicalEntity<? extends T> show() {
        if (entities.isEmpty()) {
            System.out.println("조회할 생물 정보가 없습니다.");
            return null;
        }

        BiologicalEntity<? extends T> latestEntity = entities.get(entities.size() - 1);
        System.out.printf("최신 등록 생물 : %s%n", latestEntity);
        return latestEntity;
    }

    public boolean isEmpty() {
        return entities.isEmpty();
    }

    public void sortByName() {
        // 타입과 관계없이 BiologicalEntity의 공통 이름을 기준으로 오름차순 정렬한다.
        entities.sort((first, second) -> first.getName().compareTo(second.getName()));

        System.out.println("생물 정보를 이름순으로 정렬했습니다.");
        for (BiologicalEntity<? extends T> entity : entities) {
            System.out.println("- " + entity);
        }
    }

    public List<BiologicalEntity<? extends T>> getEntities() {
        return Collections.unmodifiableList(entities);
    }
}
