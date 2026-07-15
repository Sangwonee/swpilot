package course03.question09;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import course03.question09.menu.Beverage;
import course03.question09.menu.Coffee;
import course03.question09.menu.Sandwich;
import course03.question09.menu.Size;
import course03.question09.order.Order;
import course03.question09.order.OrderList;
import course03.question09.order.OrderStatus;

class BiodomeFamily09Test {
    private static final LocalDateTime ORDER_TIME =
            LocalDateTime.of(2123, 10, 7, 14, 5, 32);

    @Test
    void 메뉴별_추가_속성을_저장한다() {
        Coffee coffee = new Coffee("블렌드", 4000, "자바 블렌드", Size.TALL);
        Beverage beverage = new Beverage("캐모마일", 3000, Size.SHORT);
        Sandwich sandwich = new Sandwich(
                "야채 샌드위치", 5000, "야채", ORDER_TIME.plusDays(1));

        assertEquals("자바 블렌드", coffee.getBeanType());
        assertEquals(Size.TALL, coffee.getSize());
        assertEquals(Size.SHORT, beverage.getSize());
        assertEquals("야채", sandwich.getIngredient());
    }

    @Test
    void 만료된_샌드위치는_주문할_수_없다() {
        Sandwich expiredSandwich = new Sandwich(
                "치즈 샌드위치", 5500, "치즈", ORDER_TIME.minusDays(1));
        Order order = new Order("제이미", ORDER_TIME);

        assertFalse(expiredSandwich.isSellable(ORDER_TIME));
        assertThrows(IllegalArgumentException.class,
                () -> order.addItem(expiredSandwich));
    }

    @Test
    void 수량을_생략하면_한_개이며_주문_총액을_계산한다() {
        Coffee coffee = new Coffee("블렌드", 4000, "블렌드", Size.TALL);
        Sandwich sandwich = new Sandwich(
                "야채 샌드위치", 5000, "야채", ORDER_TIME.plusDays(1));
        Order order = new Order("제이미", ORDER_TIME);

        order.addItem(coffee, 2);
        order.addItem(sandwich);

        assertEquals(2, order.getItems().get(0).getQuantity());
        assertEquals(1, order.getItems().get(1).getQuantity());
        assertEquals(13000, order.getTotalPrice());
        assertThrows(UnsupportedOperationException.class,
                () -> order.getItems().clear());
    }

    @Test
    void 주문은_접수된_순서대로_제공한다() {
        Beverage beverage = new Beverage("캐모마일", 3000, Size.TALL);
        Order firstOrder = createOrder("제이미", beverage, ORDER_TIME);
        Order secondOrder = createOrder("레냐", beverage, ORDER_TIME.plusMinutes(1));
        OrderList orderList = new OrderList();

        orderList.addOrder(firstOrder);
        orderList.addOrder(secondOrder);

        assertSame(firstOrder, orderList.provideNextOrder());
        assertSame(secondOrder, orderList.provideNextOrder());
        assertTrue(orderList.isEmpty());
    }

    @Test
    void 카페에서_메뉴를_추가하고_조회하고_삭제한다() {
        Caffe caffe = new Caffe();
        Coffee coffee = new Coffee("블렌드", 4000, "블렌드", Size.TALL);

        caffe.addMenu(coffee);

        assertSame(coffee, caffe.findByMenuName("블렌드"));
        assertEquals(1, caffe.getAllMenus().size());

        caffe.deleteMenu("블렌드");

        assertTrue(caffe.getAllMenus().isEmpty());
        assertThrows(IllegalArgumentException.class,
                () -> caffe.findByMenuName("블렌드"));
    }

    @Test
    void 주문_완료_상태의_주문만_취소할_수_있다() {
        Beverage beverage = new Beverage("캐모마일", 3000, Size.TALL);
        Order canceledOrder = createOrder("제이미", beverage, ORDER_TIME);
        Order nextOrder = createOrder("레냐", beverage, ORDER_TIME.plusMinutes(1));
        OrderList orderList = new OrderList();
        orderList.addOrder(canceledOrder);
        orderList.addOrder(nextOrder);

        orderList.cancelOrder("제이미", "캐모마일");

        assertEquals(OrderStatus.CANCELED, canceledOrder.getStatus());
        assertSame(nextOrder, orderList.peekNextOrder());
        assertSame(nextOrder, orderList.provideNextOrder());
        assertThrows(IllegalStateException.class, canceledOrder::cancel);
    }

    private Order createOrder(String customerName, Beverage beverage, LocalDateTime orderTime) {
        Order order = new Order(customerName, orderTime);
        order.addItem(beverage);
        return order;
    }
}
