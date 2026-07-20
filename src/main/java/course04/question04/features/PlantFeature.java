package course04.question04.features;

public class PlantFeature implements BiologicalFeature {

    private String flowerColor;
    private boolean hasFruit;
    private String bloomingSeason;

    public PlantFeature(String flowerColor, boolean hasFruit, String bloomingSeason) {
        this.flowerColor = requireText(flowerColor, "꽃 색상");
        this.hasFruit = hasFruit;
        this.bloomingSeason = requireText(bloomingSeason, "개화 시기");
    }

    public String getFlowerColor() {
        return flowerColor;
    }

    public void setFlowerColor(String flowerColor) {
        this.flowerColor = requireText(flowerColor, "꽃 색상");
    }

    public boolean hasFruit() {
        return hasFruit;
    }

    public void setHasFruit(boolean hasFruit) {
        this.hasFruit = hasFruit;
    }

    public String getBloomingSeason() {
        return bloomingSeason;
    }

    public void setBloomingSeason(String bloomingSeason) {
        this.bloomingSeason = requireText(bloomingSeason, "개화 시기");
    }

    @Override
    public String toString() {
        return String.format(
                "%s, 열매 %s, 개화 시기: %s",
                flowerColor, hasFruit ? "있음" : "없음", bloomingSeason);
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + "은(는) 비어 있을 수 없습니다.");
        }
        return value.trim();
    }
}
