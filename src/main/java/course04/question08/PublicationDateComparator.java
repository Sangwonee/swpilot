package course04.question08;

import java.util.Comparator;

public class PublicationDateComparator implements Comparator<Book> {

    @Override
    public int compare(Book first, Book second) {
        int result = first.getPublicationDate().compareTo(second.getPublicationDate());
        if (result != 0) {
            return result;
        }

        return first.compareTo(second);
    }
}
