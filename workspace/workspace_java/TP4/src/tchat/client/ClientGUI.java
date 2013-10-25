package tchat.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ClientGUI extends JFrame implements ActionListener{

	protected JButton bSend;
	protected JButton bClear;
	protected JTextArea tSend;
	protected JTextArea tReceive;
	protected ClientConnection clientconnection;
	
	public ClientGUI(Socket s, String name){
		clientconnection = new ClientConnection(this, s, name);
		clientconnection.start();
		setTitle(name);
		bSend = new JButton("Send");
		bSend.addActionListener(this);
		
		bClear = new JButton("Clear");
		bClear.addActionListener(this);
		
		tSend = new JTextArea(10,40);
		tReceive = new JTextArea(10,40);
		tReceive.setEditable(false);
		
		
		JPanel centerPanel= new JPanel();
		centerPanel.setLayout(new GridLayout(2,1));
		centerPanel.add(new JScrollPane(tReceive));
		centerPanel.add(new JScrollPane(tSend));
		
		
		JPanel southpanel = new JPanel();
		southpanel.setLayout(new FlowLayout());
		southpanel.add(bClear);
		southpanel.add(bSend);

		this.setLayout(new BorderLayout());
		this.add(BorderLayout.CENTER, centerPanel);
		this.add(BorderLayout.SOUTH, southpanel);
		pack();
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == bSend){
			String sendText = tSend.getText();
			clientconnection.send(sendText);
			tReceive.append("me<"+ sendText + "\n");
			tSend.setText("");
		}else if(e.getSource()== bClear){
			tSend.setText("");
		}
	}
	
	public static void main(String arg[]) {
		Socket ss = null;
		try {
			ss = new Socket("localhost", 30970);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClientGUI gui = new ClientGUI(ss, arg[0]);
	}
	public void addMessage(String msg) {
		// TODO Auto-generated method stub
		
		tReceive.append(msg + "\n");
//		tReceive.setText(tReceive.getText()+"\n"+msg);
	}
}
