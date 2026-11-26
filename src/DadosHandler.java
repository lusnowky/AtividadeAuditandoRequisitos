import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class DadosHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange req) throws IOException {
        DadosRobo.simularAtualizacao();

        String json = montarJson();

        req.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        req.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        byte[] bytes = json.getBytes("UTF-8");
        req.sendResponseHeaders(200, bytes.length);

        OutputStream out = req.getResponseBody();
        out.write(bytes);
        out.close();
    }

    private String montarJson() {
        StringBuilder logsJson = new StringBuilder("[");
        List<String> logs = DadosRobo.getLogs();
        for (int i = 0; i < Math.min(logs.size(), 15); i++) {
            if (i > 0) logsJson.append(",");
            logsJson.append("\"").append(logs.get(i).replace("\"", "'")).append("\"");
        }
        logsJson.append("]");

        return "{"
            + "\"statusRobo\": \"" + DadosRobo.getStatusRobo() + "\","
            + "\"statusPrograma\": \"" + DadosRobo.getStatusPrograma() + "\","
            + "\"statusComunicacao\": \"" + DadosRobo.getStatusComunicacao() + "\","
            + "\"statusCamera\": \"" + DadosRobo.getStatusCamera() + "\","
            + "\"pecasTotal\": " + DadosRobo.getPecasTotal() + ","
            + "\"pecasVermelhas\": " + DadosRobo.getPecasVermelhas() + ","
            + "\"pecasVerdes\": " + DadosRobo.getPecasVerdes() + ","
            + "\"pecasAzuis\": " + DadosRobo.getPecasAzuis() + ","
            + "\"tempoCiclo\": " + DadosRobo.getTempoCiclo() + ","
            + "\"taxaAcerto\": " + DadosRobo.getTaxaAcerto() + ","
            + "\"oee\": " + DadosRobo.getOee() + ","
            + "\"disponibilidade\": " + DadosRobo.getDisponibilidade() + ","
            + "\"emEmergencia\": " + DadosRobo.isEmEmergencia() + ","
            + "\"logs\": " + logsJson.toString()
            + "}";
    }
}
