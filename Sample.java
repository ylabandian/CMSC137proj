import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import java.net.*;

public class Sample extends JFrame implements ActionListener{
	InetAddress serverAdd;
	Socket s;
	DataInputStream dis;
	DataOutputStream dos;
	JList usersList;

	JTextArea txtBroadcast;
	JTextArea txtMessage;

	JFrame frmClashOfClans;
	JTextField txtEnterVillageName;
	JPasswordField txtEnterPassword;
	JTextField txtEnterVillageName2;
	JTextField txtEnterPassword2;
	JTextField txtConfirmPassword;
	JTextField villageName;
	JTextField numOfTrophies;
	JTextField chatField;
	JTextArea chatArea;
	JButton sendBtn;
	JButton btnLogin;
	JButton btnSignUp;
	JButton attack;
	// JScrollPane pane;
	JPanel panel_2;
	JPanel panel_1;
	JPanel mainPanel = new JPanel();
	JPanel cards = new JPanel();

	GameDBase dbase;
	CardLayout card = new CardLayout();
	
	public final String MAIN = "name_210125486665742";
	public final String SIGNUP = "signup";
	public final String LOGIN = "name_210115789996997";

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try{
					if(args.length < 1){
						System.out.println("To run: java MyClient <Server IP Address>");
					}
					else{
						new Sample(InetAddress.getByName(args[0]));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Sample(InetAddress serverAdd) throws Exception{
		this.serverAdd = serverAdd;
		s = new Socket(this.serverAdd,MyServer.PORT);
		initialize();
	}

	public void initialize(){
		frmClashOfClans = new JFrame();
		frmClashOfClans.setTitle("CLASH OF CLANS");
		frmClashOfClans.setBounds(100, 100, 840, 588);
		frmClashOfClans.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClashOfClans.setVisible(true);
		frmClashOfClans.getContentPane().setLayout(null);
		
		//cards = new JPanel();
		//mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 824, 549);
		cards.setBounds(0, 0, 824, 549);
		frmClashOfClans.getContentPane().add(cards);
		cards.setLayout(new CardLayout());
		
		JPanel signup = new JPanel();

		JPanel login = new JPanel();
		mainPanel.add(login, "name_210115789996997");
		login.setLayout(null);
		
		txtEnterVillageName = new JTextField();
		txtEnterVillageName.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtEnterVillageName.setBackground(UIManager.getColor("Label.background"));
		txtEnterVillageName.setText("Enter village name");
		txtEnterVillageName.setBounds(270, 313, 256, 32);
		txtEnterVillageName.setOpaque(true);
		login.add(txtEnterVillageName);
		txtEnterVillageName.setColumns(10);
		
		txtEnterPassword = new JPasswordField();
		txtEnterPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtEnterPassword.setText("Enter password");
		txtEnterPassword.setBounds(270, 356, 256, 32);
		txtEnterPassword.setOpaque(true);
		login.add(txtEnterPassword);
		txtEnterPassword.setColumns(10);

		txtConfirmPassword = new JTextField();
		txtConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtConfirmPassword.setText("Enter password");
		txtConfirmPassword.setBounds(270, 356, 256, 32);
		txtConfirmPassword.setOpaque(true);
		txtConfirmPassword.setColumns(10);

		ImageIcon icon = new ImageIcon("gia/COC/img/signup_btn.jpg");
		Image img = icon.getImage() ;  
   		Image newimg = img.getScaledInstance(120,30,java.awt.Image.SCALE_SMOOTH) ;  
  		icon = new ImageIcon(newimg);
		btnSignUp = new JButton("");
		btnSignUp.setIcon(icon);
		btnSignUp.setBounds(405, 448, 121, 32);
		login.add(btnSignUp);
		

		ImageIcon icon2 = new ImageIcon("gia/COC/img/login_btn.jpg");
		Image img2 = icon2.getImage() ;  
   		Image newimg2 = img2.getScaledInstance(100,31,java.awt.Image.SCALE_SMOOTH) ;  
  		icon2 = new ImageIcon(newimg2);

		btnLogin = new JButton("");
		btnLogin.setIcon(icon2);
		btnLogin.setBackground(new Color(240, 240, 240));
		btnLogin.setBounds(270, 448, 100, 32);
		login.add(btnLogin);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("D:\\DATA\\Desktop\\clash of clans\\coc_logo_2013.png"));
		label_1.setBounds(217, 42, 539, 201);
		login.add(label_1);

		JLabel lblNewLabel2 = new JLabel("");
		lblNewLabel2.setIcon(new ImageIcon("gia/COC/img/home_bg.jpg"));
		lblNewLabel2.setBounds(0, 0, 824, 549);
		signup.add(lblNewLabel2);
		btnSignUp.addActionListener(this);

/*
		mainPanel.add(signup, "name_210125486665742");
		signup.setLayout(null);
		
		txtEnterVillageName2 = new JTextField();
		txtEnterVillageName2.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtEnterVillageName2.setBackground(UIManager.getColor("Label.background"));
		txtEnterVillageName2.setText("Enter village name");
		txtEnterVillageName2.setBounds(270, 313, 256, 32);
		txtEnterVillageName2.setOpaque(true);
		signup.add(txtEnterVillageName2);
		txtEnterVillageName2.setColumns(10);
		
		txtEnterPassword2 = new JTextField();
		txtEnterPassword2.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtEnterPassword2.setText("Enter password");
		txtEnterPassword2.setBounds(270, 356, 256, 32);
		txtEnterPassword2.setOpaque(true);
		signup.add(txtEnterPassword2);
		txtEnterPassword2.setColumns(10);

		txtConfirmPassword = new JTextField();
		txtConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtConfirmPassword.setText("Enter password");
		txtConfirmPassword.setBounds(270, 356, 256, 32);
		txtConfirmPassword.setOpaque(true);
		signup.add(txtConfirmPassword);
		txtConfirmPassword.setColumns(10);

	*/	
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("gia/COC/img/home_bg.jpg"));
		lblNewLabel.setBounds(0, 0, 824, 549);
		login.add(lblNewLabel);
		
		btnLogin.addActionListener(this);
		JPanel home = new JPanel();
		mainPanel.add(home, "name_210125486665742");
		home.setLayout(null);
		
		JPanel left = new JPanel();
		left.setBackground(Color.BLACK);
		left.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		left.setBounds(10, 11, 533, 527);
		home.add(left);
		left.setLayout(null);
		
		JPanel header = new JPanel();
		header.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		header.setBounds(10, 11, 513, 50);
		left.add(header);
		header.setLayout(null);
		
		villageName = new JTextField();
		villageName.setFont(new Font("Tahoma", Font.BOLD, 14));
		villageName.setEditable(false);
		villageName.setText("VILLAGE NAME");
		villageName.setBounds(10, 11, 226, 28);
		header.add(villageName);
		villageName.setColumns(10);
		
		numOfTrophies = new JTextField();
		numOfTrophies.setEditable(false);
		numOfTrophies.setText("Number of trophies");
		numOfTrophies.setBounds(275, 12, 112, 28);
		header.add(numOfTrophies);
		numOfTrophies.setColumns(10);
		
		attack = new JButton("ATTACK");
		attack.addActionListener(this);
		attack.setBounds(414, 11, 89, 28);
		header.add(attack);
		
		JPanel panel = new JPanel();
		panel.setBounds(246, 11, 36, 28);
		header.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("D:\\DATA\\Desktop\\Trophy.png"));
		label.setBounds(10, 0, 36, 28);
		panel.add(label);
		
