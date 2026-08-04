package course07.question01;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlInfoPrinter {
    private UrlInfoPrinter() {
    }

    public static void print(URL url) throws URISyntaxException {
        System.out.println("url.getAuthority(): " + url.getAuthority());
        printContent(url);
        System.out.println("url.getDefaultPort(): " + url.getDefaultPort());
        System.out.println("url.getPort(): " + url.getPort());
        System.out.println("url.getFile(): " + url.getFile());
        System.out.println("url.getHost(): " + url.getHost());
        System.out.println("url.getPath(): " + url.getPath());
        System.out.println("url.getProtocol(): " + url.getProtocol());
        System.out.println("url.getQuery(): " + url.getQuery());
        System.out.println("url.getRef(): " + url.getRef());
        System.out.println("url.getUserInfo(): " + url.getUserInfo());
        System.out.println("url.toExternalForm(): " + url.toExternalForm());
        System.out.println("url.toURI(): " + url.toURI());
    }

    // getContent()가 연 입력 스트림은 출력 후 즉시 닫아 네트워크 자원을 반환한다.
    private static void printContent(URL url) {
        try {
            Object content = url.getContent();
            if (content instanceof InputStream inputStream) {
                try (inputStream) {
                    System.out.println("url.getContent(): " + content);
                }
                return;
            }
            System.out.println("url.getContent(): " + content);
        } catch (IOException e) {
            System.out.println("url.getContent(): 콘텐츠를 가져올 수 없습니다. (" + e.getMessage() + ")");
        }
    }
}
