package course07.question01;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.URL;

public class DnsLookupService {
    private DnsLookupService() {
    }

    public static void printAddress(URL url) {
        try {
            InetAddress address = InetAddress.getByName(url.getHost());
            System.out.println("호스트 이름: " + url.getHost());
            System.out.println("IP 주소: " + address.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("DNS 조회에 실패했습니다: " + url.getHost());
        }
    }
}
