package course03.question05;

public abstract class AncientArtifact {

    private final String name;

    public AncientArtifact(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void describe();
}
