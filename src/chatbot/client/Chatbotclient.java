package chatbot.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Chatbotclient {

    public static void main(String[] args) {
        while (true) {
            try (Socket socket = new Socket("localhost", 9090);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("");
                System.out.println("");
                System.out.println("Seleccione una opción (1-5) o escriba 'exit' para salir:");
                System.out.println("Opcion 1, saber mi nombre:");
                System.out.println("Opcion 2, saber el dia:");
                System.out.println("Opcion 3, saber la hora:");
                System.out.println("Opcion 4, saber mi ubicación:");
                System.out.println("Opcion 5, saber la IP:");
                String option = scanner.nextLine();
                if (option.equals("exit")) {
                    break;
                }
                out.println(option);
                System.out.println(in.readLine());
            } catch (IOException e) {
                System.out.println("Ha ocurrido un error conectando con el servidor: " + e.getMessage());
                break;
            }
        }
    }
}
