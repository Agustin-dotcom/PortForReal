import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;

public class Port extends JFrame {

    private JTextField tfIdNumber;//tf stands for "text field"
    private JComboBox comboBox1;
    private JTextField tfWeight;
    private JRadioButton a1RadioButton;
    private JTextArea descriptionIsShownHereTextArea;
    private JTextArea textArea1;
    private JTextField textField3;
    private JTextField textField4;
    private JCheckBox customInspectionCheckBox;
    private JButton pileButton;
    private JButton unpileButton;
    private JButton showContainerDescriptionButton;
    private JButton numberOfContainersButton;
    private JComboBox comboBox2;
    private JTextField putNumberHereTextField;
    private JTextField columnNumberTextField;
    private JTextArea descriptionIsShownHereTextArea1;
    private JTextField companyLogoTextField;
    private JPanel MainPanel;
    private JRadioButton a3RadioButton;
    private JRadioButton a2RadioButton;

    //2.CONSTRUCTORS
    public Port() {

        ValenciaPort valencia=new ValenciaPort();
        valencia.initializeHub();
        setContentPane(MainPanel);
        setTitle("Port Management");//JFrame("Port Management");
        setSize(1500,600);//pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        //setLayout(new BorderLayout(10,5));
        //setBackground(Color.MAGENTA);
        setResizable(false);
        //setLayout(null);//??
        //getIconImage();//setIconImage(new ImageIcon().getImage());
        tfIdNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valencia.idNumber= Integer.parseInt(tfIdNumber.getText());
            }
        });
        tfWeight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valencia.weight=Integer.parseInt(tfWeight.getText());
            }
        });
        descriptionIsShownHereTextArea.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                valencia.contentDescription=descriptionIsShownHereTextArea.getText();
            }
        });
        textField3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valencia.companyThatSendsTheContainer=textField3.getText();
            }
        });
        textField4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valencia.companyThatReceivesTheContainer=textField4.getText();
            }
        });
        customInspectionCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    valencia.hasBeenInspected=true;
            }
        });
        a1RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valencia.priorityLevel=1;
            }
        });
        a2RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valencia.priorityLevel=2;
            }
        });
        a3RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valencia.priorityLevel=3;
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        pileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valencia.stackAContainerAccordingToPriority(valencia.priorityLevel);
            }
        });
        unpileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valencia.removeContainerFromAColumn(columnNumberTextField.getColumns());
            }
        });

        showContainerDescriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                descriptionIsShownHereTextArea1.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        valencia.displayAllDataFromAnIdNumber(valencia.idNumber);
                    }
                });
            }
        });
        textArea1.addComponentListener(new ComponentAdapter() {
            @Override
            public String toString() {
                return valencia.toString();
            }
        });
    }
    public static void main(String[] args) {
        Port vamos= new Port();
        //JFrame frame =new JFrame();
        //JPanel panel=new JPanel();
        //ValenciaPort port=new ValenciaPort();
        //JPanel panel=new JPanel();
        //panel.setVisible(true);
    }
}
