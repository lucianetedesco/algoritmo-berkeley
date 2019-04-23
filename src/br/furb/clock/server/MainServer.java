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

		try {
			ServerTime severTime1 = new ServerTimeImpl();
			Registry registry1 = LocateRegistry.createRegistry(serverPort1);
			registry1.rebind("ServerTimeImpl", severTime1);
			System.out.println("Server started");

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}
}