import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        boolean botaoEmergencia = false; // RF-01
        String statusRobo = "Rampa 3 em execução"; // RF-02
        boolean programaHabilitado = true; // RF-03
        boolean comsOnline = true; // RF-04

        int peçasProcessadas = 1257; // RF-05
        double tempoCiclo = 4.2; // RF-06
        double taxaAcerto = 98.5; // RF-07

        String cameraStatus = "CAM-01 (Ao Vivo)"; // RF-08
        boolean gravandoVideo = true; // RF-09
        String statusProcessamentoCam = "Processando"; // RF-10

        double oee = 85.0; // RF-12
        double dispMecanica = 94.2; // RF-13

        int peçasVermelhas = 600; // RF-14
        int peçasAzuis = 657; // RF-14

        boolean executando = true;

        System.out.println("==================================================");
        System.out.println("          SISTEMA SUPERVISÓRIO INDUSTRIAL         ");
        System.out.println("==================================================");

        while (executando) {
            System.out.println("PAINEL GERAL");
            System.out.println();
            System.out.println("PARADA EMERGÊNCIA: " + (botaoEmergencia ? " ATIVADA " : "NORMAL"));
            System.out.println("Status do Robô: " + statusRobo);
            System.out.println("Programa: " + (programaHabilitado ? "Habilitado" : "Desabilitado"));
            System.out.println("Comunicação: " + (comsOnline ? "Online - Verde" : "Offline - Vermelho"));
            System.out.println("Vídeo: " + cameraStatus + (gravandoVideo ? " [REC]" : "") + " | Status: " + statusProcessamentoCam);
            System.out.println();
            System.out.println("============================");
            System.out.println("MÉTICAS DE PRODUÇÃO");
            System.out.println("Peças Processadas: " + peçasProcessadas + " (Vermelhas: " + peçasVermelhas + " | Azuis: " + peçasAzuis + ")");
            System.out.println("Tempo de Ciclo: " + tempoCiclo + " seg");
            System.out.println("Taxa de Acerto: " + taxaAcerto + "%");
            System.out.println("OEE: " + oee + "% | Disponibilidade: " + dispMecanica + "%");
            System.out.println();
            System.out.println("============================");
            System.out.println("NAVEGAÇÃO (SIDEBAR)");
            System.out.println();
            System.out.println("1. Produzir 1 Peça");
            System.out.println("2. Acionar PARADA DE EMERGÊNCIA"); //RF-01
            System.out.println("3. Resetar Emergência");
            System.out.println("4. Ver Logs em Tempo Real"); //RF-11
            System.out.println("5. Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    if (botaoEmergencia) {
                        System.out.println("!!! ERRO !!! Não é possível produzir a emergência está ativada!");
                        registrarLog("Tentativa de produção bloqueada por Emergência.");
                    } else if (!comsOnline) {
                        System.out.println("!!! ERRO !!! Comunicação Sem Sinal!");
                    } else {
                        peçasProcessadas++;
                        peçasAzuis++;
                        System.out.println("OK! Peça processada com sucesso!");
                        registrarLog("Peça #" + peçasProcessadas + " processada na Rampa 3.");
                    }
                    break;

                case 2:
                    botaoEmergencia = true;
                    statusRobo = "PARADO (EMERGÊNCIA)";
                    programaHabilitado = false;
                    System.out.println("[ALERTA] Botão de Parada de Emergência acionado!");
                    registrarLog("EMERGÊNCIA ACIONADA PELO OPERADOR.");
                    break;

                case 3:
                    botaoEmergencia = false;
                    statusRobo = "Rampa 3 em execução";
                    programaHabilitado = true;
                    System.out.println("Sistema reinicializado com sucesso.");
                    registrarLog("Sistema liberado após parada de emergência.");
                    break;

                case 4:
                    System.out.println(" PAINEL DE LOGS DO ROBÔ "); // RF-11
                    exibirLogs();
                    break;

                case 5:
                    executando = false;
                    System.out.println("Encerrando sessão do supervisório...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            Thread.sleep(800);
        }
    }

    public static void registrarLog(String mensagem) {
        String horaAtual = java.time.LocalTime.now().toString().substring(0, 8);
        System.out.println("[LOG " + horaAtual + "] " + mensagem);
    }

    public static void exibirLogs() {
        String horaAtual = java.time.LocalTime.now().toString().substring(0, 8);
        System.out.println("[" + horaAtual + "] [INFO] Comunicação estabelecida com CAM-01.");
        System.out.println("[" + horaAtual + "] [INFO] Sensor de presença ativado.");
        System.out.println("[" + horaAtual + "] [LOG] Contagem atualizada no supervisório.");
    }
}