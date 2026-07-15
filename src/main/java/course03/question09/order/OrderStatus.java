package course03.question09.order;

public enum OrderStatus {
    ORDERED("주문 완료"),
    PREPARING("제조 중"),
    COMPLETED("완료"),
    CANCELED("취소");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
