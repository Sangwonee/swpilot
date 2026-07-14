package course03.question08;

public enum SessionStatus {
    OPEN("개설"),
    CANCELED("취소");

    private final String displayName;

    SessionStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
