package course04.question04;

import java.util.Objects;

public class BiologicalEntity<T> {

    private String name;
    private String category;
    private T feature;

    public BiologicalEntity(String name, String category, T feature) {
        this.name = requireText(name, "이름");
        this.category = requireText(category, "분류");
        this.feature = requireFeature(feature);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = requireText(name, "이름");
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = requireText(category, "분류");
    }

    public T getFeature() {
        return feature;
    }

    public void setFeature(T feature) {
        this.feature = requireFeature(feature);
    }

    @Override
    public String toString() {
        return name + ", " + category + ", " + feature;
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + "은(는) 비어 있을 수 없습니다.");
        }
        return value.trim();
    }

    private static <T> T requireFeature(T feature) {
        return Objects.requireNonNull(feature, "종별 특징은 비어 있을 수 없습니다.");
    }
}
