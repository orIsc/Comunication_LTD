package com.hit.server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import com.hit.server.Request.Body;
import com.hit.server.Request.Header;


public class HandleRequest implements PropertyChangeListener, Runnable {
   
    private String requestString;
    private Socket socket;
    private Request request;
    private ObjectInputStream reader;
	private ObjectOutputStream writer;
    private Response response;
    private String responseString;
    private String pat = "";
    private Body body;
    private Header header;
    private String action;
    
    public HandleRequest(Socket socket) {
    	super();
    	this.socket = socket;
  
    	try {
    		writer = new ObjectOutputStream(this.socket.getOutputStream());
    		reader = new ObjectInputStream(this.socket.getInputStream());
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    	responseString = (String) evt.getNewValue();
		Thread t1 = new Thread(this);
		t1.start();
	}
    

	@Override
	public void run() {
		
		try {
			//messages that arrives from the client side.
			requestString = reader.readObject().toString();
			//request = readRequest(requestString);
			System.out.println(request);
			
			//get the action from the client message
			//String action = request.getHeader().getAction();
			
			switch (action) {	
			
			} 
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    

}