package semi_proj.tomatalk;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm00 extends JFrame implements ActionListener {
	// 선언부
	String imgPath = "E:\\vscode_java\\dev_java\\app\\src\\main\\java\\dev_java\\images\\login\\";
	ImageIcon imageIcon = new ImageIcon(imgPath + "main.png");
	JLabel jlb_id = new JLabel("아이디");
	JTextField jtf_id = new JTextField();
	JLabel jlb_pw = new JLabel("비밀번호");
	JPasswordField jpf_pw = new JPasswordField();
	Font font = new Font("돋움체", Font.BOLD, 10);
	// JButton jbtn_login = new JButton(new ImageIcon(imgPath+"login.png"));
	JButton jbtn_login = new JButton("LOGIN");
	// JButton jbtn_join = new JButton(new ImageIcon(imgPath+"confirm.png"));
	JButton jbtn_join = new JButton("JOIN US");
	JoinJDialog joindia = new JoinJDialog(this);
	ChatDao cDao = null;
	String nickname = null;

	// 생성자
	LoginForm00() {
	}

	// 내부클래스로 배경 이미지 처리
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.drawImage(imageIcon.getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}
	}// end of MyPanel - 사용자 패널정의 - LoginForm$1.class, LoginForm$MyPanel.class
		// 화면그리기

	public void initDisplay() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new MyPanel());
		this.setLayout(null);
		jlb_id.setBounds(45, 200, 80, 40);
		jlb_id.setFont(font);
		jtf_id.setBounds(110, 200, 185, 40);
		this.add(jlb_id);
		this.add(jtf_id);
		jlb_pw.setBounds(45, 240, 80, 40);
		jlb_pw.setFont(font);
		jpf_pw.setBounds(110, 240, 185, 40);
		this.add(jlb_pw);
		this.add(jpf_pw);
		jbtn_join.setBounds(45, 285, 120, 40);
		jbtn_login.addActionListener(this);
		jbtn_join.addActionListener(this);
		this.add(jbtn_join);
		jbtn_login.setBounds(175, 285, 120, 40);
		this.add(jbtn_login);
		this.setTitle("토마톡 초기화면");
		this.setLocation(500, 100);
		this.setSize(350, 600);
		this.setVisible(true);
	}

	// 메인메소드
	public static void main(String[] args) {
		LoginForm00 loginForm = new LoginForm00();
		loginForm.initDisplay();
		JoinJDialog joinDialog = new JoinJDialog();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == jbtn_join) { // 가입버튼 눌렀니?
			// System.out.println("액션퍼폼드");
			joindia.isClicked = true; // 버튼을 누르면 JoinDialog가 열림
			joindia.initDisplay();
		} else if (obj == jbtn_login) {
			cDao = new ChatDao();
			String user_id = jtf_id.getText();
			String user_pw = jpf_pw.getText();
			nickname = cDao.login(user_id, user_pw);
			TmtClient tClient = new TmtClient(this);
		}
		System.out.println("현재 적용된 닉네임" + nickname);
	}// end of actionPerformed
}