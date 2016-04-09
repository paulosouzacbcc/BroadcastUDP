package br.com.paulosouza.cliente;

import javax.xml.transform.sax.SAXSource;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created by Paulo Soza on 08/04/2016.
 */
public class BroadcastClient {
  
    public InetAddress address;
    public byte[] buffer;
    public String msg, str2;
    public DatagramPacket packet;
    public MulticastSocket socket;

    public BroadcastClient() throws Exception {
        socket = new MulticastSocket(1502);
        System.out.println("Esperando mensagens do servidor");
        transmitir();
    }

    public void transmitir() {

        try {

            //registra arquivo recebido ??
            address = InetAddress.getByName("233.0.0.1");
            //registra o cliente no grupo
            socket.joinGroup(address);

            while (true) {
                buffer = new byte[256];
                packet = new DatagramPacket(buffer, buffer.length);

                //recebe arquivos do servidor.
                socket.receive(packet);
                msg = new String(packet.getData());
                System.out.println("Recebendo mensagem: " +msg);
            }
        } catch (Exception e) {
            System.out.println("Error1 " + e);
        }finally {

            try {
                //remove cliente do grupo
                socket.leaveGroup(address);
                //fecha o socket
                socket.close();
            }catch (Exception e){
                System.out.println("Error2 " +e);
            }
        }
    }
}
