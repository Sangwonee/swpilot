package course03.question08.practice;

import java.time.LocalDateTime;

import course03.question08.Session;

public interface PracticeScheduler {
    Session createSession(LocalDateTime sessionDate, String sessionLocation);
}
