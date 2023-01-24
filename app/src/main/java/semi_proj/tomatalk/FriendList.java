package semi_proj.tomatalk;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FriendList extends JFrame implements ActionListener { // implements ActionListener
    // 선언부
    TmtMultiChat            tMulti      = new TmtMultiChat( this ); //
    public JList            friendList;
    public DefaultListModel model; // JList에서 목록수가 많아지면 자동으로 스크롤바 생김
    JPanel                  jp_north    = new JPanel();
    JPanel                  jp_south    = new JPanel();
    JButton                 jbtn_edit   = new JButton( "설정" );
    JButton                 jbtn_multi  = new JButton( "단체 채팅" );
    JButton                 jbtn_exit   = new JButton( "로그 아웃" );
    JScrollPane             jsp_display = new JScrollPane( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
    // 필요할때만 스크롤바가 보이도록 함, 스크롤바를 숨김
    Font f = new Font( "Paryrus", Font.BOLD, 30 );// 글자 크기
    // 생성부
    
    public FriendList() {
        System.out.println( "생성자 호출ok" );
        initDisplay();
    }
    
    // 화면처리기
    public void initDisplay() {
        setDefaultCloseOperation( EXIT_ON_CLOSE ); // 화면 끄면 작동도 꺼진다.
        model = new DefaultListModel();
        model.addElement( "박상준" );
        model.addElement( "윤성준" );
        model.addElement( "김성훈" );
        model.addElement( "정다희" );
        model.addElement( "권양아" );
        friendList = new JList( model );
        add( new JScrollPane( friendList ) );
        jp_south.setLayout( new GridLayout( 1, 3 ) );
        jp_north.add( jbtn_edit );
        jp_north.add( jbtn_multi );
        jp_north.add( jbtn_exit );
        // this.add("Center", jtf_display);
        this.add( "South", jp_south );
        this.add( "North", jp_north );
        this.setTitle( "친구목록" );
        this.setSize( 400, 600 );
        jbtn_multi.addActionListener( this );
        jbtn_exit.addActionListener( this );
        jbtn_edit.addActionListener( this );
        
    }
    
    public static void main( String[] args ) {
        FriendList fl = new FriendList();
        fl.setVisible( true );
    }
    // @Override
    // public void actionPerformed(ActionEvent e){ //액션리스너에는 액션Performed메소드 정의
    // Object obj = e.getSource();
    // } //액션리스너에는 액션Performed메소드 정의
    
    @Override
    public void actionPerformed( ActionEvent e ) {
        Object obj = e.getSource();
        
        if ( obj == jbtn_multi ) { ///////////////////////////////////////////////////////// 박상준//단체 채팅창 열기,
            System.out.println( "단체 버튼 눌림" );
            tMulti.isClicked = true; // setVisible을 true로 바꿔줌
            tMulti.initDisplay(); // initDisplay로 창띄움
        }
    }
}