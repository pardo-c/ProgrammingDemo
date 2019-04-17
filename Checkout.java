import java.time.*;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.math.*;
import java.util.Locale;

/** Class Checkout calculates all the information needed to output accurate Rental Agreements for clients.
 *
 *  @author Cameron Pardo
 *
 */

public class Checkout
{
    private Tool tool;
    private int rentalDayCount;
    private LocalDate checkoutDate;
    private RentalAgreement rentalAgreement;
    private double discount;


    public LocalDate calculateDueDate(){
        // due date is calculated from checkout date and rental days.
        LocalDate dueDate = checkoutDate.plusDays(rentalDayCount);
        return dueDate;
    }

    public String changeDateFormat(LocalDate date){
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("M/d/uu");
        String formattedDate = date.format(formatDate);
        return formattedDate;
    }

    public int numberOfDaysCharged(){
        int numberOfDaysCharged = 0;

        /* Count of chargeable days starts from day after checkout through and
           including due date. Exclude “no charge” days as specified by the tool type. */
        LocalDate date = checkoutDate.plusDays(1);

        while(!date.isEqual(calculateDueDate().plusDays(1))){

            // case: tool has no holiday charge and weekend charge
            if(tool.getHolidayCharge() == "No" && tool.getWeekendCharge() == "No"){
                // check for the 4th of July
                if(date.getMonthValue() == 7 && date.getDayOfMonth() == 4){
                    // does the 4th fall on a Saturday or Sunday?
                    if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY){
                        // if yes, account for weekday to be subtracted from number of charge days
                        numberOfDaysCharged--;
                    }
                }

                // do not charge if it is Labor Day
                else if(date.getMonthValue() == 9 && date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() < 8) {
                    /* There are no operations or statements within this else if block. If the date is Labor
                       day, the client should not be charged. To avoid charging, we avoid incrementing the
                       numberOFDayCharged counter to accurately calculate the number of charge days. */
                }

                // do not charge if it is a weekend
                else if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY){
                    /* There are no operations or statements within this else if block. If the day is a Saturday or
                       Sunday, the client should not be charged. To avoid charging, we avoid incrementing the
                       numberOFDayCharged counter to accurately calculate the number of charge days. */
                }

                // if not a weekend or holiday, increment number of days charged by default
                else{
                    numberOfDaysCharged++;
                }
            }

            // tool has no weekend charge, but has holiday charge
            else if(tool.getWeekendCharge() == "No" && tool.getHolidayCharge() == "Yes")
            {
                // do not charge on weekends
                if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    /* There are no operations or statements within this if block. If the day is a Saturday or
                       Sunday, the client should not be charged. To avoid charging, we avoid incrementing the
                       numberOFDayCharged counter to accurately calculate the number of charge days. */
                }

