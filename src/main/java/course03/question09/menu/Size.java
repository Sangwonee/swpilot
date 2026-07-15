package course03.question09.menu;

public enum Size {
    SHORT("숏"),
    TALL("톨"),
    VENTI("벤티");

    private final String name;
    
    Size(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
