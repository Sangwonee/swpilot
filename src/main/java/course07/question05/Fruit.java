package course07.question05;

public class Fruit {
    private final String name;
    private final int price;
    private final boolean farmChanged;
    private final boolean reserved;
    private int requestCount;

    public Fruit(String name, int price, boolean farmChanged, boolean reserved) {
        this.name = name;
        this.price = price;
        this.farmChanged = farmChanged;
        this.reserved = reserved;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public boolean isFarmChanged() {
        return farmChanged;
    }

    public boolean isReserved() {
        return reserved;
    }

    void increaseRequestCount() {
        requestCount++;
    }

    void resetRequestCount() {
        requestCount = 0;
    }
}
