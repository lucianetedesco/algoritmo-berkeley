package br.furb.clock.common;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 
 * @author Bruno G. Vigentas, Luciane Tedesco & Gustavo Westarb
 *
 */
public class ServerTimeImpl extends UnicastRemoteObject implements ServerTime {

	private static final long serialVersionUID = 1L;
	private Time time;

	public ServerTimeImpl() throws RemoteException {
		time = new Time();
		System.out.println("Server time: " + time);
	}

	@Override
	public Time getTime() throws RemoteException {
		return time;
	}

	@Override
	public void setTime(long epochSeconds) throws RemoteException {
		time.setTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSeconds), ZoneOffset.UTC));
		System.out.println("Updated time to: " + time);
	}
}