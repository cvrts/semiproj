package semi_proj.tomatalk;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class TmtChatForm extends JFrame implements ActionListener {
  // 선언부
  //////////////// 통신과 관련한 전역변수 추가 시작//////////////
  // Socket socket = null;
  // ObjectOutputStream oos = null;// 말 하고 싶을 때
  // ObjectInputStream ois = null;// 듣기 할 때
  // String nickName = null;// 닉네임 등록
  //////////////// 통신과 관련한 전역변수 추가 끝 //////////////
  JPanel jp = new JPanel();
  JPanel jp_south = new JPanel();
  // String cols[] = { "대화명" };
  // String data[][] = new String[0][1];
  // DefaultTableModel dtm = new DefaultTableModel(data, cols);
  // JTable jtb = new JTable(dtm);
  // JScrollPane jsp = new JScrollPane(jtb);
  JTextField jtf_msg = new JTextField(20);// south속지 center - 한줄 입력창 만들기
  JButton jbtn_send = new JButton("전송");// south속지 east
  JTextArea jta_display = new JTextArea(15, 38);
  JScrollPane jsp_display = new JScrollPane(jta_display, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
  // 생성자

  // 소켓 관련 초기화
  // public void init() {
  // try {
  // // 서버측의 ip주소 작성하기
  // // socket = new Socket("192.168.0.244",3000);
  // // new ServerSocket(3000)이 받아서 accept()통해서 client소켓에 저장됨.
  // socket = new Socket("192.168.10.89", 3000);
  // oos = new ObjectOutputStream(socket.getOutputStream());
  // ois = new ObjectInputStream(socket.getInputStream());
  // // initDisplay에서 닉네임이 결정된 후 init메소드가 호출되므로
  // // 서버에게 내가 입장한 사실을 알린다.(말하기)
  // oos.writeObject(100 + Protocol.separator + nickName);
  // // 서버에 말을 한 후 들을 준비를 한다.
  // TalkClientThread tct = new TalkClientThread(this);
  // tct.start();
  // } catch (Exception e) {
  // // 예외가 발생했을 때 직접적인 원인되는 클래스명 출력하기
  // System.out.println(e.toString());
  // }
  // }

  // // 말하기 - 서버에 전달 - List<TalkServerThread> -> 반복문 -> 전송
  // @Override
  // public void actionPerformed(ActionEvent e) {
  // Object obj = e.getSource();
  // String msg = jtf_msg.getText();
  // if (jtf_msg == obj) {
  // try {
  // oos.writeObject(Protocol.MESSAGE
  // + Protocol.separator + nickName
  // + Protocol.separator + msg);
  // jtf_msg.setText("");
  // } catch (Exception e2) {
  // e2.printStackTrace();
  // }
  // } // end of 다자간 대화
  // else if (jbtn_one == obj) {
  // // 상대를 선택
  // int row = jtb.getSelectedRow();
  // if (row == -1) {// -1=> end of file
  // JOptionPane.showMessageDialog(this, "귓속말 상대를 선택하세요.", "info",
  // JOptionPane.INFORMATION_MESSAGE);
  // return;
  // } else {
  // String name = (String) dtm.getValueAt(row, 0);
  // if (nickName.equals(name)) {
  // JOptionPane.showMessageDialog(this, "다른 상대를 선택하세요.", "info",
  // JOptionPane.INFORMATION_MESSAGE);
  // return;
  // }
  // // 귓속말 입력받기
  // String msg1 = JOptionPane.showInputDialog(name + "님에게 보낼 메시지를 입력");
  // try {
  // oos.writeObject(
  // Protocol.WHISPER + Protocol.separator + nickName + Protocol.separator + name
  // + Protocol.separator + msg1);
  // } catch (Exception e2) {
  // e2.printStackTrace();
  // }
  // }
  // // 선택된 귓속말 상대 초기화 - 선택해제
  // jtb.clearSelection();
  // } // end of 귓속말
  // else if (jbtn_change == obj) {
  // String afterName = JOptionPane.showInputDialog("변경할 대화명을 입력하세요.");
  // if (afterName == null || afterName.trim().length() < 1) {
  // JOptionPane.showMessageDialog(this, "변경할 대화명을 입력하세요", "INFO",
  // JOptionPane.INFORMATION_MESSAGE);
  // return;
  // }
  // try {
  // oos.writeObject(Protocol.CHANGE
  // + Protocol.separator + nickName
  // + Protocol.separator + afterName
  // + Protocol.separator + nickName + "의 대화명이 " + afterName + "으로 변경되었습니다.");
  // } catch (Exception e2) {
  // // TODO: handle exception
  // }
  // } // end of 대화명 변경
  // else if (jbtn_exit == obj) {
  // try {
  // oos.writeObject(Protocol.TALK_OUT + Protocol.separator + this.nickName);
  // // 자바가상머신과 연결고리 끊기
  // System.exit(0);
  // } catch (Exception e2) {
  // // TODO: handle exception
  // }
  // } // end of 나가기
  // }

  // 메인
  public static void main(String[] args) {
    TmtChatForm tmtChatForm = new TmtChatForm();
    tmtChatForm.initDisplay();
    // tmtChatForm.init();
  }

  // 화면그리기
  public void initDisplay() {
    jtf_msg.addActionListener(this);

    // 사용자의 닉네임 받기
    // nickName = JOptionPane.showInputDialog("닉네임을 입력하세요.");
    this.setLayout(new GridLayout(1, 2));
    jp.setLayout(new BorderLayout());
    jp.add("Center", jsp_display);
    jp.add("South", jp_south);
    jp_south.setLayout(new BorderLayout());
    jp_south.add("Center", jtf_msg);
    jp_south.add("East", jbtn_send);
    jta_display.setLineWrap(true);// 자동줄바꾸기

    this.add(jp);
    this.setSize(400, 550);
    this.setVisible(true);
    this.setTitle("tomatalk_chat_form");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

  }
}
