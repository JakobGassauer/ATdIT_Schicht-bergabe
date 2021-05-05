package library.presentation;

import library.model.implementation.Resident;
import library.persistence.implementation.DatabaseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;


public class GUI extends JFrame {

    static boolean beingEdited = false;
    static boolean isSaved = true;
    static boolean hasSwitched = false;
    static int buttonIdentification = -1;
    static int indexComparison = -1;
    static int lastButton = -1;


    ImageIcon saveicon;
    ImageIcon editicon;

    Container c;

    CardLayout cl = new CardLayout();

    JPanel jpResidentRoom, jpFilterTextAll, jpFilter, jpTextResident, jpResident, jpRoom, jpSpecific, jpEditResident, jpTextResidentAndEdit, cards;

    JScrollPane spBaseData, spMedication, spDiagnoseblatt, spClosestRelative, spVisits, spOther;
    JTextArea taBaseData, taMedication, taDiagnoseblatt, taClosestRelative, taVisits, taOther;

    JButton[] btnResident;
    JButton[] btnEditResident;
    JButton btnAll;
    JLabel[] lblRoom;
    String[] shifts;
    String[] time;
    JComboBox jcbShift;
    JComboBox jcbTime;


    JTextArea[] taResident;
    JScrollPane[] spTextResident;
    JTextArea taAll;


    GridBagConstraints gbc = new GridBagConstraints();

    Color lightgrey = new Color(245, 245, 245);
    Color lightyellow = new Color(255, 255, 202);


