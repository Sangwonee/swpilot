package course03.question09;

import java.util.ArrayList;
import java.util.List;

import course03.question09.menu.Beverage;
import course03.question09.menu.Coffee;
import course03.question09.menu.Menu;
import course03.question09.menu.Sandwich;
import course03.question09.order.Order;
import course03.question09.order.OrderItem;
import course03.question09.order.OrderList;

public class Caffe {
    private final List<Menu> menus;
    private final OrderList orderList;

    public Caffe() {
        this.menus = new ArrayList<>();
        this.orderList = new OrderList();
    }

    public void addMenu(Menu menu) {
        if (menu == null) {
            throw new IllegalArgumentException("추가할 메뉴가 없습니다.");
        }
        if (hasMenu(menu)) {
            throw new IllegalArgumentException("이미 등록된 메뉴입니다.");
        }

        menus.add(menu);
        System.out.println(getMenuType(menu) + "가 추가되었습니다. " + menu.getInfo());
    }

    public void deleteMenu(String name) {
        Menu menu = requireMenu(name);
        menus.remove(menu);
        System.out.println("메뉴가 삭제되었습니다. " + menu.getInfo());
    }

    public Menu findByMenuName(String name) {
        Menu menu = requireMenu(name);
        System.out.println("메뉴 조회 결과: " + menu.getInfo());
        return menu;
    }

    public List<Menu> getAllMenus() {
        return List.copyOf(menus);
    }

    public void printAllMenus() {
        System.out.println("== 전체 메뉴 ==");
        for (Menu menu : menus) {
            System.out.println(menu.getInfo());
        }
    }

    public void receiveOrder(Order order) {
        if (order == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("주문 정보를 다시 확인해주세요.");
        }
        for (OrderItem item : order.getItems()) {
            if (!hasMenu(item.getMenu())) {
                throw new IllegalArgumentException("카페에 등록되지 않은 메뉴입니다.");
            }
        }

        orderList.addOrder(order);
        System.out.println("\n=== 주문이 추가되었습니다. ===");
        order.printInfo();
    }

    public Order provideNextOrder() {
        Order order = orderList.provideNextOrder();
        if (order == null) {
            throw new IllegalStateException("대기 중인 주문이 없습니다.");
        }

        order.startPreparing();
        System.out.println("\n=== 다음 주문의 제조를 시작합니다. ===");
        order.printInfo();
        return order;
    }

    public void completeOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("완료할 주문이 없습니다.");
        }

        order.complete();
        System.out.println("주문 제공이 완료되었습니다: " + order.getCustomerName());
    }

    public void cancelOrder(String customerName, String menuName) {
        Order canceledOrder = orderList.cancelOrder(customerName, menuName);
        System.out.println("주문이 취소되었습니다: "
                + canceledOrder.getCustomerName() + " / " + menuName);
    }

    public void printAllOrders() {
        orderList.printOrderList();
    }

    private boolean hasMenu(Menu menu) {
        for (Menu savedMenu : menus) {
            if (savedMenu.getName().equals(menu.getName())) {
                return true;
            }
        }
        return false;
    }

    private Menu requireMenu(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("메뉴 이름을 입력해주세요.");
        }

        for (Menu menu : menus) {
            if (menu.getName().equals(name)) {
                return menu;
            }
        }

        throw new IllegalArgumentException("메뉴를 찾을 수 없습니다: " + name);
    }

    private String getMenuType(Menu menu) {
        if (menu instanceof Coffee) {
            return "커피";
        }
        if (menu instanceof Beverage) {
            return "음료";
        }
        if (menu instanceof Sandwich) {
            return "샌드위치";
        }
        return "메뉴";
    }

}
