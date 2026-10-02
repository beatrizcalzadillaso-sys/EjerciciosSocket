package ejemplo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	public static void main(String[] args) {
		ServerSocket miServicio;
		
		try {
			miServicio = new ServerSocket(22456);
			
			//	un print para saber que empieza a dar servicio
			System.out.println("Servidor inciado, a la espera de clientes");
			
			for (int i=0; i < 4; i++) {
				Socket clientSock = miServicio.accept();
				
				//	imprimo los cardinales de los clientes conectados
				System.out.println("Cliente "+i+" conectado");
				
				//	 declaro los stream
				//	stream para recibir datos del cliente
				DataInputStream entradaServ = new DataInputStream(clientSock.getInputStream());
				
				//	stream para enviar datos al cliente
				DataOutputStream salidaServ = new DataOutputStream(clientSock.getOutputStream());
				
				//	 recibo el mensaje enviado por el cliente
				String mensaje = entradaServ.readUTF();
				
				//	imprimo en consola el mensaje
				System.out.println("Recibido: --"+ mensaje);
				
				//	respuesta del servidor hacia el cliente				
				String respuesta = "Saludos desde el servidor al cliente numero: "+i;
				
				// mando la respuesta al cliente
				salidaServ.writeUTF(respuesta);
				
				// cierro las conexiones
				salidaServ.close();
				entradaServ.close();
				clientSock.close();
							
			}
			//	 se sale del for pq ya se atendieron a los 3 clientes
			System.out.println("Demasiados clientes por hoy");
			miServicio.close();	
			
		} catch (IOException exc) {
			exc.printStackTrace();
		}

	}

}