		JPanel game = new JPanel();
		game.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		game.setBounds(10, 72, 513, 444);
		left.add(game);

		JLabel lblNewLabel3 = new JLabel("");
		lblNewLabel3.setIcon(new ImageIcon("../img/th.png"));
		lblNewLabel3.setBounds(10, 72, 513, 444);
		game.add(lblNewLabel3);
		
		JPanel right = new JPanel();
		right.setBackground(Color.BLACK);
		right.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		right.setBounds(553, 11, 261, 527);
		home.add(right);
		right.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 241, 254);
		right.add(panel_1);
		
		panel_2 = new JPanel();
		panel_2.setBounds(10, 276, 241, 240);
		right.add(panel_2);
		panel_2.setLayout(new BorderLayout());




		txtBroadcast = new JTextArea(5,30);
		txtBroadcast.setEditable(false);
		txtMessage = new JTextArea(2,20);

	    panel_2.add(new JScrollPane(txtBroadcast), BorderLayout.CENTER);

	    JPanel sendBox = new JPanel();
	    sendBox.setLayout(new GridLayout(2,1));
	    sendBox.add(new JScrollPane(txtMessage));

		sendBtn = new JButton("Send");
		sendBtn.setBounds(165, 184, 66, 45);
		sendBtn.addActionListener(this);
		sendBox.add(sendBtn);

		panel_2.add(sendBox, BorderLayout.SOUTH);

		cards.add(login);
		cards.add(mainPanel);
		cards.add(signup);
		cards.add(login, LOGIN);
		cards.add(mainPanel, MAIN);
		cards.add(home, "HOME");
		cards.add(signup, SIGNUP);

		card.addLayoutComponent(login, LOGIN);
		card.addLayoutComponent(mainPanel, MAIN);
		card.addLayoutComponent(home, "HOME");
		card.addLayoutComponent(signup, SIGNUP);
		CardLayout clay = (CardLayout)(cards.getLayout());
		clay.show(cards, LOGIN);
	}

	public void actionPerformed(ActionEvent ev){
		if(ev.getSource()==sendBtn){
			// if(s==null){
			try{
				dos.writeUTF(txtMessage.getText());
				txtMessage.setText("");
			}
			catch(Exception excp){
				txtBroadcast.append("\nsend button click :"+excp);
			}
		}
		if(ev.getSource() == btnLogin){
			//String uname = JOptionPane.showInputDialog(frmClashOfClans,"Enter nickname: ");
			String uname = txtEnterVillageName.getText().trim();
			String pass = new String(txtEnterPassword.getPassword());
			if(uname!=null){
				dbase = new GameDBase(this.s, uname, pass);
				if(dbase.checkUname()){
					if(dbase.checkPass()){
						clientChat(uname);
						frmClashOfClans.setTitle(uname);
						villageName.setText(uname);
						numOfTrophies.setText(dbase.getTrophies());
						CardLayout clay = (CardLayout)(cards.getLayout());
						clay.show(cards, "HOME");
						System.out.println(uname);
					}
					else JOptionPane.showMessageDialog(null, "Invalid password!", "", JOptionPane.ERROR_MESSAGE);
				}
				else JOptionPane.showMessageDialog(null, "Invalid username!", "", JOptionPane.ERROR_MESSAGE);
			}
		}

		if(ev.getSource() == btnSignUp){
			JOptionPane.showMessageDialog(null, "Sorry. This feature is not yet implemented.", "", JOptionPane.ERROR_MESSAGE);
		}

		if(ev.getSource() == attack){
			JOptionPane.showMessageDialog(null, "Sorry. This feature is not yet implemented.", "", JOptionPane.ERROR_MESSAGE);
		}		

		// if(temp==logoutButton){
		// 	if(s!=null)
		// 		logoutSession();
		// }
		// if(temp==exitButton){
		// 	if(s!=null){
		// 		JOptionPane.showMessageDialog(chatWindow,"u r logged out right now. ","Exit",JOptionPane.INFORMATION_MESSAGE);
		// 		logoutSession();
		// 	}
		// 	System.exit(0);
		// }
	}

	public void clientChat(String uname){
		try{
		    //s=new Socket(InetAddress.getLocalHost(),MyServer.PORT);
		    s = new Socket(this.serverAdd,MyServer.PORT);
		    dis = new DataInputStream(s.getInputStream());
		    dos = new DataOutputStream(s.getOutputStream());
		    ClientThread ct = new ClientThread(dis,this);
		    Thread t1 = new Thread(ct);
		    t1.start();
		    dos.writeUTF(uname);
		}
		catch(Exception e){
			txtBroadcast.append("\nClient Constructor " +e);
		}

		// logoutButton.setEnabled(true);
		// loginButton.setEnabled(false);
	}
}
