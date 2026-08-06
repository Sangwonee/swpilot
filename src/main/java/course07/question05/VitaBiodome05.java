package course07.question05;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VitaBiodome05", urlPatterns = "/*")
public class VitaBiodome05 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CURRENT_TIME_PATH = "/now";
    private static final String RESET_PATH = "/reset";

    private FruitInformationService fruitInformationService;

    @Override
    public void init() {
        fruitInformationService = new FruitInformationService();
    }

    // GET 경로를 현재 시간 조회와 과일 정보 조회로 나누어 처리한다.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = getRequestPath(request);
        if (CURRENT_TIME_PATH.equals(path)) {
            String serverTime = LocalDateTime.now()
                    .withNano(0)
                    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            JsonResponseWriter.write(response, HttpServletResponse.SC_OK, "serverTime", serverTime);
            return;
        }

        String fruitName = removeLeadingSlash(path);
        String message = fruitInformationService.answerQuestion(fruitName);
        JsonResponseWriter.write(response, HttpServletResponse.SC_OK, "message", message);
    }

    // /reset POST 요청만 허용하고 모든 과일 안내 횟수를 초기화한다.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = getRequestPath(request);
        if (RESET_PATH.equals(path)) {
            fruitInformationService.resetRequestCounts();
            JsonResponseWriter.write(response, HttpServletResponse.SC_OK, "message", "Request counts have been reset.");
            return;
        }

        JsonResponseWriter.write(response, HttpServletResponse.SC_NOT_FOUND, "message", "Page Not Found :(");
    }

    private String getRequestPath(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        return requestUri.substring(contextPath.length());
    }

    private String removeLeadingSlash(String path) {
        if (path.startsWith("/")) {
            return path.substring(1);
        }
        return path;
    }
}
