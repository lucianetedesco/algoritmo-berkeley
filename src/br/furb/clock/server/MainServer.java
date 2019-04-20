package br.furb.clock.server;

import br.furb.clock.common.ServerTime;
import br.furb.clock.common.ServerTimeImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 
 * @author Bruno G. Vigentas, Luciane Tedesco & Gustavo Westarb
 *
 */
public class MainServer {

	public static void main(String[] args) {
		final int serverPort1 = 4500;
//      Descomente para testar local
//		final int serverPort2 = 4501;

		try {
			ServerTime severTime1 = new ServerTimeImpl();
			Registry registry1 = LocateRegistry.createRegistry(serverPort1);
			registry1.rebind("ServerTimeImpl", severTime1);
			System.out.println("Server started");

//          Descomente para testar local
//			ServerTime severTime2 = new ServerTimeImpl();
//			Registry registry2 = LocateRegistry.createRegistry(serverPort2);
//			registry2.rebind("ServerTimeImpl", severTime2);
//			System.out.println("Server 2 started");

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}
}