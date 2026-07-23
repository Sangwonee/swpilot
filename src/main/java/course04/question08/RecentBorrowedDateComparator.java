package course04.question08;

import java.time.LocalDate;
import java.util.Comparator;

public class RecentBorrowedDateComparator implements Comparator<Book> {

    // 최근 대출 날짜가 최신인 책을 먼저 두고, 대출 기록이 없는 책은 마지막에 둔다.
    @Override
    public int compare(Book first, Book second) {
        LocalDate firstDate = first.getRecentBorrowedDate();
        LocalDate secondDate = second.getRecentBorrowedDate();

        if (firstDate == null && secondDate == null) {
            return first.compareTo(second);
        }
        if (firstDate == null) {
            return 1;
        }
        if (secondDate == null) {
            return -1;
        }

        int result = secondDate.compareTo(firstDate);
        if (result != 0) {
            return result;
        }

        return first.compareTo(second);
    }
}
