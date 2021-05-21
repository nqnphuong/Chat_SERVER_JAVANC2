package Client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame implements ActionListener{
	
	private javax.swing.JTextField Ten;
    private javax.swing.JLabel c_ten;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton login;
    private javax.swing.JButton logout;
    public javax.swing.JTextArea msg;
    public javax.swing.JTextArea online;
    private javax.swing.JPanel p_chat;
    private javax.swing.JButton send;
    private javax.swing.JTextField tn;
    
    private Socket client;
    private DataStream dataStream;
    private DataOutputStream dos;
	private DataInputStream dis;
    
	public Client(){
		super("Client");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				exit();
			}	
		});
		setSize(600, 350);
		addItem();
		setVisible(true);
	}
	private void addItem() {
		p_chat = new javax.swing.JPanel();
        Ten = new javax.swing.JTextField();
        tn = new javax.swing.JTextField();
        send = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        c_ten = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        online = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        msg = new javax.swing.JTextArea();
        login = new javax.swing.JButton();
        logout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        p_chat.setBackground(new java.awt.Color(204, 204, 255));
        p_chat.setPreferredSize(new java.awt.Dimension(600, 350));

        Ten.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        Ten.setForeground(new java.awt.Color(0, 0, 51));
        Ten.setMinimumSize(new java.awt.Dimension(10, 22));
        

        tn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tn.setForeground(new java.awt.Color(0, 0, 51));
        tn.setMinimumSize(new java.awt.Dimension(10, 22));
        
        send.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        send.setForeground(new java.awt.Color(0, 0, 102));
        send.setText("SEND");
        send.setMaximumSize(new java.awt.Dimension(10, 25));
        send.setPreferredSize(new java.awt.Dimension(10, 25));
        send.addActionListener(this);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Messenger:");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        c_ten.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        c_ten.setForeground(new java.awt.Color(0, 0, 102));
        c_ten.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c_ten.setText("Name:");
        c_ten.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        online.setEditable(false);
        online.setColumns(20);
        online.setRows(5);
        online.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Online", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 51))); // NOI18N
        jScrollPane1.setViewportView(online);

        msg.setEditable(false);
        msg.setColumns(20);
        msg.setRows(5);
        msg.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 51))); // NOI18N
        jScrollPane2.setViewportView(msg);

        login.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        login.setForeground(new java.awt.Color(0, 0, 102));
        login.setText("LOGIN");
        login.setMaximumSize(new java.awt.Dimension(10, 25));
        login.setPreferredSize(new java.awt.Dimension(67, 7));
        login.addActionListener(this);

        logout.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        logout.setForeground(new java.awt.Color(0, 0, 102));
        logout.setText("LOGOUT");
        logout.setMaximumSize(new java.awt.Dimension(10, 25));
        logout.setPreferredSize(new java.awt.Dimension(67, 7));
        logout.addActionListener(this);

        javax.swing.GroupLayout p_chatLayout = new javax.swing.GroupLayout(p_chat);
        p_chat.setLayout(p_chatLayout);
        p_chatLayout.setHorizontalGroup(
            p_chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_chatLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(p_chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(p_chatLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(tn, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p_chatLayout.createSequentialGroup()
                        .addGroup(p_chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, p_chatLayout.createSequentialGroup()
                                .addComponent(c_ten)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Ten, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(p_chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(p_chatLayout.createSequentialGroup()
                                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        p_chatLayout.setVerticalGroup(
            p_chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_chatLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(p_chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c_ten)
                    .addComponent(Ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(p_chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(p_chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(p_chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p_chat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p_chat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
	}
	
	private void go() {
		try {
			client = new Socket("localhost",2207);
			dos=new DataOutputStream(client.getOutputStream());
			dis=new DataInputStream(client.getInputStream());
		
			//client.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,"Lỗi đăng nhập, chưa mở server","Message Dialog",JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		new Client().go();
	}
	private void sendMSG(String data){
		try {
			dos.writeUTF(data);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private String getMSG(){
		String data=null;
		try {
			data=dis.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public void getMSG(String msg1, String msg2){
		int stt = Integer.parseInt(msg1);
		switch (stt) {
		
		case 3:
			this.msg.append(msg2);
			break;
		
		case 4:
			this.online.setText(msg2);
			break;
		
		case 5:
			dataStream.stopThread();
			exit();
			break;
		
		default:
			break;
		}
	}

	private void checkSend(String msg){
		if(msg.compareTo("\n")!=0){
			this.msg.append("Tôi : "+msg);
			sendMSG("1");
			sendMSG(msg);
		}
	}
	private boolean checkLogin(String Ten1){
		if(Ten1.compareTo("")==0)
			return false;
		else if(Ten1.compareTo("0")==0){
			return false;
		}
		else{
			sendMSG(Ten1);
			int sst = Integer.parseInt(getMSG());
			if(sst==0)
				 return false;
			else return true;
		}
	}

	private void exit(){
		try {
			sendMSG("0");
			dos.close();
			dis.close();
			client.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.exit(0);
	}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==send){
			checkSend(tn.getText()+"\n");
			tn.setText("");
		}
		else if(e.getSource()==login){
			if(checkLogin(Ten.getText())){
				p_chat.setVisible(true);
				this.setTitle(Ten.getText());
				msg.append("Đăng nhập thành công\n");
				dataStream = new DataStream(this, this.dis);
			}
			else{
				JOptionPane.showMessageDialog(this,"Tài khoản đã tồn tại , đăng nhập lại","Message Dialog",JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(e.getSource()==logout){
			exit();
		}
	}


}