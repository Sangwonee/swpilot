package course03.question09.menu;

import java.time.LocalDateTime;

public class Menu {
    private final String name;
    private int price;

    public Menu(String name, int price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("메뉴 이름을 입력해주세요.");
        }
        validatePrice(price);

        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void updatePrice(int price) {
        validatePrice(price);
        this.price = price;
    }

    public boolean isSellable(LocalDateTime orderTime) {
        return true;
    }

    public String getUnavailableMessage() {
        return "주문할 수 없는 상품입니다.";
    }

    public String getInfo() {
        return name + ": " + String.format("%,d원", price);
    }

    public String getOrderDescription() {
        return name;
    }

    public String getUnit() {
        return "개";
    }

    private void validatePrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("가격은 0원 이상이어야 합니다.");
        }
    }
}
