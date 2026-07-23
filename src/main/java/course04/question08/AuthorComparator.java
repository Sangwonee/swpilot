package course04.question08;

import java.util.Comparator;

public class AuthorComparator implements Comparator<Book> {

    @Override
    public int compare(Book first, Book second) {
        int result = first.getAuthor().compareTo(second.getAuthor());
        if (result != 0) {
            return result;
        }

        return first.compareTo(second);
    }
}
