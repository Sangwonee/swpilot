package course03.question08.practice;

import course03.question08.Session;

import java.time.LocalDateTime;

public interface PracticeController {
    void cancelSession(Session session);

    void delaySession(Session session, LocalDateTime newSessionDate);
}
