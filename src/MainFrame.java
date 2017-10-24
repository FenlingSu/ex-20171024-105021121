import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MainFrame extends JFrame{
    private int scrW = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int scrH = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frmW = 500, frmH = 450;
    private JDesktopPane jdp = new JDesktopPane();
    private JMenuBar jmb = new JMenuBar();
    private JMenu jmF = new JMenu("File");
    private JMenu jmS = new JMenu("Set");
    private JMenu jmG = new JMenu("Loto");
    private JMenu jmA = new JMenu("About");
    private JMenuItem jmiExit = new JMenuItem("Exit");
    private JMenuItem jmiGame = new JMenuItem("Game");
    private JInternalFrame jif = new JInternalFrame();

    private Container jifcp;
    private JPanel jpl = new JPanel(new GridLayout(1,6,5,5));
    private JPanel jpl1 = new JPanel(new GridLayout(1,2,5,5));
    private JButton jbtcls = new JButton("Close");
    private JButton jbtgen = new JButton("Generate");
    private JLabel jlb[] = new JLabel[6];
    private int data[] = new int[6];
    private Random rnd = new Random(System.currentTimeMillis());

    private LoginFrame loginframe;
    public MainFrame(LoginFrame loginfr){
        loginframe = loginfr;
        initComp();
    }
    private void initComp(){
        this.setBounds(scrW/2-frmW/2,scrH/2-frmH/2,frmW,frmH);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                loginframe.reset();
                loginframe.setVisible(true);
            }
        });
        this.setJMenuBar(jmb);
        this.setContentPane(jdp);
        jif.setBounds(0,0,200,80);
        jmb.add(jmF);
        jmb.add(jmS);
        jmb.add(jmG);
        jmb.add(jmA);
        jmF.add(jmiExit);
        jmG.add(jmiGame);

        jmiExit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                loginframe.reset();
                loginframe.setVisible(true);
            }
        });

        jmiExit.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        jmiGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jdp.add(jif);
                jif.setVisible(true);
                lotoGenerate();
            }
        });
        jmiGame.setAccelerator(KeyStroke.getKeyStroke('L',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));


        jifcp = jif.getContentPane();
        jifcp.add(jpl, BorderLayout.CENTER);
        jifcp.add(jpl1, BorderLayout.SOUTH);
        jpl1.add(jbtcls);
        jpl1.add(jbtgen);

        for(int i=0; i<6; i++){
            jlb[i] = new JLabel(Integer.toString(data[i]));
            jpl.add(jlb[i]);
        }


        jbtcls.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jif.dispose();
            }
        });

        jbtgen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lotoGenerate();
            }
        });

    }
    private void lotoGenerate(){
        int i=0;
        boolean flag = true;
        while (i<6){
            data[i]=rnd.nextInt(42)+1;
            int j=0;
            while(j<i && flag){
                if(data[i] == data[j]){
                    flag = false;
                }
                j++;
            }
            if(flag){
                jlb[i].setText((Integer.toString(data[i])));
                i++;
            }
        }
    }
}