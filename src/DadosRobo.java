import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DadosRobo {

    private static String statusRobo = "Rampa 3 em execucao";
    private static String statusPrograma = "Habilitado";
    private static String statusComunicacao = "Online";
    private static String statusCamera = "Processando";

    private static int pecasTotal     = 1257;
    private static int pecasVermelhas = 528;
    private static int pecasVerdes    = 440;
    private static int pecasAzuis     = 289;

    private static double tempoCiclo = 4.2;
    private static double taxaAcerto = 98.5;
    private static double oee = 85.0;
    private static double disponibilidade = 92.3;

    private static List<String> logs = new ArrayList<>();
    private static boolean emEmergencia = false;

    static {
        adicionarLog("Sistema iniciado");
        adicionarLog("Camera conectada");
        adicionarLog("CLP online");
        adicionarLog("Robo em automatico");
    }

    public static void adicionarLog(String msg) {
        String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        logs.add(0, hora + " - " + msg);
        if (logs.size() > 50) logs.remove(logs.size() - 1);
    }

    public static List<String> getLogs() { return logs; }

    public static void simularAtualizacao() {
        if (emEmergencia) return;
        java.util.Random r = new java.util.Random();

        int novas = r.nextInt(3);
        if (novas > 0) {
            pecasTotal += novas;
            int tipo = r.nextInt(3);
            if (tipo == 0) pecasVermelhas += novas;
            else if (tipo == 1) pecasVerdes += novas;
            else pecasAzuis += novas;
        }

        tempoCiclo = Math.round((3.8 + r.nextDouble()) * 10.0) / 10.0;
        taxaAcerto = Math.round((96.0 + r.nextDouble() * 4.0) * 10.0) / 10.0;
        oee = Math.round((82.0 + r.nextDouble() * 8.0) * 10.0) / 10.0;
        disponibilidade = Math.round((90.0 + r.nextDouble() * 5.0) * 10.0) / 10.0;

        String[] rampas = {"Rampa 1 em execucao","Rampa 2 em execucao","Rampa 3 em execucao","Aguardando peca"};
        if (r.nextInt(20) == 0) {
            statusRobo = rampas[r.nextInt(rampas.length)];
        }
    }

    public static int    getPecasTotal()       { return pecasTotal; }
    public static int    getPecasVermelhas()   { return pecasVermelhas; }
    public static int    getPecasVerdes()      { return pecasVerdes; }
    public static int    getPecasAzuis()       { return pecasAzuis; }
    public static double getTempoCiclo()       { return tempoCiclo; }
    public static double getTaxaAcerto()       { return taxaAcerto; }
    public static double getOee()              { return oee; }
    public static double getDisponibilidade()  { return disponibilidade; }
    public static String getStatusRobo()       { return statusRobo; }
    public static String getStatusPrograma()   { return statusPrograma; }
    public static String getStatusComunicacao(){ return statusComunicacao; }
    public static String getStatusCamera()     { return statusCamera; }
    public static boolean isEmEmergencia()     { return emEmergencia; }

    public static void acionarEmergencia() {
        emEmergencia = true;
        statusRobo = "PARADA DE EMERGENCIA";
        statusPrograma = "Desabilitado";
        adicionarLog("EMERGENCIA ACIONADA");
    }

    public static void liberarEmergencia() {
        emEmergencia = false;
        statusRobo = "Aguardando reinicio";
        statusPrograma = "Habilitado";
        adicionarLog("Emergencia liberada");
    }
}
