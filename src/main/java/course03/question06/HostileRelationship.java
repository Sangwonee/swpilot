package course03.question06;

public class HostileRelationship {
    private final AnimalType frontType;
    private final AnimalType followingType;

    public HostileRelationship(AnimalType frontType, AnimalType followingType) {
        if (frontType == null || followingType == null || frontType == followingType) {
            throw new IllegalArgumentException();
        }

        this.frontType = frontType;
        this.followingType = followingType;
    }

    public AnimalType getFrontType() {
        return frontType;
    }

    public AnimalType getFollowingType() {
        return followingType;
    }
}
