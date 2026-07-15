package course03.question09.menu;

public class Coffee extends Menu {
    private final String beanType;
    private Size size;

    public Coffee(String name, int price, String beanType, Size size) {
        super(name, price);
        if (beanType == null || beanType.isBlank() || size == null) {
            throw new IllegalArgumentException("원두 종류와 사이즈를 입력해주세요.");
        }

        this.beanType = beanType;
        this.size = size;
    }

    public String getBeanType() {
        return beanType;
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
        return getName() + " 커피 (사이즈: " + size.getName() + ")";
    }

    @Override
    public String getUnit() {
        return "잔";
    }
}