                // if not a weekend, increment number of days charged by default
                else{
                    numberOfDaysCharged++;
                }
            }

            // tool has no holiday charge, but has weekend charge
            else if(tool.getHolidayCharge() == "No" && tool.getWeekendCharge() == "Yes"){

                // check for July 4th
                if(date.getMonthValue() == 7 && date.getDayOfMonth() == 4){
                    // is it a Saturday?
                    if(date.getDayOfWeek() == DayOfWeek.SATURDAY){
                        // if checkoutDate Friday, they miss opportunity to get a free day
                        if(checkoutDate.isEqual(date.minusDays(1))){
                            numberOfDaysCharged++;
                        }
                    }
                    // is it a Sunday?
                    else if(date.getDayOfWeek() == DayOfWeek.SUNDAY)
                    {
                        // if dueDate is Sunday July, 4th, they miss opportunity to get free day
                        if(calculateDueDate().isEqual(date)){
                            numberOfDaysCharged++;
                        }
                    }
                }
                // check for Labor Day
                else if(date.getMonthValue() == 9 && date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() < 8) {
                    /* There are no operations or statements within this else if block. If the date is Labor
                       day, the client should not be charged. To avoid charging, we avoid incrementing the
                       numberOFDayCharged counter to accurately calculate the number of charge days. */
                }

                // increment number of days charged by default
                else{
                    numberOfDaysCharged++;
                }
            }
            // increment date counter
            date = date.plusDays(1);
        }
        return numberOfDaysCharged;
    }


    public String formatDiscount(double discount){
        double discountAsDecimal = discount/100;
        NumberFormat defaultFormat = NumberFormat.getPercentInstance();
        defaultFormat.setMinimumFractionDigits(0);
        return defaultFormat.format(discountAsDecimal);
    }

    public BigDecimal calculateFinalCharge(){
        BigDecimal finalCharge = getPrediscountAmount().subtract(getDiscountAmount());
        return finalCharge.setScale(2, RoundingMode.HALF_UP);
    }

    public String formatCurrency(BigDecimal dollarAmount){
        Locale currentLocale = Locale.getDefault();
        NumberFormat amount = NumberFormat.getCurrencyInstance(currentLocale);
        amount.setMaximumFractionDigits(2);
        return amount.format(dollarAmount);
    }

    // generate rental agreement
    public RentalAgreement generateRentalAgreement(){
        RentalAgreement newRentalAgreement =
                new RentalAgreement(
                        tool.getToolCode(),
                        tool.getToolType(),
                        tool.getBrand(),
                        getRentalDayCount(),
                        changeDateFormat(getCheckoutDate()),
                        changeDateFormat(calculateDueDate()),
                        formatCurrency(tool.getRentalCharge()),
                        numberOfDaysCharged(),
                        formatCurrency(getPrediscountAmount()),
                        formatDiscount(getDiscount()),
                        formatCurrency(getDiscountAmount()),
                        formatCurrency(calculateFinalCharge()));
        rentalAgreement = newRentalAgreement;

        return rentalAgreement;
    }

    public void displayCheckoutTermsAndRentalAgreement(){
        rentalAgreement.displayCheckoutTerms();
        System.out.println();
        rentalAgreement.displayRentalAgreement();
    }

    // the following method sets the tool to be rented out
    public void selectTool(Tool identifier){
        if(identifier == Tool.LADW) {
            tool = Tool.setTool(Tool.LADW);
        }
        else if(identifier == Tool.CHNS) {
            tool = Tool.setTool(Tool.CHNS);
        }
        else if(identifier == Tool.JAKD) {
            tool = Tool.setTool(Tool.JAKD);
        }
        else if(identifier == Tool.JAKR) {
            tool = Tool.setTool(Tool.JAKR);
        }
        else {
            throw new IllegalArgumentException(identifier + " not found. Please select the tool to be rented out by using the tool's unique code");
        }
    }

    // the following method sets the discount
    public void setDiscount(double discount){
        if(discount > 100 || discount < 0)
            throw new IllegalArgumentException("Discounts cannot exceed 100% or be less than 0. Please adjust discount value");
        this.discount = discount;
    }

    // the following method sets the number of days a tool will be rented
    public void setRentalDayCount(int days){
        if(days < 1)
            throw new IllegalArgumentException("Tools must be rented out for 1 or more days. Please increase your day count.");
        rentalDayCount = days;
    }

    // the following method sets the date a tool is checkedout
    public void setCheckoutDate(int year, int month, int day){
        LocalDate dateCheckedout = LocalDate.of(year, month, day);
        checkoutDate = dateCheckedout;
    }

    // the following getter method retrieves the current checkout date
    public LocalDate getCheckoutDate(){
        return checkoutDate;
    }

    // The following getter methods retrieve tool attributes

    public String getToolCode(){
        return tool.getToolCode();
    }
    public BigDecimal getToolRentalCharge(){
        return tool.getRentalCharge();
    }


    // the following getter method retreives the current set discount
    public double getDiscount(){
        return discount;
    }

    // the following getter method retrieves the number of days a tool is to be rented
    public int getRentalDayCount(){
        return rentalDayCount;
    }

    // The following getter methods deal with charges

    public BigDecimal getDiscountAmount(){
        BigDecimal discount = new BigDecimal(getDiscount());
        BigDecimal onehundred = new BigDecimal(100);
        discount = discount.divide(onehundred);
        BigDecimal discountAmount = getPrediscountAmount().multiply(discount);
        return discountAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getPrediscountAmount(){
        BigDecimal numberOfDaysCharged = new BigDecimal(numberOfDaysCharged());
        BigDecimal prediscount = tool.getRentalCharge().multiply(numberOfDaysCharged);
        return prediscount.setScale(2, RoundingMode.HALF_UP);
    }
}
