import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditDialog extends JFrame implements ActionListener{
    // 선언
    HomeDisplay      hd          = null;
    JPanel           jp          = new JPanel();
    JButton          jbtn_pic    = new JButton("사진 변경");
    JButton          jbtn_name   = new JButton("이름 변경");
    JButton          jbtn_logout = new JButton("로그아웃");
    boolean          isVisible   = true;
    NameChangeDialog ncd         = new NameChangeDialog(this);
    PicChangeDialog  pcd         = new PicChangeDialog(this);
    
    // 생성자
    EditDialog() {
        System.out.println( "EditDialog 생성자 호출" );
        initDisplay();
        NameChangeDialog namechange = new NameChangeDialog();
        PicChangeDialog picchange = new PicChangeDialog();
    }
    
    EditDialog( HomeDisplay homeD ) {
        System.out.println( "EditDialog 생성자 호출 / 홈디스플레이 초기화" );
        this.hd = homeD;
    }
    
    // 화면
    public void initDisplay() {
        System.out.println( "EditDialog 화면 호출" );
        this.setLayout( null );
        this.add( jbtn_name );
        this.add( jbtn_pic );
        this.add( jbtn_logout );
        this.setSize( 400, 600 );
        this.setVisible( isVisible );
        this.setTitle( "설정화면" );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        jbtn_name.addActionListener( this );
        jbtn_pic.addActionListener( this );
        jbtn_logout.addActionListener( this );
        jbtn_name.setBounds( 100, 10, 200, 50 );
        jbtn_pic.setBounds( 100, 80, 200, 50 );
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
        else if ( obj == jbtn_pic ) {
            pcd.isVisible = true;
            pcd.initDisplay();
        }
    }
}