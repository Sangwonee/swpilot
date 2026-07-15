package course03.question09.order;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrderList {
    private final Queue<Order> orders;

    public OrderList() {
        this.orders = new LinkedList<>();
    }

    public void addOrder(Order order) {
        if (order == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("주문 정보를 다시 확인해주세요.");
        }
        orders.add(order);
    }

    public Order provideNextOrder() {
        while (!orders.isEmpty()) {
            Order order = orders.poll();
            if (order.getStatus() != OrderStatus.CANCELED) {
                return order;
            }
        }
        return null;
    }

    public Order peekNextOrder() {
        for (Order order : orders) {
            if (order.getStatus() != OrderStatus.CANCELED) {
                return order;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public List<Order> getAllOrders() {
        return List.copyOf(orders);
    }

    public int size() {
        return orders.size();
    }

    public Order cancelOrder(String customerName, String menuName) {
        for (Order order : orders) {
            if (order.getCustomerName().equals(customerName)
                    && order.containsMenuName(menuName)) {
                order.cancel();
                return order;
            }
        }

        throw new IllegalArgumentException("취소할 주문을 찾을 수 없습니다.");
    }

    public void printOrderList() {
        System.out.println("== 대기 주문 목록 ==");
        if (orders.isEmpty()) {
            System.out.println("대기 중인 주문이 없습니다.");
            return;
        }

        int orderNumber = 1;
        for (Order order : orders) {
            System.out.println("주문" + orderNumber + ".");
            order.printInfo();
            System.out.println();
            orderNumber++;
        }
    }
}
