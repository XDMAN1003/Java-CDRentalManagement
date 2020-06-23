package com.company;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

abstract class CompactDisk {
    private String movieTitle;
    private boolean status;
    private double price;
    private LocalDate dateRent;
    private int rentalPeriod;
    private String description;
    public CompactDisk(String mTitle,boolean stat,double harga){
        this.movieTitle = mTitle;
        this.status = stat;
        this.price = harga;
    }
    public void rent(int year,int month,int day){
        dateRent = referDate(year,month,day);
        Period hari = Period.between(LocalDate.now(),dateRent);
        rentalPeriod = hari.getDays();
    }
    public void updateStatus(){
        if(this.status == true){
            this.status = false;
        }
        else{
            this.status = true;
        }
    }
    public boolean getStatus(){
        return status;
    }

    public String getMovieTitle() {
        return movieTitle;
    }
    abstract void displayInfo();
    public LocalDate referDate(int year,int month,int day){
        Month realMonth = Month.FEBRUARY;
        switch(month){
            case 1: realMonth = Month.JANUARY;
                break;
            case 2: realMonth = Month.FEBRUARY;
                break;
            case 3: realMonth = Month.MARCH;
                break;
            case 4: realMonth = Month.APRIL;
                break;
            case 5: realMonth = Month.MAY;
                break;
            case 6: realMonth = Month.JUNE;
                break;
            case 7: realMonth = Month.JULY;
                break;
            case 8: realMonth = Month.AUGUST;
                break;
            case 9: realMonth = Month.SEPTEMBER;
                break;
            case 10: realMonth = Month.OCTOBER;
                break;
            case 11: realMonth = Month.NOVEMBER;
                break;
            case 12: realMonth = Month.DECEMBER;
                break;
            default: System.out.println("Error data input");
        }
        LocalDate date = LocalDate.of(year,realMonth,day);
        return date;
    }
    public double getPrice() {
        return price;
    }
    abstract double getFinalPrice();

    public int getRentalPeriod() {
        return rentalPeriod;
    }
}

public class Catelogue{
    private String name;
    private String createdBy;
    private int totalDisk = 0;
    public CompactDisk[] disk = new CompactDisk[99];
    public Catelogue(String nama,String dateCipta){
        this.name = nama;
        this.createdBy = dateCipta;
    }
    public void addCD(CompactDisk x){
        disk[totalDisk] = x;
        totalDisk++;
    }

    public String getName() {
        return name;
    }

    public int getTotalDisk() {
        return totalDisk;
    }

    public String getCreatedBy() {
        return createdBy;
    }
}
interface Rentable{
    double cdRentRate = 0.15;
    double dvdRentRate = 0.20;
    double calcRentBill(int day);
}

class CD extends CompactDisk implements Rentable{
    private double mediaTax=0.05;
    private double rentPrice = 0;
    private double tPrice;
    private String id;
    static int kira = 1;
    public CD(String moTitle, boolean stats, double hargax){
        super(moTitle,stats,hargax);
        this.id = "CD-"+kira;

        kira++;
    }

    @Override
    public double calcRentBill(int day) {
        return day * cdRentRate;
    }

    @Override
    public void displayInfo() {
        System.out.println("-----"+id+"-----");
        System.out.println("Movie Title: "+super.getMovieTitle());
        System.out.printf("Tax : %.2f\n",mediaTax*100);
        System.out.printf("Price(Buy): %.2f\n",super.getPrice()*(1+mediaTax));
        System.out.printf("Rent Price/day: RM%.2f\n",cdRentRate);
        if(super.getStatus()){
            System.out.println("Rent : Available");

        }
        else{
            System.out.println("Rent: Unavailable");
            System.out.println("Rent Day: "+super.getRentalPeriod()+" day(s)");
            rentPrice = calcRentBill(super.getRentalPeriod());
            tPrice = (rentPrice)*(1+mediaTax);
            System.out.printf("Rent Price: RM%.2f\n",rentPrice);
            System.out.printf("Total Price : RM%.2f\n",rentPrice);
            System.out.printf("Total Price (Include 10%% tax): RM%.2f\n\n",tPrice);
        }
        System.out.print("\n");
    }

    public String getId() {
        return id;
    }
    public double getRentRate() {
        return cdRentRate;
    }


    @Override
    double getFinalPrice() {
        return super.getPrice() * (1 + mediaTax);
    }

    public double gettPrice() {
        return tPrice;
    }
}

class DVD extends CompactDisk implements Rentable {
    private double copyrightTax = 0.1;
    private double rentPrice = 0;
    private double tPrice;
    private String id;
    static int count;

    public DVD(String moTitle, boolean stats, double hargax) {
        super(moTitle, stats, hargax);
        count++;
        this.id = "DVD-"+(count);
        System.out.print(count);
    }

    @Override
    public double calcRentBill(int day) {
        return day * dvdRentRate;
    }

    @Override
    public void displayInfo() {
        System.out.println("-----" + id + "-----");
        System.out.println("Movie Title: " + super.getMovieTitle());
        System.out.printf("Tax : %.2f\n", copyrightTax * 100);
        System.out.printf("Price(Buy): %.2f\n", super.getPrice() * (1 + copyrightTax));
        System.out.printf("Rent Price/day: RM%.2f\n", dvdRentRate);
        if (super.getStatus()) {
            System.out.println("Rent : Available");

        } else {
            System.out.println("Rent: Unavailable");
            System.out.println("Rent Day: " + super.getRentalPeriod() + " day(s)");
            rentPrice = calcRentBill(super.getRentalPeriod());
            tPrice = rentPrice * (1 + copyrightTax);
            System.out.printf("Rent Price: RM%.2f\n", rentPrice);
            System.out.printf("Total Price (Include 10%% tax): RM%.2f", tPrice);
        }
        System.out.print("\n");
    }
    @Override
    double getFinalPrice() {
        return super.getPrice() * (1 + copyrightTax);
    }
    public double getRentRate() {
        return dvdRentRate;
    }

    public String getId() {
        return id;
    }
    public double gettPrice() {
        return tPrice;
    }
}


