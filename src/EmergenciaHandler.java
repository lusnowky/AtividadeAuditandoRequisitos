import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EmergenciaHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange req) throws IOException {
        if (req.getRequestMethod().equals("OPTIONS")) {
            req.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            req.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
            req.sendResponseHeaders(204, -1);
            return;
        }

        InputStream corpo = req.getRequestBody();
        String acao = new String(corpo.readAllBytes(), "UTF-8").trim();

        if (acao.equals("acionar")) {
            DadosRobo.acionarEmergencia();
        } else if (acao.equals("liberar")) {
            DadosRobo.liberarEmergencia();
        }

        String json = "{\"ok\": true, \"estado\": \""
            + (DadosRobo.isEmEmergencia() ? "emergencia" : "normal") + "\"}";

        req.getResponseHeaders().add("Content-Type", "application/json");
        req.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        byte[] bytes = json.getBytes("UTF-8");
        req.sendResponseHeaders(200, bytes.length);
        OutputStream out = req.getResponseBody();
        out.write(bytes);
        out.close();
    }
}
