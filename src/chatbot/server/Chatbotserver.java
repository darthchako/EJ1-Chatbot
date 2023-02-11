package chatbot.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Chatbotserver extends Thread{

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9090)) {
            System.out.println("Chatbot se esta ejecutando en el puerto 9090");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
                System.out.println("Nuevo cliente conectado");    
            }
        } catch (IOException e) {
            System.out.println("Error durante el inicio del servidor: " + e.getMessage());
        }
    }
    
    static class ClientHandler implements Runnable {
        private Socket clientSocket;

        ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                String option = in.readLine();
                switch (option) {
                    case "1":
                        out.println("Mi nombre es ChatBot Server");
                        break;
                    case "2":
                        out.println("Hoy es " + java.time.LocalDate.now());
                        break;
                    case "3":
                        out.println("La hora actual es " + java.time.LocalTime.now());
                        break;
                    case "4":
                        out.println("No tengo ubicación fisica, soy software :)");
                        break;
                    case "5":
                    	InetAddress IP=InetAddress.getLocalHost();
                    	System.out.println(IP.toString());
                        out.println("Tu IP es " + IP.toString());
                        break;
                    default:
                        out.println("Opción invalida, eliga un numero del 1-5 o escriba \"exit\" para salir");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Ha ocurrido un error en el comunicación con el cliente: " + e.getMessage());
            }
        }
    }
}
