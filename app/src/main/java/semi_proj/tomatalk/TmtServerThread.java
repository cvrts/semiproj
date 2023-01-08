package semi_proj.tomatalk;
// 서버에서 각 클라이언트의 요청을 처리할 스레드

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.print.event.PrintEvent;

public class TmtServerThread extends Thread{
//클라이언트 소켓 저장
Socket socket;

TmtChatServer tmtChatServer;
//입출력
BufferedReader br;
PrintWriter pw;
//전달 할 문자열
String str;

//대화명(ID)
String name;


public TmtServerThread(TmtChatServer tmtChatServer, Socket socket){
    /* tmtChatServer = new TmtChatServer 사용 불가함
     * 서버가 두번 가동되기 때문에 충돌이 일어난다. 
     * 따라서 매개변수를 이용해서 객체를 얻어야 한다.
     */
this.tmtChatServer=tmtChatServer;
//접속한 클라이언트의 정보를 저장한다.
this.socket=socket;
//데이터 전송을 위한 입출력스트림
try {
    //입력
    //socket.getInputStream() =>접속 클라이언트의 InputStream 얻어옴
    br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    //출력
    pw= new PrintWriter(socket.getOutputStream(),true);
} catch (Exception e) {
   System.out.println("에러발생>>>"+e);
    }
}

    public void send(String str) {
        pw.println(str);//문자열 출력
        pw.flush();//버퍼에 남아있는 것을 비워냄
    }
@Override
    public void run(){
        try {
            //대화명 입력받기
            pw.println("대화명을 입력하세요");
            name = br.readLine();

            //서버에서 각 클라이언트에 대화명 출력
            tmtChatServer.broadCast("["+name+"]"+"님이 입장했습니다.");
            
            //무한 대기하며 입력한 메세지를 각 클라이언트에 계속 전달한다.
            while((str = br.readLine())!= null){
                tmtChatServer.broadCast("["+name+"]"+str);
            }
        } catch (Exception e) {
            tmtChatServer.removeThread(this);
            tmtChatServer.broadCast("["+name+"]"+"님이 퇴장했습니다.");
            // 콘솔에 퇴장 클라이언트의 IP 주소 출력
            System.out.println(socket.getInetAddress()+"의 연결이 종료됨");
        }
    }

    public void strat() {
    }

}
