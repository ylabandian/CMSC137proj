import java.io.BufferedReader;
import java.io.FileReader;
import java.net.Socket;
import java.io.DataInputStream;


public class GameDBase implements Runnable{	// MITM Class
	String uname;
	String pass;
	String trophies;
	String baseLayout;
	String inputUname;
	String inputPass;


	String userState;
	Thread t;
	Socket s;
	BufferedReader br;

	public GameDBase(Socket s, String uname, String pass){
		this.s = s;
		this.inputUname = uname;
		this.inputPass = pass;
		t = new Thread(this);
		t.start();
	}

	public void run(){
		String news;
		try{
			while(true){
				DataInputStream dis = new DataInputStream(s.getInputStream());
				news = dis.readUTF();
				String[] splits = news.split(" ");
				if(splits[0].toLowerCase().equals(MyServer.MITM_MESSAGE)){
					System.out.print("MITM reads: ");
					for(int i=1;i<splits.length;i++){
						System.out.print(splits[i]+" ");
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	public boolean checkUname(){
		try{
			br = new BufferedReader(new FileReader("sample.txt"));
			String line1 = "";
			do{
				line1 = br.readLine();
				String[] line = line1.split(",");

				if(line[0] != null && line[0].equals(this.inputUname)){
					this.uname = line[0];
					this.trophies = line[2];
					return true;
				}
				else continue;
			}while(line1 != null);
			br.close();
			return false;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkPass(){
		try{
			br = new BufferedReader(new FileReader("sample.txt"));
			String line1 = "";
			do{
				line1 = br.readLine();
				String[] line = line1.split(",");

				if(line[1] != null && line[1].equals(this.inputPass)){
					this.pass = line[1];
					this.trophies = line[2];
					return true;
				}
				else continue;
			}while(line1 != null);
			br.close();
			return false;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public String getBaseLayout(){
		return null;
	}

	public String getPass(){
		return this.pass;
	}

	public String getUname(){
		return this.uname;
	}

	public String getTrophies(){
		return this.trophies;
	}

}