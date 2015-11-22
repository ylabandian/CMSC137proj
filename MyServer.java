import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*************************/
class MyServer{
	ArrayList al=new ArrayList();
	ArrayList users=new ArrayList();
	ServerSocket ss;
	Socket s;

	public final static int PORT=10;
	public final static String UPDATE_USERS="updateuserslist:";
	public final static String LOGOUT_MESSAGE="@@logoutme@@:";
	public final static String MITM_MESSAGE = "maninthemiddle:";
	public MyServer(){
		try{
			ss=new ServerSocket(PORT);
			System.out.println("Server Started "+ss);
			while(true){
				s=ss.accept();
				Runnable r=new MyThread(s,al,users);
				Thread t=new Thread(r);
				t.start();
			//	System.out.println("Total alive clients : "+ss.);
			}
	    }
		catch(Exception e){System.err.println("Server constructor"+e);}
	}
	/////////////////////////
	public static void main(String [] args){
		new MyServer();
	}
	/////////////////////////
}
/*************************/

/*********************************/

/*********************************/
