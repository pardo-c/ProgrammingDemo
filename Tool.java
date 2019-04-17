import java.math.*;

/** Enumeration class Tools - A class in which the tools rented out by Cardinal's
 * "Tool Depot" store are listed with all of their attributes. The tools that can
 *  be rented out by are ladders, chainsaws, and jackhammers.
 *
 * @author Cameron Pardo
 *
 */

public enum Tool
{
    LADW ("Ladder", "LADW", "Werner", 1.99, "Yes", "Yes","No"),
    CHNS ("Chainsaw", "CHNS", "Stihl", 1.49, "Yes", "No", "Yes"),
    JAKR ("Jackhammer", "JAKR", "Ridgid", 2.99, "Yes", "No", "No"),
    JAKD ("Jackhammer", "JAKD","Ridgid", 2.99, "Yes", "No", "No");

    private final String toolType;            // type of tool
    private final String toolCode;            // unique tool code
    private final String brand;               // tool's brand name
    private final double rentalCharge;        // tool's rentalCharge
    private final String weekdayCharge;       // tool's weekday charge
    private final String weekendCharge;       // tool's weekend charge
    private final String holidayCharge;       // tool's holiday charge

    Tool(String toolType, String toolCode, String brand, double rentalCharge, String weekdayCharge, String weekendCharge, String holidayCharge){
        this.toolType = toolType;
        this.toolCode = toolCode;
        this.brand = brand;
        this.rentalCharge = rentalCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public String getToolType(){
        return toolType;
    }

    public String getToolCode(){
        return toolCode;
    }

    public String getBrand(){
        return brand;
    }

    public BigDecimal getRentalCharge(){
        BigDecimal charge = new BigDecimal(rentalCharge);
        return charge;
    }

    public String getWeekdayCharge(){
        return weekdayCharge; }

    public String getWeekendCharge(){
        return weekendCharge; }

    public String getHolidayCharge() {
        return holidayCharge; }

    public static Tool setTool(Tool identifier){
        return identifier;
    }
}
