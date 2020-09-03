package com.hit.server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//import com.hit.service.CarServerController;

public class Server implements PropertyChangeListener, Runnable{

    protected int port;
    private Socket socket = null;
    private HandleRequest handleRequest;
    private String response;
    private int numberOfClients;
	static final int MAX_CLIENTS = 100;
    private ServerSocket serverSocket;
    private boolean isUp = true;
    
    public Server(int port){
    	numberOfClients = 0;
        this.port = port;
        new Thread(this).start();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
		response = (String) evt.getNewValue();
		Thread t1 = new Thread(response);
		t1.start();
	}
    
	@Override
	public void run() {
		System.out.println("Srever started");
		try {
		    serverSocket = new ServerSocket(12346);
		    ExecutorService executorService = Executors.newFixedThreadPool(MAX_CLIENTS);
		    while(isUp) {
			    socket = serverSocket.accept();	
			    numberOfClients++;
			    //handleRequest = new HandleRequest(socket, carServerController);
			    //executorService.execute(handleRequest);  
			    

		    }
		} catch (IOException  e) { 
			System.out.println("Server couldent start" + e);  
		}
		finally {
			try {
				serverSocket.close();
				System.out.println("Server closed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}