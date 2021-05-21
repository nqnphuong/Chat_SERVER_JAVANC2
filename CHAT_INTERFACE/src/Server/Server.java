package Server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server extends JFrame implements ActionListener{
	
	private JButton close;
	public JTextArea user;
    private javax.swing.JLabel text;
    private javax.swing.JScrollPane jScrollPane1;
	private ServerSocket server;
	public Hashtable<String, Connection> listUser;
	
	public Server(){
		super("Server");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				try {
					server.close();
					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}	
		});
		setSize(420, 350);
		addItem();
		setVisible(true);
	}
	
	private void addItem() {
		
		text = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        user = new javax.swing.JTextArea();
        close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        text.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        text.setForeground(new java.awt.Color(0, 0, 102));
        text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text.setText("SERVER OPERATING STATUS");
        text.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        user.setColumns(20);
        user.setRows(5);
        user.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 51)));
        jScrollPane1.setViewportView(user);

        close.setFont(new java.awt.Font("Tahoma", 0, 13));
        close.setForeground(new java.awt.Color(0, 0, 102));
        close.setText("CLOSE SERVER");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(text)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(close))
        );

        
		close.addActionListener(this);
		user.append("Máy chủ đã khởi động.\n");
	}
	
	private void go(){
		try {
			listUser = new Hashtable<String, Connection>();
			server = new ServerSocket(2207);
			user.append("Máy chủ bắt đầu hoạt động\n");
			while(true){
				Socket client = server.accept();
				new Connection(this,client);
			}
		} catch (IOException e) {
			user.append("Không thể khởi động máy chủ\n");
		}
	}
	
	public static void main(String[] args) {
		new Server().go();
	}

	public void actionPerformed(ActionEvent e) {
			try {
				server.close();
			} catch (IOException e1) {
				user.append("Không thể dừng được máy chủ\n");
			}
			System.exit(0);
	}
	
	public void sendAll(String from, String msg){
		Enumeration e = listUser.keys();
		String name=null;
		while(e. hasMoreElements()){
			name=(String) e.nextElement();
			if(name.compareTo(from)!=0) listUser.get(name).sendMSG("3",msg);
		}
	}
	public void sendAllUpdate(String from){
		Enumeration e = listUser.keys();
		String name=null;
		while(e. hasMoreElements()){
			name=(String) e.nextElement();
			if(name.compareTo(from)!=0) listUser.get(name).sendMSG("4",getAllName());
		}
	}
	
	public String getAllName(){
		Enumeration e = listUser.keys();
		String name="";
		while(e. hasMoreElements()){
			name+=(String) e.nextElement()+"\n";
		}
		return name;
	}

}
