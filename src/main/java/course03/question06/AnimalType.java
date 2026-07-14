package course03.question06;

public enum AnimalType {
    MONKEY("원숭이"),
    TIGER("호랑이"),
    DEER("사슴"),
    ELEPHANT("코끼리"),
    RHINOCEROS("코뿔소"),
    LION("사자");

    private final String displayName;

    AnimalType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
