import PaqGroupINameAgustin.Container;
import PaqGroupINameAgustin.ContainerHub;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

public class Port extends JFrame implements WindowListener {
//1.ATTRIBUTES
    private JTextField IdNumberField;
    private JComboBox ListOfCountries;
    private JTextField WeightField;
    private JRadioButton Priority1;
    private JTextArea descriptionAbove;
    private JTextArea planOfTheSecondHub;
    private JTextField remitentCompany;
    private JTextField receiverCompany;
    private JCheckBox customInspectionCheckBox;
    private JButton pileButton;
    private JButton unpileButton;
    private JButton showContainerDescriptionButton;
    private JButton numberOfContainersButton;
    private JComboBox ListOfCountries2;
    private JTextField putNumberHereTextField;
    private JTextField columnNumberField;
    private JTextArea descriptionBelow;
    private JPanel MainPanel;
    private JRadioButton Priority3;
    private JRadioButton Priority2;
    private JTextArea planOfTheHub;
    private JTextArea planOfTheThirdHub;
    private JLabel idNumberLabel;
    private JButton weightButton;
    //2.CONSTRUCTORS

    public Port() {
        ContainerHub hub=new ContainerHub();
        ContainerHub hub2=new ContainerHub();
        ContainerHub hub3=new ContainerHub();
        setContentPane(MainPanel);
        ImageIcon imageIcon = new ImageIcon("src/puerto-ciudad.png");
        setIconImage(imageIcon.getImage());
        setTitle("Port Management");
        setSize(1500,1600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        addWindowListener(this);
        showContainerDescriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id=IdNumberField.getText();
                if(!hub.displayAllDataFromAnIdNumber(id).equals("No container was found with that identification number."))
                    descriptionBelow.setText(hub.displayAllDataFromAnIdNumber(id));
                else{
                    descriptionBelow.setText("No container was found with that identification number.");
                    JOptionPane.showMessageDialog(null,"No container was found with that identification number.");
                }
            }
        });
        pileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id=IdNumberField.getText();
                if(!id.matches("[0-9]+")){
                    JOptionPane.showMessageDialog(null,"Invalid ID format (only numbers)");
                    return;
                }
                for(int i=0;i<hub.getHub().length;i++){
                    for(int k=0;k<hub.getHub().length;k++){
                        if(hub.getHub()[i][k].getIdNumber().equals(id)){
                            JOptionPane.showMessageDialog(null,"This id has been already assgined to a container. Please introduce another id number.");
                            return;
                        }
                    }
                }
                for(int i=0;i<hub2.getHub().length;i++){
                    for(int k=0;k<hub2.getHub().length;k++){
                        if(hub2.getHub()[i][k].getIdNumber().equals(id)){
                            JOptionPane.showMessageDialog(null,"This id has been already assgined to a container. Please introduce another id number.");
                            return;
                        }
                    }
                }
                for(int i=0;i<hub3.getHub().length;i++){
                    for(int k=0;k<hub3.getHub().length;k++){
                        if(hub3.getHub()[i][k].getIdNumber().equals(id)){
                            JOptionPane.showMessageDialog(null,"This id has been already assgined to a container. Please introduce another id number.");
                            return;
                        }
                    }
                }
                String weight= WeightField.getText();
                if(!weight.matches("[0-9]+")){
                    JOptionPane.showMessageDialog(null,"Invalid WEIGHT format (only numbers)");
                    return;
                }
                String countryOfOrigin = (String) ListOfCountries.getSelectedItem();
                boolean hasBeenInspected= customInspectionCheckBox.isSelected();
                int priority = 0;
                if(Priority1.isSelected()){
                    priority=1;
                }
                else if(Priority2.isSelected()){
                    priority=2;
                }
                if(Priority3.isSelected()){
                    priority=3;
                    }
                if(priority==0){
                    JOptionPane.showMessageDialog(null,"Please select a priority level");
                    return;
                }

                String description= descriptionAbove.getText();
                if(description.matches("Description is shown here")){
                    JOptionPane.showMessageDialog(null,"Please... write a proper description!");
                    return;
                }
                String companyThatSendsTheContainer=remitentCompany.getText();
                if(!companyThatSendsTheContainer.matches("[a-zA-Z]+")){
                    JOptionPane.showMessageDialog(null,"Invalid format in REMITENT COMPANY (only letters)");
                    return;
                }
                String companyThatReceivesTheContainer=receiverCompany.getText();
                if(!companyThatReceivesTheContainer.matches("[a-zA-Z]+")){
                    JOptionPane.showMessageDialog(null,"Invalid format in RECEIVER COMPANY (only letters)");
                    return;
                }
                //si lo estamos apilando es que no esta free
                Container containerToPile=new Container(false,id,weight,countryOfOrigin,hasBeenInspected,
                        priority,description,companyThatSendsTheContainer,companyThatReceivesTheContainer);
                switch (containerToPile.getPriorityLevel()) {
                    case 1 -> {
                        hub.stackAContainerAccordingToPriority(containerToPile);
                        if (hub.isFullPriority1())
                            hub2.stackAContainerAccordingToPriority(containerToPile);
                        if (hub2.isFullPriority1())
                            hub3.stackAContainerAccordingToPriority(containerToPile);
                    }
                    case 2 -> {
                        hub.stackAContainerAccordingToPriority(containerToPile);
                        if (hub.isFullPriority2())
                            hub2.stackAContainerAccordingToPriority(containerToPile);
                        if (hub2.isFullPriority2())
                            hub3.stackAContainerAccordingToPriority(containerToPile);
                    }
                    case 3 -> {
                        hub.stackAContainerAccordingToPriority(containerToPile);
                        if (hub.isFullPriority3())
                            hub2.stackAContainerAccordingToPriority(containerToPile);
                        if (hub2.isFullPriority3())
                            hub3.stackAContainerAccordingToPriority(containerToPile);
                    }
                }
                planOfTheHub.setText(hub.toString());
                planOfTheSecondHub.setText(hub2.toString());
                planOfTheThirdHub.setText(hub3.toString());
            }
        });
        unpileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String column=columnNumberField.getText();
                if(!column.matches("[0-9]+")){
                    JOptionPane.showMessageDialog(null,"Invalid COLUMN NUMBER format (only numbers)");
                    return;
                }
                int decide=hub3.removeContainerFromAColumn(Integer.parseInt(column));
                if(decide==-1){
                    decide=hub2.removeContainerFromAColumn(Integer.parseInt(column));
                    if(decide==-1){
                        decide=hub.removeContainerFromAColumn(Integer.parseInt(column));
                        if(decide==-1){
                            JOptionPane.showMessageDialog(null,"We couldn't unpile any container from column number "+column);
                        }
                    }
                }
                planOfTheHub.setText(hub.toString());
                planOfTheSecondHub.setText(hub2.toString());
                planOfTheThirdHub.setText(hub3.toString());
            }
        });

        numberOfContainersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String country= (String) ListOfCountries2.getSelectedItem();
                putNumberHereTextField.setText(String.valueOf(hub.numberOfContainersFromACertainCountry(country)));
            }
        });
        Priority1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Priority1.isSelected()){
                    Priority3.setSelected(false);
                    Priority2.setSelected(false);
                }
            }
        });
        Priority2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Priority2.isSelected()){
                    Priority1.setSelected(false);
                    Priority3.setSelected(false);
                }
            }
        });
        Priority3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Priority3.isSelected()){
                    Priority1.setSelected(false);
                    Priority2.setSelected(false);
                }
            }
        });
        /////////////////////////////////EXERCISE OF THE EXAM///////////////////////////////////////////
        weightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String weight = WeightField.getText();
                if(Priority1.isSelected()){
                    hub.markAsChecked(weight,hub.getHub());
                    //display a new window
                    JLabel label = new JLabel();
                    JTextArea area = new JTextArea();
                    area.setText(hub.markAsChecked(weight,hub.getHub()));
                    label.add(area);
                    JFrame frame = new JFrame();
                    frame.setLayout(null);
                    frame.setContentPane(label);
                    frame.setSize(600,600);
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
                if(Priority2.isSelected()){
                    hub2.markAsChecked(weight,hub2.getHub());
                    //display a new window
                    JLabel label = new JLabel();
                    JTextArea area = new JTextArea();
                    area.setText(hub2.markAsChecked(weight,hub2.getHub()));
                    label.add(area);
                    JFrame frame = new JFrame();
                    frame.setLayout(null);
                    frame.setContentPane(label);
                    frame.setSize(600,600);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.setResizable(false);
                }

                if(Priority3.isSelected()){
                    hub3.markAsChecked(weight,hub3.getHub());
                    //display a new window
                    JLabel label = new JLabel();
                    JTextArea area = new JTextArea();
                    area.setText(hub3.markAsChecked(weight,hub3.getHub()));
                    label.add(area);
                    JFrame frame = new JFrame();
                    frame.setLayout(null);
                    frame.setContentPane(label);
                    frame.setSize(600,600);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.setResizable(false);
                }
            }
        });
    }
    public static void main(String[] args) {
        Port vamos= new Port();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?");
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
