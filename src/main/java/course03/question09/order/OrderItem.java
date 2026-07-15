package course03.question09.order;

import course03.question09.menu.Menu;

public class OrderItem {
    private final Menu menu;
    private final int quantity;

    public OrderItem(Menu menu) {
        this(menu, 1);
    }

    public OrderItem(Menu menu, int quantity) {
        if (menu == null || quantity <= 0) {
            throw new IllegalArgumentException("상품과 수량을 다시 확인해주세요.");
        }

        this.menu = menu;
        this.quantity = quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return this.menu.getPrice() * this.quantity;
    }

    public String getOrderInfo() {
        return menu.getOrderDescription()
                + " - " + quantity + menu.getUnit()
                + ": " + String.format("%,d원", getTotalPrice());
    }
}
