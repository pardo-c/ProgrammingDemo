/** Rental Agreement class is a receipt-like object in which both employees and clients
 *  can refer to for transaction information.
 *
 *  @author Cameron Pardo
 *
 */

public class RentalAgreement
{
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDays;
    private String checkoutDate;
    private String dueDate;
    private String dailyRentalCharge;
    private int chargeDays;
    private String prediscountCharge;
    private String discountPercent;
    private String discountAmount;
    private String finalCharge;

    public RentalAgreement(String toolCode,
                           String toolType,
                           String toolBrand,
                           int rentalDays,
                           String checkoutDate,
                           String dueDate,
                           String dailyRentalCharge,
                           int chargeDays,
                           String prediscountCharge,
                           String discountPercent,
                           String discountAmount,
                           String finalCharge)
    {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.prediscountCharge = prediscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }
    public void displayRentalAgreement(){
        System.out.printf("%-10s %n", "Rental Agreement\nexpected values");
        System.out.println("Due date " +dueDate);
        System.out.println("Daily charge " +dailyRentalCharge);
        System.out.println("Charge days " +chargeDays);
        System.out.println("Pre-discount charge " +prediscountCharge);
        System.out.println("Discount% " +discountPercent);
        System.out.println("Discount amount" +discountAmount);
        System.out.println("Final charge " +finalCharge);
    }
    public void displayCheckoutTerms(){
        System.out.printf("%-10s %n", "Checkout Terms");
        System.out.println("Tool code " +toolCode);
        System.out.println("Checkout date " +checkoutDate);
        System.out.println("Rental days " +rentalDays);
        System.out.println("Discount " +discountPercent);
    }

    // create equals method to use in replace of junit assertEquals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        RentalAgreement compareRentalAgreement = (RentalAgreement) obj;

        return (this.toolCode.equals( compareRentalAgreement.toolCode) &&
                this.toolType.equals( compareRentalAgreement.toolType) &&
                this.toolBrand.equals( compareRentalAgreement.toolBrand) &&
                this.rentalDays == compareRentalAgreement.rentalDays &&
                this.checkoutDate.equals( compareRentalAgreement.checkoutDate) &&
                this.dueDate.equals( compareRentalAgreement.dueDate) &&
                this.dailyRentalCharge.equals( compareRentalAgreement.dailyRentalCharge) &&
                this.chargeDays == compareRentalAgreement.chargeDays &&
                this.prediscountCharge.equals( compareRentalAgreement.prediscountCharge) &&
                this.discountPercent.equals( compareRentalAgreement.discountPercent) &&
                this.discountAmount.equals( compareRentalAgreement.discountAmount) &&
                this.finalCharge.equals( compareRentalAgreement.finalCharge));
    }
}
