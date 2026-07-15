package course03.question09.menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sandwich extends Menu {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final String ingredient;
    private final LocalDateTime expirationDate;

    public Sandwich(String name, int price, String ingredient, LocalDateTime expirationDate) {
        super(name, price);
        if (ingredient == null || ingredient.isBlank() || expirationDate == null) {
            throw new IllegalArgumentException("재료와 만료날짜를 입력해주세요.");
        }

        this.ingredient = ingredient;
        this.expirationDate = expirationDate;
    }

    public String getIngredient() {
        return ingredient;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String getInfo() {
        return super.getInfo()
                + " (재료: " + ingredient
                + ", 만료일: " + expirationDate.format(DATE_FORMATTER) + ")";
    }

    public boolean isExpired(LocalDateTime orderTime) {
        if (orderTime == null) {
            throw new IllegalArgumentException("주문 시각을 입력해주세요.");
        }
        return expirationDate.isBefore(orderTime);
    }

    @Override
    public boolean isSellable(LocalDateTime orderTime) {
        return !isExpired(orderTime);
    }

    @Override
    public String getUnavailableMessage() {
        return "주문할 수 없는 상품입니다. (만료날짜: "
                + expirationDate.format(DATE_FORMATTER) + ")";
    }

}
