package com.company;

import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import static java.lang.System.exit;

public class DiskPage {
    private Font h1  = new Font(Font.SANS_SERIF,  Font.BOLD, 60); //Set the font family , font type and font size
    private Font h2  = new Font(Font.SANS_SERIF,  Font.PLAIN, 40); //Set the font family , font type and font size
    private Font p  = new Font(Font.SANS_SERIF,  Font.PLAIN, 20); //Set the font family , font type and font size
    public DiskPage(JFrame frame,Container cp,CompactDisk disk){
        cp.removeAll();
        frame.setSize(950,550);
        String title = "";
        String idd="";
        double rentRate = 0;


        if(disk instanceof CD){
            title = ((CD)disk).getId()+" "+ disk.getMovieTitle();
            idd = ((CD)disk).getId();
            rentRate = ((CD)disk).getRentRate();
        }
        else if(disk instanceof DVD){
            title = ((DVD)disk).getId()+" "+ disk.getMovieTitle();
            idd = ((DVD)disk).getId();
            rentRate = ((DVD)disk).getRentRate();
        }
        frame.setTitle(title);//Set the title of GUI
        JPanel Panel1 = new JPanel();
        Panel1.setLayout(null);
        Panel1.setBackground(new Color(53,54,58));
        cp.add(Panel1);

        //Insert the image into the GUI
        JLabel movieImg = new JLabel(new ImageIcon("C:\\Users\\xdman\\Desktop\\Sem 4\\OOP\\myProject\\"+disk.getMovieTitle().toLowerCase()+".jpg"));
        movieImg.setBounds(15,35,325,428); //Set the location (first 2) and the size of the picture
        Panel1.add(movieImg); //Add the image inside the Gui

        //Insert the title for the movie
        Vector<JLabel>Mtitle = new Vector<JLabel>();
        Mtitle.add(new JLabel(idd));
        Mtitle.get(0).setBounds(350,100,500,80); //Set the location (first 2) and the size of title
        Mtitle.get(0).setFont(h1); // Set the font to the font's setting
        Mtitle.get(0).setForeground(new Color(248,248,248)); //Set the font color to white

        Mtitle.add(new JLabel(disk.getMovieTitle()));
        Mtitle.get(1).setBounds(350,175,700,80); //Set the location (first 2) and the size of title
        Mtitle.get(1).setFont(h1); // Set the font to the font's setting
        Mtitle.get(1).setForeground(new Color(248,248,248)); //Set the font color to white

        Mtitle.add(new JLabel("Status: "));
        Mtitle.get(2).setBounds(350,250,500,50); //Set the location (first 2) and the size of title
        Mtitle.get(2).setFont(h2); // Set the font to the font's setting
        Mtitle.get(2).setForeground(new Color(248,248,248)); //Set the font color to white

        if(disk.getStatus()){
            Mtitle.add(new JLabel("Available"));
            Mtitle.get(3).setBounds(480,250,500,50); //Set the location (first 2) and the size of title
            Mtitle.get(3).setFont(h2); // Set the font to the font's setting
            Mtitle.get(3).setForeground(Color.GREEN); //Set the font color to white
        }
        else{
            Mtitle.add(new JLabel("Occupied"));
            Mtitle.get(3).setBounds(450,220,500,50); //Set the location (first 2) and the size of title
            Mtitle.get(3).setFont(h2); // Set the font to the font's setting
            Mtitle.get(3).setForeground(Color.RED); //Set the font color to white
        }

        Mtitle.add(new JLabel(String.format("Price(Buy): RM%.2f",disk.getPrice())));
        Mtitle.get(4).setBounds(400,320,500,30); //Set the location (first 2) and the size of title
        Mtitle.get(4).setFont(p); // Set the font to the font's setting
        Mtitle.get(4).setForeground(new Color(248,248,248)); //Set the font color to white

        Mtitle.add(new JLabel(String.format("Price(Rent): RM%.2f",rentRate)));
        Mtitle.get(5).setBounds(650,320,500,30); //Set the location (first 2) and the size of title
        Mtitle.get(5).setFont(p); // Set the font to the font's setting
        Mtitle.get(5).setForeground(new Color(248,248,248)); //Set the font color to white

        Mtitle.add(new JLabel("All price shown is exclude the tax (CD : 5% & DVD : 10%)"));
        Mtitle.get(6).setBounds(450,400,700,30); //Set the location (first 2) and the size of title
        Mtitle.get(6).setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 15)); // Set the font to the font's setting
        Mtitle.get(6).setForeground(new Color(248,248,248)); //Set the font color to white

        for(JLabel MM: Mtitle){
            Panel1.add(MM);
        }

        JButton buy = new JButton("BUY");
        buy.setBounds(400,350,200,50);
        buy.setBackground(Color.WHITE);
        buy.setBorder(BorderFactory.createEmptyBorder());
        buy.setForeground(new Color(53,54,58));
        Panel1.add(buy);

        JButton rent = new JButton("RENT");
        rent.setBounds(650,350,200,50);
        rent.setBackground(Color.WHITE);
        rent.setBorder(BorderFactory.createEmptyBorder());
        rent.setForeground(new Color(53,54,58));
        Panel1.add(rent);

        ActionListener buyME = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null,
                        "Did you sure to buy "+disk.getMovieTitle()+" for "+String.format("RM%.2f",disk.getFinalPrice())+" (Include Tax) ",
                        "Buy Disk", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null,"You buy the "+disk.getMovieTitle()+" for "+String.format("RM%.2f",disk.getFinalPrice()),
                            "Buy Successfully",JOptionPane.INFORMATION_MESSAGE);
                    exit(0);
                }
            }
        };

        ActionListener rentMe = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DatePicker(frame, cp, disk);
            }
        };

        buy.addActionListener(buyME);
        rent.addActionListener(rentMe);
        cp.validate();
        frame.setVisible(true);
    }
}
