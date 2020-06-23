package com.company;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class gui{
    private JFrame frame;
    private JPanel mainPage;
    private JButton search;
    private JTextField CDnameSearch;
    private Container cp;
    public static Catelogue newOne;

    public gui(){
        //Setting for the frame
        frame = new JFrame();
        frame.setSize(600,300); //Set the size of the frame
        mainPage = new JPanel();
        cp = frame.getContentPane();
        cp.add(mainPage);
        frame.setTitle("CD Rental Service");//Set the title of GUI
        mainPage.setBackground(new Color(53,54,58)); //Create the background color for the GUI
        mainPage.setLayout(null); //Set the layout (Same like app application

        //Insert the title
        JLabel title = new JLabel("CD Rental Service"); //Set the title for the CD Rental Servide
        Font  f4  = new Font(Font.SANS_SERIF,  Font.BOLD, 40); //Set the font family , font type and font size
        title.setBounds(200,70,500,70); //Set the location (first 2) and the size of title
        title.setFont(f4); // Set the font to the font's setting
        title.setForeground(Color.WHITE); //Set the font color to white
        mainPage.add(title); //Add the title inside the Gui

        //Insert the search bar
        JLabel pls = new JLabel("Please enter the CD you want to find. Thank you");
        Font ff = new Font(Font.SANS_SERIF,Font.PLAIN,15);
        pls.setBounds(210,130,400,30);
        pls.setFont(ff);
        pls.setForeground(Color.WHITE);
        mainPage.add(pls);
        CDnameSearch = new JTextField(15);
        CDnameSearch.setBounds(200,165,300,30);
        CDnameSearch.setBorder(BorderFactory.createEmptyBorder());
        CDnameSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (e.getOffset () + e.getLength () == e.getDocument ().getLength ())
                    SwingUtilities.invokeLater (new Runnable()
                    {
                        @Override
                        public void run ()
                        {
                            predict (CDnameSearch);
                        }
                    });
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //Do nothing
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //Do nothing
            }
        });
        mainPage.add(CDnameSearch);

        //Insert the button into the Gui
        search = new JButton();
        search.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\xdman\\Desktop\\Sem 4\\OOP\\myProject\\download.jpg").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT)) );
        search.setBounds(500,165,30,30);
        search.setBackground(Color.WHITE);
        search.setBorder(BorderFactory.createEmptyBorder());

        mainPage.add(search);

        //Insert the image into the GUI
        JLabel logo = new JLabel(new ImageIcon("C:\\Users\\xdman\\Desktop\\Sem 4\\OOP\\myProject\\fc8339156f7c62479ebc1138273102d0.jpg"));
        logo.setBounds(-30,50,250,180); //Set the location (first 2) and the size of the picture
        mainPage.add(logo); //Add the image inside the Gui

        //Set the function for the button
        ActionListener action1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String movie = CDnameSearch.getText();
                System.out.println(movie);
                for(CompactDisk xd:newOne.disk){
                    if(movie.equals(xd.getMovieTitle().toLowerCase())){
                        System.out.println(movie);
                        new DiskPage(frame,cp,xd);
                    }
                }

            }
        };
        search.addActionListener(action1);
        frame.setVisible(true);
    }

    private static void predict (JTextField field) {
        String text = field.getText();

        String prediction = null;

        for (CompactDisk cd: newOne.disk) {
            String color = cd.getMovieTitle().toLowerCase();
            if (color.startsWith(text) && !color.equals(text)) {
                if (prediction != null) return;
                prediction = color;
            }
            if (prediction != null)
            {
                field.setText (prediction);

                field.setCaretPosition (text.length ());
                field.select (text.length (), prediction.length ());
            }
        }
    }

    public static void main(String[] args){
        Vector<CD> CDone = new Vector<CD>();
        CDone.add(new CD("Joker",true,25.00));
        CDone.add(new CD("Ghajini",true,30.00));
        CDone.add(new CD("Sonic the Hedgehog",true,25.00));
        ArrayList<DVD> DVDone = new ArrayList<DVD>();
        DVDone.add(new DVD("Furious 7",true,30.00));
        DVDone.add(new DVD("Beauty & the Beast",true,35.00));
        newOne = new Catelogue("June 2020","6-6-2020");
        for(CD x:CDone){
            newOne.addCD(x);
        }
        for(DVD w:DVDone){
            newOne.addCD(w);
        }
        System.out.println("Total Disk in the new Catelogue: "+newOne.getTotalDisk()+"\n");
        for(int i=0;i<newOne.getTotalDisk();i++){
            newOne.disk[i].displayInfo();
        }
        new gui();
    }
}