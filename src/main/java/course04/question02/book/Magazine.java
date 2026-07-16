package course04.question02.book;

/** 일반 도서와 같은 대출 정책을 사용하는 잡지이다. */
public class Magazine extends Book {

    public Magazine(String title, String author, String isbn) {
        super(title, author, isbn);
    }

    @Override
    public String getBookType() {
        return "잡지";
    }
}