    public GUI() {
        ArrayList<Resident> residents;
        residents = DatabaseService.getResidents();

        saveicon = new ImageIcon("Saveicon.png");
        editicon = new ImageIcon("Editicon.png");

        c = getContentPane();


        jpResidentRoom = new JPanel(new GridBagLayout());
        jpFilterTextAll = new JPanel(new BorderLayout());
        jpFilter = new JPanel(new GridLayout(2, 1));
        jpTextResident = new JPanel(new GridLayout(10, 1));
        jpResident = new JPanel(new GridLayout(10, 1));
        jpRoom = new JPanel(new GridLayout(10, 1));
        jpEditResident = new JPanel(new GridLayout(10, 1));
        jpSpecific = new JPanel(new GridBagLayout());
        jpTextResidentAndEdit = new JPanel(new GridBagLayout());
        cards = new JPanel(cl);

        taBaseData = new JTextArea("Stammdaten");
        taMedication = new JTextArea("Medikation");
        taDiagnoseblatt = new JTextArea("Diagnoseblatt");
        taClosestRelative = new JTextArea("Angehöriger");
        taVisits = new JTextArea("Besuch");
        taOther = new JTextArea("Sonstiges");
        taResident = new JTextArea[residents.size()];


        taBaseData.setFont(new Font("TimesNewRoman", Font.BOLD, 18));
        taBaseData.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        taMedication.setFont(new Font("TimesNewRoman", Font.BOLD, 18));
        taDiagnoseblatt.setFont(new Font("TimesNewRoman", Font.BOLD, 18));
        taClosestRelative.setFont(new Font("TimesNewRoman", Font.BOLD, 18));
        taVisits.setFont(new Font("TimesNewRoman", Font.BOLD, 18));
        taOther.setFont(new Font("TimesNewRoman", Font.BOLD, 18));


        spBaseData = new JScrollPane(taBaseData);
        spMedication = new JScrollPane(taMedication);
        spDiagnoseblatt = new JScrollPane(taDiagnoseblatt);
        spClosestRelative = new JScrollPane(taClosestRelative);
        spVisits = new JScrollPane(taVisits);
        spOther = new JScrollPane(taOther);
        spTextResident = new JScrollPane[residents.size()];


        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weighty = 1;
        gbc.weightx = 1;
        jpSpecific.add(spBaseData, gbc);
        gbc.gridx = 1;
        jpSpecific.add(spMedication, gbc);
        gbc.gridx = 2;
        jpSpecific.add(spDiagnoseblatt, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        jpSpecific.add(spClosestRelative, gbc);
        gbc.gridx = 1;
        jpSpecific.add(spVisits, gbc);
        gbc.gridx = 2;
        jpSpecific.add(spOther, gbc);


        c.add(jpResidentRoom, BorderLayout.WEST);
        c.add(jpFilterTextAll, BorderLayout.NORTH);
        c.add(cards, BorderLayout.CENTER);
        jpFilterTextAll.add(jpFilter, BorderLayout.WEST);
        cards.add(jpTextResidentAndEdit, "Bewohner");
        cards.add(jpSpecific, "Spezifisch");
        cl.show(cards, "Bewohner");


        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weighty = 1;
        gbc.weightx = 1;
        jpResidentRoom.add(jpResident, gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        jpResidentRoom.add(jpRoom, gbc);


        gbc.gridx = 0;
        jpTextResidentAndEdit.add(jpTextResident, gbc);
        gbc.weightx = 0;
        gbc.gridx = 1;
        jpTextResidentAndEdit.add(jpEditResident, gbc);


        btnResident = new JButton[10];
        btnEditResident = new JButton[10];
        lblRoom = new JLabel[10];
        shifts = new String[]{"Frühschicht", "Spätschicht", "Nachtschicht"};
        time = new String[]{"25.04.2021", "26.04.2021", "27.04.2021", "28.04.2021", "29.04.2021", "30.04.2021"};


        GUI.ButtonListener1 bL1 = new GUI.ButtonListener1();
        GUI.Buttonlistener2 bl2 = new GUI.Buttonlistener2();


        for (int i = 0; i < residents.size(); i++) {
            btnResident[i] = new JButton(residents.get(i).getName()+ " " +residents.get(i).getSurname());
            btnResident[i].setBackground(lightgrey);
            btnResident[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            btnResident[i].setPreferredSize(new Dimension(302, 51));
            btnResident[i].setFont(new Font("TimesNewRoman", Font.BOLD, 18));
            jpResident.add(btnResident[i]);
            btnResident[i].addActionListener(bL1);
        }

        for (int i = 0; i < residents.size(); i++) {
            lblRoom[i] = new JLabel("Raum " + (residents.get(i).getRoom()), SwingConstants.CENTER);
            lblRoom[i].setBackground(lightgrey);
            lblRoom[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            lblRoom[i].setOpaque(true);
            lblRoom[i].setPreferredSize(new Dimension(132, 51));
            lblRoom[i].setFont(new Font("TimesNewRoman", Font.BOLD, 18));
            jpRoom.add(lblRoom[i]);
        }

        for (int i = 0; i < residents.size(); i++) {
            taResident[i] = new JTextArea("TEST " + (i + 1));
            taResident[i].setLineWrap(true);
            taResident[i].setWrapStyleWord(true);
            taResident[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            taResident[i].setFont(new Font("TimesNewRoman", Font.PLAIN, 15));
            taResident[i].setEditable(false);
            spTextResident[i] = new JScrollPane(taResident[i]);
            jpTextResident.add(spTextResident[i]);

        }

        for (int i = 0; i < residents.size(); i++) {
            btnEditResident[i] = new JButton();
            btnEditResident[i].setBackground(lightgrey);
            btnEditResident[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            btnEditResident[i].setPreferredSize(new Dimension(30, 51));
            jpEditResident.add(btnEditResident[i]);
            btnEditResident[i].setIcon(editicon);
            btnEditResident[i].addActionListener(bl2);
        }


        jcbShift = new JComboBox(shifts);
        jpFilter.add(jcbShift);
        jcbTime = new JComboBox(time);
        jpFilter.add(jcbTime);
        jcbTime.setBorder(BorderFactory.createMatteBorder(5, 30, 17, 300, lightyellow));
        jcbShift.setBorder(BorderFactory.createMatteBorder(17, 30, 5, 300, lightyellow));
        jpFilter.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        taAll = new JTextArea(" Hier steht ein text, der über bestimmte Ereiginisse berichtet, die Bewohnerunspezifisch sind.");
        taAll.setLineWrap(true);
        taAll.setWrapStyleWord(true);
        jpFilterTextAll.add(taAll, BorderLayout.CENTER);
        taAll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        taAll.setBackground(lightyellow);
        taAll.setEditable(false);
        taAll.setFont(new Font("TimesNewRoman", Font.BOLD, 15));


        btnAll = new JButton();
        btnAll.setBackground(lightgrey);
        btnAll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnAll.setPreferredSize(new Dimension(30, 51));
        jpFilterTextAll.add(btnAll, BorderLayout.EAST);
        btnAll.setIcon(editicon);
        btnAll.addActionListener(bl2);
    }

    class ButtonListener1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int index = Arrays.asList(btnResident).indexOf(e.getSource());

            if (isSaved == true) {
                if (buttonIdentification == index) {

                    cl.show(cards, "Bewohner");
                    buttonIdentification = -1;
                    hasSwitched = false;
                    btnResident[index].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    lblRoom[index].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                } else {
                    if (hasSwitched == false) {

                        buttonIdentification = index;
                        //Aufspielen der Daten auf die Bewohnerübersicht
                        cl.show(cards, "Spezifisch");
                        btnResident[index].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 0, Color.lightGray));
                        lblRoom[index].setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, Color.lightGray));
                        hasSwitched = true;
                        lastButton = index;

                    } else {
                        buttonIdentification = index;
                        //Aufspielen der Daten auf die Bewohnerübersicht
                        cl.show(cards, "Spezifisch");
                        hasSwitched = true;

                        btnResident[lastButton].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        lblRoom[lastButton].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                        btnResident[index].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 0, Color.lightGray));
                        lblRoom[index].setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, Color.lightGray));

                        lastButton = index;
                    }
                }
            }

        }
    }

    class Buttonlistener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


 //           System.out.println(index);

            if (e.getSource() != btnAll) {

                int index = Arrays.asList(btnEditResident).indexOf(e.getSource());

                if (beingEdited == false) {

                    if (isSaved == true) {

                        beingEdited = true;
                        taResident[index].setEditable(true);
                        btnEditResident[index].setIcon(saveicon);
                        isSaved = false;
                        indexComparison = index;

                    }
                } else {
                    if (isSaved == false && btnAll.getIcon()==editicon) {
                        if (indexComparison == index && e.getSource() != btnAll) {

                            taResident[index].setEditable(false);
                            btnEditResident[index].setIcon(editicon);
                            //Sachen abspeichern Mehode
                            isSaved = true;
                            beingEdited = false;

                        }
                    }
                }
            } else {
                if (beingEdited == false) {

                    if (isSaved == true) {

                        beingEdited = true;
                        taAll.setEditable(true);
                        btnAll.setIcon(saveicon);
                        isSaved = false;

                    }
                } else {
                    if (isSaved == false && btnAll.getIcon()==saveicon) {
                        if (btnAll ==e.getSource()) {

                            taAll.setEditable(false);
                            btnAll.setIcon(editicon);
                            //Sachen abspeichern Mehode
                            isSaved = true;
                            beingEdited = false;

                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        GUI frame = new GUI();
        frame.setTitle("Schichtplan");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
