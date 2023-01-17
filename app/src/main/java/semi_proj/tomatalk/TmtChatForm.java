package semi_proj.tomatalk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TmtChatForm extends JFrame implements ActionListener, FocusListener {

  Socket chatFormClient = null; // 1대1채팅 소켓
  ObjectOutputStream oos2 = null;// 말 하고 싶을 때
  ObjectInputStream ois2 = null;// 듣기 할 때
  String nickName = null;// 닉네임 등록
  // 선언부
  String imgPath = "D:\\semi\\app\\src\\main\\java\\semi_proj\\images\\토마토채팅방배경.png";
  JPanel jp = new JPanel();
  JPanel jp_imgPanel = new JPanel();// 채팅방 배경사진올려줄 패널 선언
  JPanel jp_south = new JPanel();
  JPanel jp_south_first = new JPanel();// 메세지 입력, 전송 부분
  JPanel jp_south_second = new JPanel();// 메인,개인,단체버튼 부분
  // JTextField jtf_msg = new JTextField(25);// south속지 center - 한줄 입력창 만들기
  JTextField jtf_msg_guide = new JTextField("메세지를 입력하세요.", 25);// south속지 center - 한줄 입력창 만들기
  JButton jbtn_send = new JButton("전송");// south속지 east
  JButton jbtn_tmtchat = new JButton("채팅불러오기");
  JButton jbtn_friendlist = new JButton("친구목록");
  JButton jbtn_exit = new JButton("나가기");
  JTextArea jta_display = new JTextArea(30, 60);
  JScrollPane jsp_display = new JScrollPane(jta_display, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
      JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  FriendList friendList = new FriendList(this);
  TmtClient tc = null;

  public TmtChatForm(TmtClient tc) {
    this();
    this.tc = tc;
    }

  // 생성자
  public TmtChatForm() {
    initDisplay();
  }
  // 메인
  public static void main(String[] args) {
    //TmtChatForm tmtChatForm = new TmtChatForm();
    //tmtChatForm.initDisplay();
  }

  // 화면그리기
  public void initDisplay() {
    jtf_msg_guide.addFocusListener(this);
    jtf_msg_guide.addActionListener(this);
    jbtn_send.addActionListener(this);
    jbtn_exit.addActionListener(this);
    jbtn_friendlist.addActionListener(this);
    jp.setLayout(new BorderLayout());
    jp.add("Center", jsp_display);
    jp.add("South", jp_south);
    // jp.add("Center",jp_imgPanel);=============================
    // jp_south.setLayout(new GridLayout());
    jp_south.setLayout(new GridLayout(2, 1));
    jp_south.add(jp_south_first);
    jp_south.add(jp_south_second);
    jp_south_first.add("Center", jtf_msg_guide);
    jp_south_first.add("East", jbtn_send);
    jp_south_second.setLayout(new GridLayout(1, 3));
    jp_south_second.add(jbtn_tmtchat);
    jp_south_second.add(jbtn_friendlist);
    jp_south_second.add(jbtn_exit);
    // jp_imgPanel.add(imdPath)=========================================
    jta_display.setBackground(Color.WHITE);// 대화창 배경색 변경
    jta_display.setForeground(Color.black);// 대화창 글자색 변경
    jta_display.setLineWrap(true);// 자동줄바꾸기
    jta_display.setEnabled(false);// JTextArea 입력불가하게 만들기
    // jta_display.setIcon();// JTextArea 입력불가하게 만들기
    this.setLayout(new GridLayout(1, 2));// 메세지창 , 전송버튼
    this.add(jp);
    this.setSize(400, 700);
    this.setVisible(true);
    this.setTitle("tomatalk_chat_form");
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    jbtn_exit.requestFocus(true);// actionPerformed이벤트 걸리지 않은곳에 포커스를 맞춰줌
  }
  // 채팅방배경이미지 넣기
  // JViewport viewport = new JViewport() {
  // public void paintComponent(Graphics g) {
  // Image img = imgPath.getImage();
  // setOpaque(false);
  // Graphics2D gd = (Graphics2D) g;
  // gd.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
  // g.drawImage(imgPath, 0, 0, this);//
  // super.paintComponent(g);// 패널에 그려진 장상을 지우기 위해 호출
  // }
  // };

  @Override
  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();
    if (jtf_msg_guide == obj || jbtn_send == obj) {
      String msg1 = jtf_msg_guide.getText();
      //jta_display.append(msg + "\n");
      jtf_msg_guide.setText("");
      try {
        tc.oos.writeObject(Protocol.PROOM_IN+Protocol.separator+tc.nickName+Protocol.separator+tc.otherName+Protocol.separator+msg1);
       // tc.oos.writeObject(Protocol.PROOM_MSG+Protocol.separator+tc.nickName+Protocol.separator+tc.otherName+Protocol.separator+msg1);
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    } else if (jbtn_exit == obj) {// 나가기 버튼 누르면 1:1 채팅창 닫힘
      this.dispose();// JFrame을 상속받았기 때문에 버튼에 거는게 아니라 this를 써야 한다
      // jbtn_exit.dispose();===> 이렇게 하면 오류남 버튼에 걸려있으니까!
    } else if (jbtn_friendlist == obj) {
      // 친구목록 버튼 누르면 개인대화방 꺼지고 친구목록창 뜨게하기
      friendList.isClicked = true;
      friendList.initDisplay();
      this.dispose();
    }

  }

  @Override
  public void focusGained(FocusEvent e) { // 컴포넌트가 포커스 획득하는 경우에 발생
    Object obj = e.getSource();
    if (jtf_msg_guide == obj) {
      jtf_msg_guide.setText("");
      System.out.println("화면");
    }
  }

  @Override
  public void focusLost(FocusEvent e) {// 컴포넌트가 포커스 상실하는 경우에 발생
    // TODO Auto-generated method stub

  }
}