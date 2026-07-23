package course04.question08;

import java.util.Comparator;

public class TitleLengthComparator implements Comparator<Book> {

    @Override
    public int compare(Book first, Book second) {
        int result = Integer.compare(first.getTitle().length(), second.getTitle().length());
        if (result != 0) {
            return result;
        }

        return first.compareTo(second);
    }
}
