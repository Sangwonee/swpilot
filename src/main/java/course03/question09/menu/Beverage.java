package course03.question09.menu;

public class Beverage extends Menu {
    private Size size;

    public Beverage(String name, int price, Size size) {
        super(name, price);
        if (size == null) {
            throw new IllegalArgumentException("사이즈를 입력해주세요.");
        }
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public void updateSize(Size size) {
        if (size == null) {
            throw new IllegalArgumentException("사이즈를 입력해주세요.");
        }
        this.size = size;
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }

    @Override
    public String getOrderDescription() {
        return getName() + " (사이즈: " + size.getName() + ")";
    }

    @Override
    public String getUnit() {
        return "잔";
    }
}
