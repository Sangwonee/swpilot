package course04.question04.features;

public class AnimalFeature implements BiologicalFeature {

    private String behavior;
    private String reproductionMethod;
    private String predator;
    private String prey;
    private int averageLifespan;

    public AnimalFeature(String behavior,String reproductionMethod,String predator,String prey,int averageLifespan) {
        this.behavior = requireText(behavior, "행동 특징");
        this.reproductionMethod = requireText(reproductionMethod, "번식 방법");
        this.predator = requireText(predator, "포식자");
        this.prey = requireText(prey, "피식자");
        this.averageLifespan = requirePositiveLifespan(averageLifespan);
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = requireText(behavior, "행동 특징");
    }

    public String getReproductionMethod() {
        return reproductionMethod;
    }

    public void setReproductionMethod(String reproductionMethod) {
        this.reproductionMethod = requireText(reproductionMethod, "번식 방법");
    }

    public String getPredator() {
        return predator;
    }

    public void setPredator(String predator) {
        this.predator = requireText(predator, "포식자");
    }

    public String getPrey() {
        return prey;
    }

    public void setPrey(String prey) {
        this.prey = requireText(prey, "피식자");
    }

    public int getAverageLifespan() {
        return averageLifespan;
    }

    public void setAverageLifespan(int averageLifespan) {
        this.averageLifespan = requirePositiveLifespan(averageLifespan);
    }

    @Override
    public String toString() {
        return String.format(
                "%s, %s, 포식자: %s, 피식자: %s, 평균 수명: %d년",
                behavior, reproductionMethod, predator, prey, averageLifespan);
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + "은(는) 비어 있을 수 없습니다.");
        }
        return value.trim();
    }

    private static int requirePositiveLifespan(int averageLifespan) {
        if (averageLifespan <= 0) {
            throw new IllegalArgumentException("평균 수명은 0보다 커야 합니다.");
        }
        return averageLifespan;
    }
}
