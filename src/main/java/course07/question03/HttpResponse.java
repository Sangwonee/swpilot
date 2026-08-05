package course07.question03;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

class HttpResponse {
    private static final String HTTP_VERSION = "HTTP/1.1";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    private final int statusCode;
    private final String reasonPhrase;
    private final String body;

    private HttpResponse(int statusCode, String reasonPhrase, String body) {
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
        this.body = body;
    }

    static HttpResponse welcome() {
        return new HttpResponse(200, "OK", createHtml("Welcom to Vitamin Storage :)"));
    }

    static HttpResponse socketInfo(String socketInfo) {
        return new HttpResponse(200, "OK", createHtml(socketInfo));
    }

    static HttpResponse notFound() {
        return new HttpResponse(404, "Not Found", createHtml("Page Not Found :("));
    }

    static HttpResponse internalServerError() {
        return new HttpResponse(500, "Internal Server Error", createHtml("Internal Server Error :o"));
    }

    // UTF-8로 변환한 실제 바이트 수를 Content-Length에 기록한다.
    void writeTo(PrintWriter writer) {
        int contentLength = body.getBytes(StandardCharsets.UTF_8).length;

        writer.print(HTTP_VERSION + " " + statusCode + " " + reasonPhrase + "\r\n");
        writer.print("Content-Type: " + CONTENT_TYPE + "\r\n");
        writer.print("Content-Length: " + contentLength + "\r\n");
        writer.print("Connection: close\r\n");
        writer.print("\r\n");
        writer.print(body);
        writer.flush();
    }

    private static String createHtml(String message) {
        return "<!DOCTYPE html>"
                + "<html><head><meta charset=\"UTF-8\"></head>"
                + "<body><h1>" + message + "</h1></body></html>";
    }
}
