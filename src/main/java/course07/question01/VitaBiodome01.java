package course07.question01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class VitaBiodome01 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("URL 주소를 입력하세요: ");
            String input = reader.readLine();

            URL url = createUrl(input);
            UrlInfoPrinter.print(url);
            DnsLookupService.printAddress(url);
        } catch (IllegalArgumentException | URISyntaxException | MalformedURLException e) {
            System.out.println("올바른 URL 주소를 입력해주세요.");
        } catch (IOException e) {
            System.out.println("입력을 읽는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 예시처럼 URL 사이에 공백이 포함되어도 주소로 해석할 수 있게 제거한다.
    private static URL createUrl(String input) throws URISyntaxException, MalformedURLException {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("URL 주소가 비어 있습니다.");
        }

        String normalizedUrl = input.replaceAll("\\s+", "");
        URI uri = new URI(normalizedUrl);
        if (uri.getScheme() == null || uri.getHost() == null) {
            throw new IllegalArgumentException("프로토콜과 호스트가 포함된 URL이 필요합니다.");
        }
        return uri.toURL();
    }
}
