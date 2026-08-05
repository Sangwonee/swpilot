package course07.question03;

public class VitaBiodome03 {
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        VitaminStorageServer server = new VitaminStorageServer(SERVER_PORT);
        server.start();
    }
}
