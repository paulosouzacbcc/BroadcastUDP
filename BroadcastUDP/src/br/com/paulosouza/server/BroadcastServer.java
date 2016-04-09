package br.com.paulosouza.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.Buffer;

/**
 * Created by Paulo Soza on 08/04/2016.
 */
public class BroadcastServer {

    BufferedReader entrada = null;
    String str = null;

    byte[] buffer;

    DatagramPacket packet;
    InetAddress address;

    int port;

    DatagramSocket socket;

    public BroadcastServer() throws IOException {
        System.out.println("Enviando Mensagens");

        //vai receber pedidos dos clientes (request)

        socket = new DatagramSocket();
        transmitir();

    }

    public void transmitir() {

        try {

            entrada = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("Entre com a mensagem para enviar aos Clientes");

                str = entrada.readLine();

                buffer = str.getBytes();

                address = InetAddress.getByName("233.0.0.1");

                packet = new DatagramPacket(buffer, buffer.length, address, 1502);

                socket.send(packet);
            }
        } catch (Exception e) {
            System.out.println("Erro2 " + e);
        } finally {
            try {
                entrada.close();
                socket.close();
            } catch (IOException e) {
                System.out.println("Erro3" + e);
            }
        }
    }
}
