import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyThread implements Runnable{
Socket s;
ArrayList al;
ArrayList users;
String username;
///////////////////////
MyThread (Socket s, ArrayList al,ArrayList users){
	this.s=s;
	this.al=al;
	this.users=users;
	try{
		DataInputStream dis=new DataInputStream(s.getInputStream());
		username=dis.readUTF();
		al.add(s);
		users.add(username);
		tellEveryOne("****** "+ username+" Logged in at "+(new Date())+" ******");
		sendNewUserList();
	    }
	catch(Exception e){
		System.err.println("MyThread constructor  "+e);
	}
}
///////////////////////
public void run(){
	String s1;
	try{
		DataInputStream dis=new DataInputStream(s.getInputStream());
		do{
			s1=dis.readUTF();
			if(s1.toLowerCase().equals(MyServer.LOGOUT_MESSAGE)) break;
		//	System.out.println("received from "+s.getPort());
			tellEveryOne(username+" said: "+" : "+s1);
		}while(true);
		DataOutputStream tdos=new DataOutputStream(s.getOutputStream());
		tdos.writeUTF(MyServer.LOGOUT_MESSAGE);
		tdos.flush();
		users.remove(username);
		tellEveryOne("****** "+username+" Logged out at "+(new Date())+" ******");
		sendNewUserList();
		informMITM(s1);
		al.remove(s);
		s.close();

	}catch(Exception e){
		System.out.println("MyThread Run"+e);
	}
}
////////////////////////
public void sendNewUserList(){
	tellEveryOne(MyServer.UPDATE_USERS+users.toString());

}

public void informMITM(String s1){
	tellEveryOne(MyServer.MITM_MESSAGE +" "+ s1);
}
////////////////////////
public void tellEveryOne(String s1){
	Iterator i=al.iterator();
	while(i.hasNext()){
		try{
			Socket temp=(Socket)i.next();
			DataOutputStream dos=new DataOutputStream(temp.getOutputStream());
			dos.writeUTF(s1);
			dos.flush();
			//System.out.println("sent to : "+temp.getPort()+"  : "+ s1);
	   }
		catch(Exception e){
			System.err.println("TellEveryOne "+e);
		}
	}
}
///////////////////////
}