package course03.question08.member;

import java.time.LocalDateTime;

public class NewMember extends Member {
    public NewMember(String name, LocalDateTime joinDate) {
        super(name, joinDate);
    }

    public NewMember(String name, LocalDateTime joinDate, int skillLevel) {
        super(name, joinDate, skillLevel);
    }

    @Override
    public String getRoleName() {
        return "신규 멤버";
    }
}
