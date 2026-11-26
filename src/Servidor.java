import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;

public class Servidor {

    public static void main(String[] args) throws IOException {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            System.setErr(new PrintStream(System.err, true, "UTF-8"));
        } catch (Exception e) {}

        HttpServer servidor = HttpServer.create(new InetSocketAddress(8080), 0);
        servidor.createContext("/", new PaginaHandler());
        servidor.createContext("/api/dados", new DadosHandler());
        servidor.createContext("/api/emergencia", new EmergenciaHandler());
        servidor.start();
        System.out.println("servidor rodando em http://localhost:8080");
    }
}
