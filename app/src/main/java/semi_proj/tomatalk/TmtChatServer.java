package semi_proj.tomatalk;

import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;

//JFrame 상속, runnable implements ,ActionListener implements

public class TmtChatServer extends JFrame implements Runnable, ActionListener {
  // 선언부

  ServerSocket serverSocket = null;// 클라이언트화 연결할 때만 필요한 ServerSocke class
  Socket client = null;// 서버로 접속한 클라이언트 Socket을 저장할 멤버변수
  TmtServerThread tmtServerThread = null; // 클라이언트측에서 new Socket하면 그 소켓정보 받아서 쓰레드로 넘김
  List<TmtServerThread> globalList = null; // 여려명 들어올 수 있으니까 list 타입 몇명이 들어오는지 모르니까 배열로 받을 수 없음
  JTextArea jta_log = new JTextArea(20, 30);// 로그 정보 담을 JTA
  JScrollPane jsp_log = new JScrollPane(jta_log, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
      JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  JButton jbtn_log = new JButton("로그를 저장합니다.");

  // 생성자 - 멤버변수 초기화
  public TmtChatServer() {

  }

  // 화면그리기

  public void initdisplay() {
    jbtn_log.addActionListener(this);
    this.add("North", jbtn_log);
    this.add("Center", jsp_log);
    this.setSize(500, 400);
    this.setVisible(true);
  }

  // 메인
  public static void main(String[] args) {
    TmtChatServer tmtChatServer = new TmtChatServer();
    tmtChatServer.initdisplay();
    Thread thread = new Thread(tmtChatServer);/////// ??//////
    thread.start();
  }

  // //벡터에 접속한 클라이언트의 스레드 저장
  // public void addThread(TmtServerThread st){
  // v.add(st);
  // }

  // //퇴장한클라이언트 스레드 제거
  // public void removeThread(TmtServerThread st){
  // v.remove(st);
  // }

  // 각 클라이언트에게 메세지 출력하는 메소드 , send()호출
  // public void broadCast(String str){
  // for (int i =0; v.size();i++){
  // //각각의 클라이언트를 ServerThread 객체로 형변환
  // TmtServerThread st = (TmtServerThread)v.elementAt(i);
  // //각 스레드 객체에 str문자열 전송
  // st.send(str);
  // }
  // }

  // 서버소켓과 클라이언트 소켓을 연결함
  @Override
  public void run() {
    globalList = new Vector<>(); // 서버에 접속해온 스레드 정보를 관리하기 위해 백터를 생성한다.

    // 접속이 안될수도 있으니까 예외처리
    try {
      // ServerSocket 객체생성 -> 포트번호 부여해야함!!
      serverSocket = new ServerSocket(2000);
      System.out.println("서버소켓>>>" + serverSocket); // 서버소켓정보 확인하기 위해 출력
      jta_log.append("server ready!");
      System.out.println("채팅서버 열리는중"); // 채팅서버 열리는지 확인하기 위해 출력
      // 서버가동 : 클라이언트가 접속할 때까지 기다림!(무한대기)
      boolean isStop = false;
      while (!isStop) {
        client = serverSocket.accept();// 접속한 클라이언트 socket을 serverSocket변수에 저장
        jta_log.append("서버에 접속한 클라이언트>>>" + client);// 서버에 접속한 클라이언트 jta_log에 붙이기
        jta_log.append("서버에 접속한 클라이언트 >>>" + client.getInetAddress() + "\n");// 서버에 접속한 클라이언트 주소
        System.out.println("서버에 접속한 클라이언트>>>" + client);
        tmtServerThread = new TmtServerThread(this);//////// ?//////////접속클라이언트와 서버로 st객체 생성
        // Thread 가동 ->run()-> broadCast()-> send()실시간 메소드 호출
        tmtServerThread.start();
      }
    } catch (Exception e) {
      System.out.println("서버 접속 실패하였습니다." + e);
      e.printStackTrace();

    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

  }
}