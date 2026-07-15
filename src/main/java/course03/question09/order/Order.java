package course03.question09.order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import course03.question09.menu.Menu;

public class Order {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final String customerName;
    private final LocalDateTime orderDateTime;
    private final List<OrderItem> items;
    private OrderStatus status;

    public Order(String customerName, LocalDateTime orderDateTime) {
        if (customerName == null || customerName.isBlank() || orderDateTime == null) {
            throw new IllegalArgumentException("고객 이름과 주문 시각을 입력해주세요.");
        }

        this.customerName = customerName;
        this.orderDateTime = orderDateTime;
        this.items = new ArrayList<>();
        this.status = OrderStatus.ORDERED;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public List<OrderItem> getItems() {
        return List.copyOf(items);
    }

    public OrderStatus getStatus() {
        return status;
    }
    
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem item : items) {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }

    public String getInfo() {
        StringBuilder information = new StringBuilder();
        information.append("고객: ").append(customerName).append(System.lineSeparator());
        information.append("주문 시각: ")
                .append(orderDateTime.format(DATE_TIME_FORMATTER))
                .append(System.lineSeparator());
        information.append("상태: ").append(status.getDisplayName())
                .append(System.lineSeparator());

        for (OrderItem item : items) {
            information.append(item.getOrderInfo()).append(System.lineSeparator());
        }

        information.append("총 금액: ")
                .append(String.format("%,d원", getTotalPrice()));
        return information.toString();
    }

    public void printInfo() {
        System.out.println(getInfo());
    }

    public void addItem(Menu menu) {
        addItem(menu, 1);
    }
    
    public void addItem(Menu menu, int quantity) {
        if (status != OrderStatus.ORDERED) {
            throw new IllegalStateException("주문 완료 상태에서만 상품을 추가할 수 있습니다.");
        }
        if (menu == null || quantity <= 0) {
            throw new IllegalArgumentException("주문 정보를 다시 확인해주세요.");
        }

        if (!menu.isSellable(orderDateTime)) {
            throw new IllegalArgumentException(menu.getUnavailableMessage());
        }

        OrderItem orderItem = new OrderItem(menu, quantity);
        items.add(orderItem);
    }

    public boolean containsMenuName(String menuName) {
        for (OrderItem item : items) {
            if (item.getMenu().getName().equals(menuName)) {
                return true;
            }
        }
        return false;
    }

    public void startPreparing() {
        if (status != OrderStatus.ORDERED) {
            throw new IllegalStateException("제조를 시작할 수 없는 주문입니다.");
        }
        status = OrderStatus.PREPARING;
    }

    public void complete() {
        if (status != OrderStatus.PREPARING) {
            throw new IllegalStateException("완료할 수 없는 주문입니다.");
        }
        status = OrderStatus.COMPLETED;
    }

    public void cancel() {
        if (status != OrderStatus.ORDERED) {
            throw new IllegalStateException("주문 완료 상태에서만 취소할 수 있습니다.");
        }
        status = OrderStatus.CANCELED;
    }
}
