package course07.question05;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletResponse;

class JsonResponseWriter {
    private JsonResponseWriter() {
    }

    static void write(HttpServletResponse response,
         int statusCode, String fieldName, String value) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter writer = response.getWriter();
        writer.printf(
                "{\n  \"%s\": \"%s\"\n}",
                escape(fieldName),
                escape(value));
        writer.flush();
    }

    private static String escape(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\r", "\\r")
                .replace("\n", "\\n");
    }
}
