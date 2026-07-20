package course04.question04.features;

public class MicrobeFeature implements BiologicalFeature {

    private String environment;
    private boolean pathogenic;
    private String metabolism;

    public MicrobeFeature(String environment, boolean pathogenic, String metabolism) {
        this.environment = requireText(environment, "생활 환경");
        this.pathogenic = pathogenic;
        this.metabolism = requireText(metabolism, "대사 방식");
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = requireText(environment, "생활 환경");
    }

    public boolean isPathogenic() {
        return pathogenic;
    }

    public void setPathogenic(boolean pathogenic) {
        this.pathogenic = pathogenic;
    }

    public String getMetabolism() {
        return metabolism;
    }

    public void setMetabolism(String metabolism) {
        this.metabolism = requireText(metabolism, "대사 방식");
    }

    @Override
    public String toString() {
        return String.format(
                "%s, 병원성 %s, %s",
                environment, pathogenic ? "있음" : "없음", metabolism);
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + "은(는) 비어 있을 수 없습니다.");
        }
        return value.trim();
    }
}
