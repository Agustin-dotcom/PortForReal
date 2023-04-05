import PaqGroupINameAgustin.Container;
import PaqGroupINameAgustin.ContainerHub;
//Exceptions
//Si pedis dos veces seguidas el number of containers no funciona
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JFrame;

public class Port extends JFrame {

    private JTextField IdNumberField;//tf stands for "text field"
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
    //2.CONSTRUCTORS

    public Port() {

        ContainerHub hub=new ContainerHub();
        ContainerHub hub2=new ContainerHub();
        ContainerHub hub3=new ContainerHub();
        setContentPane(MainPanel);
        setTitle("Port Management");
        setSize(1500,1600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        showContainerDescriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id=IdNumberField.getText();
                if(!hub.displayAllDataFromAnIdNumber(id).equals("No container was found with that identification number."))
                    descriptionBelow.setText(hub.displayAllDataFromAnIdNumber(id));
                else
                    descriptionBelow.setText("No container was found with that identification number.");
            }
        });
        pileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id=IdNumberField.getText();
                String weight= WeightField.getText();
                String countryOfOrigin = (String) ListOfCountries.getSelectedItem();
                boolean hasBeenInspected= customInspectionCheckBox.isSelected();
                int priority = 0;
                if(Priority1.isSelected()){
                    priority=1;}
                else if(Priority2.isSelected()){
                        priority=2;}
                        if(Priority3.isSelected()){
                            priority=3;
                            }

                String description= descriptionAbove.getText();
                        String companyThatSendsTheContainer=remitentCompany.getText();
                        String companyThatReceivesTheContainer=receiverCompany.getText();
                //si lo estamos apilando es que no esta free
                Container containerToPile=new Container(false,id,weight,countryOfOrigin,hasBeenInspected,
                        priority,description,companyThatSendsTheContainer,companyThatReceivesTheContainer);
                switch(containerToPile.getPriorityLevel()){
                    case 1:
                        hub.stackAContainerAccordingToPriority(containerToPile);
                        if(hub.isFullPriority1)
                            hub2.stackAContainerAccordingToPriority(containerToPile);
                        if(hub2.isFullPriority1)
                            hub3.stackAContainerAccordingToPriority(containerToPile);
                        break;
                    case 2:
                        hub.stackAContainerAccordingToPriority(containerToPile);
                        if(hub.isFullPriority2)
                            hub2.stackAContainerAccordingToPriority(containerToPile);
                        if(hub2.isFullPriority2)
                            hub3.stackAContainerAccordingToPriority(containerToPile);
                        break;
                    case 3:
                        hub.stackAContainerAccordingToPriority(containerToPile);
                        if(hub.isFullPriority3)
                            hub2.stackAContainerAccordingToPriority(containerToPile);
                        if(hub2.isFullPriority3)
                            hub3.stackAContainerAccordingToPriority(containerToPile);
                        break;
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
                int decide=hub3.removeContainerFromAColumn(Integer.parseInt(column));
                if(decide==-1){
                    decide=hub2.removeContainerFromAColumn(Integer.parseInt(column));
                    if(decide==-1)
                        hub.removeContainerFromAColumn(Integer.parseInt(column));
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
    }
    public static void main(String[] args) {
        Port vamos= new Port();
    }
}
