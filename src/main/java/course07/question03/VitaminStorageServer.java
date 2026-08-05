package course07.question03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

class VitaminStorageServer {
    private static final String GET_METHOD = "GET";
    private static final String ROOT_PATH = "/";
    private static final String SOCKET_PATH = "/socket";

    private final int port;

    VitaminStorageServer(int port) {
        this.port = port;
    }

    // 프로그램이 종료될 때까지 연결 요청을 기다리고 클라이언트를 한 명씩 처리한다.
    void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Vitamin Storage 서버가 시작되었습니다.");
            System.out.println("http://localhost:" + port);

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    handleClient(clientSocket);
                } catch (IOException e) {
                    System.out.println("클라이언트 연결 처리 중 오류가 발생했습니다: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("서버를 시작할 수 없습니다: " + e.getMessage());
        }
    }

    // HTTP 요청 헤더의 끝인 빈 줄까지 읽은 뒤 요청에 맞는 응답을 전송한다.
    private void handleClient(Socket clientSocket) {
        try (clientSocket;
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8))) {
            String requestLine = readRequest(reader);
            HttpResponse response = createResponse(requestLine, clientSocket);
            response.writeTo(writer);
        } catch (IOException e) {
            System.out.println("요청을 읽거나 응답하는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    private String readRequest(BufferedReader reader) throws IOException {
        String requestLine = reader.readLine();
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            // 현재 문제에서는 헤더 값을 사용하지 않지만 빈 줄까지 읽어 요청의 끝을 확인한다.
        }
        return requestLine;
    }

    private HttpResponse createResponse(
            String requestLine,
            Socket clientSocket) {
        if (requestLine == null || requestLine.isBlank()) {
            return HttpResponse.notFound();
        }

        String[] requestParts = requestLine.split("\\s+");
        if (requestParts.length < 2) {
            return HttpResponse.notFound();
        }

        String method = requestParts[0];
        String path = requestParts[1];
        if (!GET_METHOD.equals(method)) {
            return HttpResponse.notFound();
        }
        if (ROOT_PATH.equals(path)) {
            return HttpResponse.welcome();
        }
        if (SOCKET_PATH.equals(path)) {
            return createSocketInfoResponse(clientSocket);
        }
        return HttpResponse.notFound();
    }

    // 연결된 클라이언트의 원격 IP와 포트를 조회해 보너스 응답을 만든다.
    private HttpResponse createSocketInfoResponse(Socket clientSocket) {
        try {
            SocketAddress remoteAddress =
                    clientSocket.getRemoteSocketAddress();
            if (!(remoteAddress instanceof InetSocketAddress inetAddress)) {
                return HttpResponse.internalServerError();
            }

            InetAddress address = inetAddress.getAddress();
            if (address == null) {
                return HttpResponse.internalServerError();
            }

            String ipAddress = address.getHostAddress();
            if (ipAddress.contains(":")) {
                ipAddress = "[" + ipAddress + "]";
            }

            String socketInfo = ipAddress
                    + ":" + inetAddress.getPort();
            return HttpResponse.socketInfo(socketInfo);
        } catch (RuntimeException e) {
            return HttpResponse.internalServerError();
        }
    }
}
