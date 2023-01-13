package semi_proj.tomatalk;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class TmtMultiChat extends JFrame implements ActionListener {
    
    String            roomPW        = null; // 단체방 비밀번호
    String            cols[]        = { "대화명" };
    String            data[][]      = new String[0][1];
    DefaultTableModel dtm           = new DefaultTableModel( data, cols );
    JTable            jtb           = new JTable( dtm );
    JScrollPane       jsp           = new JScrollPane( jtb );
    JPanel            jp            = new JPanel();
    JPanel            jp_msgsend    = new JPanel();
    JPanel            jp_south      = new JPanel();
    JTextField        jtf_msg       = new JTextField( 15 );
    JButton           jbtn_send     = new JButton( "전송" );
    JButton           jbtn_edit     = new JButton( "설정" );
    JButton           jbtn_exit = new JButton( "나가기" );
    JButton           jbtn_multi = new JButton( "단체" );
    JTextArea         jta_display   = new JTextArea( 25, 50 );
    JScrollPane       jsp_display   = new JScrollPane( jta_display, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
    FriendList        fList         = null;
    boolean           isClicked     = false;
    
    // 생성자
    public TmtMultiChat() {
        
    }
    
    public TmtMultiChat( FriendList fl ) { //FriendList에서 인스턴스화해서 사용할 생성자
        this.fList = fl; //// 파라미터로 MultiChat의 fl을 바라봄(이어주기)
    }
    
    // 화면그리기
    public void initDisplay() {
        jtf_msg.addActionListener( this );
        jbtn_send.addActionListener( this );
        // roomPW = JOptionPane.showInputDialog( "비밀번호를 입력하세요." );
        
        jp.setLayout( new BorderLayout() );
        jp.add( "North", jsp_display );
        jp.add( "Center", jp_msgsend );
        jp.add( "South", jp_south );
        
        jp_msgsend.setLayout( new BorderLayout() );
        jp_msgsend.add( "Center", jtf_msg );
        jp_msgsend.add( "East", jbtn_send );
        
        jp_south.setLayout( new GridLayout() );
        jp_south.setLayout( new GridLayout( 1, 3 ) );
        jp_south.add( jbtn_edit );
        jp_south.add( jbtn_multi );
        jp_south.add( jbtn_exit );
        
        jta_display.setLineWrap( true );
        this.setLayout( new GridLayout( 1, 2 ) );
        this.add( jp );
        this.setSize( 400, 550 );
        this.setTitle( "Tomatalk_MultiChat" );
        this.setVisible( !isClicked );
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
    }
    
    public static void main( String[] args ) {
        TmtMultiChat tMultiChat = new TmtMultiChat();
        tMultiChat.initDisplay();
    }
    
    @Override
    public void actionPerformed( ActionEvent e ) {
        Object obj = e.getSource();
        
        if ( jtf_msg == obj || jbtn_send == obj ) {
            String msg = jtf_msg.getText();
            jta_display.append( msg + "\n" );
            jtf_msg.setText( "" );
        }

    }
}