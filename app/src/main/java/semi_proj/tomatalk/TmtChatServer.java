package semi_proj.tomatalk;
import java.util.Vector;

import javax.xml.transform.Source;

import java.net.ServerSocket;
import java.net.Socket;

public class TmtChatServer {
//클라이언트화 연결할 때만 필요한 ServerSocke class
ServerSocket ss;
  

//서버로 접속한 클라이언트 Socket을 저장할 멤버변수
Socket socket;
//접속 클라이언트 정보 실시간 저장
Vector v;
//ServerThread 자료형 멤버변수 선언..???
TmtServerThread st;

//생성자 - 멤버변수 초기화

public TmtChatServer(){
//사용자 정보를 담을 v를 vector객체로 초기화
v= new Vector();

//접속이 안될수도 있으니까 예외처리
try {
    //ServerSocket 객체생성 -> 포트번호 부여해야함!!
    ss= new ServerSocket(2000);
  System.out.println("ss>>>"+ss);
  System.out.println("채팅서버 열리는중");
  // 서버가동 : 클라이언트가 접속할 때까지 기다림!(무한대기)
  while(true){
    //접속한 클라이언트 socket을 s변수에 저장
    socket=ss.accept();
System.out.println("서버에 접속한 클라이언트>>>"+socket);
//접속클라이언트와 서버로 st객체 생성
st = new TmtServerThread(this,socket);
//접속할 때마다 v에 접속 클라이언트 스레드 추가
this.addThread(st);
//Thread 가동 ->run()-> broadCast()-> send()실시간 메소드 호출
st.strat();
}
} catch (Exception e) {
System.out.println("서버 접속 실패하였습니다."+ e);
    }
}

//벡터에 접속한 클라이언트의 스레드 저장
public void addThread(TmtServerThread st){
    v.add(st);
}

//퇴장한클라이언트 스레드 제거
public void removeThread(TmtServerThread st){
    v.remove(st);
}

// 각 클라이언트에게 메세지 출력하는 메소드 , send()호출

public void broadCast(String str){
    for (int i =0; v.size();i++){
        //각각의 클라이언트를 ServerThread 객체로 형변환
        TmtServerThread st = (TmtServerThread)v.elementAt(i);
        //각 스레드 객체에 str문자열 전송
        st.send(str);
    }
}
  public static void main(String[] args) {
    new TmtChatServer();
   
  }
}

