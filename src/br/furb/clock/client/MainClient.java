package br.furb.clock.client;

import br.furb.clock.common.Connection;
import br.furb.clock.common.ServerTime;
import br.furb.clock.common.Time;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Bruno G. Vigentas, Luciane Tedesco & Gustavo Westarb
 *
 */
public class MainClient {

	public static void main(String[] args) {
		try {

			final Time time = new Time();

			// List connections
			final List<Connection> connections = new ArrayList<>();

            // Se você quiser testar local, informe duas conexões com o seu ip. Uma na porta 4500 e outra na 4501. Necessário descomentar o código na classe MainServer. 
			connections.add(new Connection("192.168.208.1", 4500));
			connections.add(new Connection("192.179.209.1", 4500));

			// List date time
			final Map<Connection, ServerTime> servers = new HashMap<>();
			for (Connection c : connections) {
				Registry registry = LocateRegistry.getRegistry(c.getAddress(), c.getPort());
				ServerTime st = (ServerTime) registry.lookup("ServerTimeImpl");
				System.out.println(String.format("Server time %s: %s", c.getAddress(), st.getTime().getTime()));
				servers.put(c, st);
			}

			System.out.println("Client time: " + time);

			// Berkeley
			long sum = time.getEpochSeconds();
			for (Map.Entry<Connection, ServerTime> entry : servers.entrySet()) {
				sum += entry.getValue().getTime().getEpochSeconds();
			}
			long resultSeconds = sum / (servers.size() + 1);
			LocalDateTime resultDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(resultSeconds),
					ZoneOffset.UTC);
			System.out.println("Result date time: " + resultDateTime);

			// Set the new date time
			for (Map.Entry<Connection, ServerTime> entry : servers.entrySet()) {
				entry.getValue().setTime(resultSeconds);
				System.out.println(String.format("Server time %s: %s ", entry.getKey().getAddress(),
						entry.getValue().getTime().getTime()));
			}
			time.setTime(resultDateTime);
			System.out.println("Client time: " + time);

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}