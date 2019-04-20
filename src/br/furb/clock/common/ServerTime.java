package br.furb.clock.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author Bruno G. Vigentas, Luciane Tedesco & Gustavo Westarb
 *
 */
public interface ServerTime extends Remote {

	Time getTime() throws RemoteException;

	void setTime(long epochSeconds) throws RemoteException;
}