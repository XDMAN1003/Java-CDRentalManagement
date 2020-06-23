package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import static java.lang.System.exit;

public class DatePicker {
    private Font h1  = new Font(Font.SANS_SERIF,  Font.BOLD, 60); //Set the font family , font type and font size
    private Font h2  = new Font(Font.SANS_SERIF,  Font.BOLD, 40); //Set the font family , font type and font size
    private Font p  = new Font(Font.SANS_SERIF,  Font.PLAIN, 20); //Set the font family , font type and font size
    public DatePicker(JFrame frame,Container cp,CompactDisk disk){
        cp.removeAll();
        frame.setSize(600,300);
        frame.setTitle("Rent Date and Price");//Set the title of GUI
        JPanel Panel1 = new JPanel();
        Panel1.setLayout(null);
        Panel1.setBackground(new Color(53,54,58));
        cp.add(Panel1);

        //Set the title for the GUI
        JLabel title = new JLabel("Rent Service");
        title.setBounds(30,10,500,65);
        title.setFont(h2);
        title.setForeground(new Color(248,248,248));
        Panel1.add(title);

        //Set the Movie title for the GUI
        JLabel movietitle = new JLabel(disk.getMovieTitle());
        movietitle.setBounds(30,70,500,65);
        movietitle.setFont(h2);
        movietitle.setForeground(new Color(248,248,248));
        Panel1.add(movietitle);

        //Set the label for the day
        JLabel days = new JLabel("DAY: ");
        days.setBounds(40,152,100,45);
        days.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        days.setForeground(new Color(248,248,248));
        Panel1.add(days);

        //Set the list of the days
        DefaultListModel<Integer> day = new DefaultListModel<Integer>();
        for(int x=1;x<32;x++){
            day.addElement(((Integer)x));
        }
        JList<Integer>dayList = new JList<Integer>(day);
        JScrollPane dayScroll = new JScrollPane(dayList);
        dayList.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        dayScroll.setBounds(100,155,45,38);
        dayScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Panel1.add(dayScroll);

        //Set the label for the day
        JLabel months = new JLabel("MONTH: ");
        months.setBounds(170,152,100,45);
        months.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        months.setForeground(new Color(248,248,248));
        Panel1.add(months);

        //Set the list of the months
        DefaultListModel<Integer> month = new DefaultListModel<Integer>();
        for(int x=1;x<13;x++){
            month.addElement(((Integer)x));
        }
        JList<Integer>monthList = new JList<Integer>(month);
        JScrollPane monthScroll = new JScrollPane(monthList);
        monthList.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        monthScroll.setBounds(260,155,45,38);
        monthScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Panel1.add(monthScroll);

        //Set the label for the day
        JLabel years = new JLabel("year: ");
        years.setBounds(330,152,100,45);
        years.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        years.setForeground(new Color(248,248,248));
        Panel1.add(years);

        //Set the list of the years
        DefaultListModel<Integer> year = new DefaultListModel<Integer>();
        for(int x=2020;x<2026;x++){
            year.addElement(((Integer)x));
        }
        JList<Integer>yearList = new JList<Integer>(year);
        JScrollPane yearScroll = new JScrollPane(yearList);
        yearList.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        yearScroll.setBounds(390,155,70,38);
        yearScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Panel1.add(yearScroll);

        JButton deal = new JButton("OK");
        deal.setBounds(500,155,50,38);
        deal.setBackground(Color.WHITE);
        deal.setBorder(BorderFactory.createEmptyBorder());
        deal.setForeground(new Color(53,54,58));
        Panel1.add(deal);

        ActionListener buyME = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int hari =25,bulan=2,tahun=2020;
                if (dayList.getSelectedIndex() != -1) {
                    hari = dayList.getSelectedValue();
                }
                if (monthList.getSelectedIndex() != -1) {
                    bulan = monthList.getSelectedValue();
                }
                if (yearList.getSelectedIndex() != -1) {
                    tahun = yearList.getSelectedValue();
                }
                disk.rent(tahun,bulan,hari);
                disk.updateStatus();
                disk.displayInfo();
                double harga = 0;

                if(disk instanceof CD){
                    harga = ((CD) disk).gettPrice();
                }
                else if(disk instanceof DVD){
                    harga = ((DVD) disk).gettPrice();
                }

                int reply = JOptionPane.showConfirmDialog(null,
                        "Did you sure to rent "+disk.getMovieTitle()+" at "+String.format("RM%.2f",harga)+" (Include Tax) for "+disk.getRentalPeriod()+"day(s)",
                        "Rent Disk", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null,"You rent the "+disk.getMovieTitle()+" at "+String.format("RM%.2f",harga)+" (Include Tax) for "+disk.getRentalPeriod()+"day(s)",
                            "Buy Successfully",JOptionPane.INFORMATION_MESSAGE);
                    exit(0);
                }
            }
        };
        deal.addActionListener(buyME);
        cp.validate();
        frame.setVisible(true);
    }
}
