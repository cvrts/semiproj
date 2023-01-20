package semi_proj.tomatalk;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDialog extends JFrame implements ActionListener{
    // 선언
    TmtClient        tcl         =null;
    JPanel           jp          = new JPanel();
    JButton          jbtn_dbd    = new JButton("회원 탈퇴");
    JButton          jbtn_name   = new JButton("이름 변경");
    JButton          jbtn_logout = new JButton("로그아웃");
    boolean          isVisible   = true;
    NameChangeDialog ncd         = new NameChangeDialog(this);
    DBDeleteDialog   dbd         = new DBDeleteDialog(this);
    
    // 생성자
    EditDialog() {
        System.out.println( "EditDialog 생성자 호출" );
        initDisplay();
        NameChangeDialog namechange = new NameChangeDialog();
        DBDeleteDialog dbdlete= new DBDeleteDialog();
    }
    
    EditDialog( TmtClient tmtClient ) {
        System.out.println( "EditDialog 생성자 호출 / TmtClient 초기화" );
        this.tcl = tmtClient;
    }
    
    // 화면
    public void initDisplay() {
        System.out.println( "EditDialog 화면 호출" );
        this.setLayout( null );
        this.add( jbtn_name );
        this.add( jbtn_dbd );
        this.add( jbtn_logout );
        this.setSize( 400, 600 );
        this.setVisible( isVisible );
        this.setTitle( "설정화면" );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        jbtn_name.addActionListener( this );
        jbtn_dbd.addActionListener( this );
        jbtn_logout.addActionListener( this );
        jbtn_name.setBounds( 100, 10, 200, 50 );
        jbtn_dbd.setBounds( 100, 80, 200, 50 );
        jbtn_logout.setBounds( 100, 150, 200, 50 );
    }
    
    ///////////////////////////////////////////////////메인 메소드 삭제 예정///////////////////////////////////////////////////
    
    public static void main( String[] args ) {
        EditDialog ed = new EditDialog();
        ed.initDisplay();
    }
    
    ///////////////////////////////////////////////////메인 메소드 삭제 예정///////////////////////////////////////////////////

    
    @Override
    public void actionPerformed( ActionEvent e ) {
        Object obj = e.getSource();
        
        if ( obj == jbtn_name ) {
            ncd.isVisible = true;
            ncd.initDisplay();
        }
        else if ( obj == jbtn_dbd ) {
            dbd.isVisible = true;
            dbd.initDisplay();
        }
        else if (obj == jbtn_logout){
            System.exit(0);
        }
    }
}