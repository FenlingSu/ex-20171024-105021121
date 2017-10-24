import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private Container cp;
    private JLabel jlbid = new JLabel("ID: ");
    private JLabel jlbpw = new JLabel("PassWord: ");
    private JTextField jtfid = new JTextField();
    private JPasswordField jtfpw = new JPasswordField();
    // private JTextField jtfpw = new JTextField();
    private JButton jbtexit = new JButton("Exit");
    private JButton jbtlog = new JButton("Login");
    private Dimension dis = Toolkit.getDefaultToolkit().getScreenSize();
    private int frmW = 300, frmH = 150, disW, disH;

    public LoginFrame(){
        initComp();
    }

    private void initComp(){
        disW = dis.width;
        disH = dis.height;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(disW/2-frmW/2,disH/2-frmH/2,frmW,frmH);

        cp = this.getContentPane();
        cp.setLayout(new GridLayout(3,2,3,3));
        cp.add(jlbid);
        jlbid.setHorizontalAlignment(JLabel.RIGHT);
        cp.add(jtfid);
        cp.add(jlbpw);
        jlbpw.setHorizontalAlignment(JLabel.RIGHT);
        cp.add(jtfpw);
        cp.add(jbtexit);
        cp.add(jbtlog);

        jbtexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        jbtlog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(jtfid.getText().equals("h304") && new String(jtfpw.getPassword()).equals("23323456")){
                    MainFrame mfm = new MainFrame(LoginFrame.this);
                    mfm.setVisible(true);
                    LoginFrame.this.setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(LoginFrame.this,"Error");
                }
            }
        });

    }

    public void reset(){
        jtfid.setText("");
        jtfpw.setText("");
    }
}