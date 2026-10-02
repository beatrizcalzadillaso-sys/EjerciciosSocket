package ejemplo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		Socket clientSock;
		
		try {
			clientSock = new Socket("host", 22456);
			
			//	imprimo que inicio la conexion
			System.out.println("Conexion realizada con el servidor");
			
			// stream para enviar datos al servidor
			DataOutputStream salidaCli =
                    new DataOutputStream(clientSock.getOutputStream());

            // Flujo para recibir datos del servidor
            DataInputStream entradaCli =
                    new DataInputStream(clientSock.getInputStream());

            // Mensaje que pide el ejercicio
            String mensaje = "Hola servidor, soy un cliente";

            // Enviamos el mensaje
            salidaCli.writeUTF(mensaje);
            salidaCli.flush();

            // Esperamos la respuesta del servidor
            String respuesta = entradaCli.readUTF();

            // Mostramos la respuesta
            System.out.println(respuesta);

            // Cerramos los recursos
            salidaCli.close();
            entradaCli.close();
            clientSock.close();
			
		}catch(IOException exc) {
			exc.printStackTrace();
		}

	}

}
