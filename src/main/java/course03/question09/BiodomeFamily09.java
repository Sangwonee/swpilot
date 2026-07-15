package course03.question09;

import course03.question09.menu.Beverage;
import course03.question09.menu.Coffee;
import course03.question09.menu.Sandwich;
import course03.question09.menu.Size;
import course03.question09.order.Order;

import java.time.LocalDateTime;

public class BiodomeFamily09 {
    public static void main(String[] args) {
        try {
            Caffe caffe = new Caffe();

            Coffee blendCoffee = new Coffee("블렌드", 4000, "블렌드", Size.TALL);
            Coffee darkCoffee = new Coffee("다크", 4500, "다크", Size.TALL);
            Coffee decafCoffee = new Coffee("디카페인", 4200, "디카페인", Size.TALL);

            Beverage chamomile = new Beverage("캐모마일", 3000, Size.TALL);
            Beverage orangeJuice = new Beverage("오렌지 주스", 3500, Size.TALL);
            Beverage water = new Beverage("물", 1000, Size.SHORT);

            LocalDateTime firstOrderTime = LocalDateTime.of(2123, 10, 7, 14, 5, 32);
            Sandwich vegetableSandwich = new Sandwich("야채 샌드위치", 5000, "야채", firstOrderTime.plusDays(3));
            Sandwich hamSandwich = new Sandwich("햄 샌드위치", 6000, "햄", firstOrderTime.plusDays(4));
            Sandwich cheeseSandwich = new Sandwich("치즈 샌드위치", 5500, "치즈", firstOrderTime.minusDays(1));

            caffe.addMenu(blendCoffee);
            caffe.addMenu(darkCoffee);
            caffe.addMenu(decafCoffee);
            caffe.addMenu(chamomile);
            caffe.addMenu(orangeJuice);
            caffe.addMenu(water);
            caffe.addMenu(vegetableSandwich);
            caffe.addMenu(hamSandwich);
            caffe.addMenu(cheeseSandwich);

            Order firstOrder = new Order("제이미", firstOrderTime);
            firstOrder.addItem(blendCoffee, 2);
            firstOrder.addItem(vegetableSandwich);
            caffe.receiveOrder(firstOrder);

            LocalDateTime secondOrderTime = LocalDateTime.of(2123, 10, 7, 14, 6, 15);
            Order secondOrder = new Order("레냐", secondOrderTime);
            secondOrder.addItem(chamomile);
            caffe.receiveOrder(secondOrder);

            System.out.println("\n치즈 샌드위치 주문 시도...");
            try {
                Order expiredSandwichOrder = new Order(
                        "제이미",
                        firstOrderTime.plusMinutes(2));
                expiredSandwichOrder.addItem(cheeseSandwich);
                caffe.receiveOrder(expiredSandwichOrder);
            } catch (IllegalArgumentException e) {
                System.out.println("오류: " + e.getMessage());
            }

            System.out.println();
            caffe.printAllOrders();

            caffe.findByMenuName("블렌드");
            caffe.deleteMenu("물");
            caffe.printAllMenus();

            Order providedOrder = caffe.provideNextOrder();
            caffe.completeOrder(providedOrder);

            System.out.println("\n=== 보너스 주문 취소 ===");
            caffe.cancelOrder("레냐", "캐모마일");
            caffe.printAllOrders();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("오류: " + e.getMessage());
        }
    }
}
