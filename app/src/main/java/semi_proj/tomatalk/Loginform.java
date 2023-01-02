package semi_proj.tomatalk;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.Color;
import java.awt.GridLayout;
// import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Loginform extends JFrame implements ActionListener {
  //선언부
  ///////////////네트워크 관련 부분///////////////
  Socket socket = null;
	ObjectOutputStream oos = null;// 말 하고 싶을 때
	ObjectInputStream ois = null;// 듣기 할 때
	String userID = null;// 유저 아이디 등록
  String userPW = null;// 유저 패스워드 등록
  ///////////////네트워크 관련 부분///////////////
  JButton loginButton = new JButton("로그인");
  JButton signButton = new JButton("회원가입");
  JButton signconfirmButton = new JButton("가입하기");
  JLabel idLabel = new JLabel("ID");
  JLabel pwLabel = new JLabel("PW");
  JTextField idField = new JTextField(10);
  JPasswordField pwField = new JPasswordField(10);

  //생성자
  Loginform(){
    System.out.println("생성자 호출 성공");
    initDisplay();
  }
  //소켓관련초기화

	// //내부클래스로 배경 이미지 처리
	// class MyPanel extends JPanel{
	// 	public void paintComponent(Graphics g){
    // 		g.drawImage(imageIcon.getImage(), 0, 0, null);
	// 		setOpaque(false);
	// 		super.paintComponent(g);
	// 	}
  //화면그리기
  public void initDisplay() {
    System.out.println("화면그리기 호출 성공");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // jf.setContentPane(new MyPanel());
    this.setLayout(null);
    loginButton.addActionListener(this);
    signButton.addActionListener(this);
    this.add("South", loginButton);
    this.add("South", signButton);
    this.add(idLabel);
    this.add(idField);
    this.add(pwLabel);
    this.add(pwField);
    this.setSize(350, 600);
    this.setVisible(true);
    this.setLayout(new GridLayout(1,2));
    this.setTitle("토 마 톡");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocation(900,300);
    this.setBackground(Color.RED);
    idLabel.setBounds(45, 200, 80, 40);
    idField.setBounds(110, 200, 185, 40);
    pwLabel.setBounds(45, 240, 80, 40);
    pwField.setBounds(110, 240, 185, 40);
    // id
  }
  //메인
  public static void main(String[] args) {
    new Loginform();
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    
  }
  
}
