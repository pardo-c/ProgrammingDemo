import static org.junit.Assert.*;

/** Rental Agreement Test class contains 6 tests comparing the expected
 *  rental agreement values with each test case's generated rental
 *  agreement.
 *
 *  @author Cameron Pardo
 *
 */
public class RentalAgreementTest {
    @org.junit.Test
    public void Test6() {
        // initialize expected rental agreement
        RentalAgreement expectedRentalAgreement =
                new RentalAgreement(
                        "JAKR",
                        "Jackhammer",
                        "Ridgid",
                        4,
                        "7/2/20",
                        "7/6/20",
                        "$2.99",
                        1,
                        "$2.99",
                        "50%",
                        "$1.50",
                        "$1.49");

        // initialize checkout values
        Checkout test6 = new Checkout();
        test6.selectTool(Tool.JAKR);
        test6.setRentalDayCount(4);
        test6.setCheckoutDate(2020, 07, 02);
        test6.setDiscount(50);

        // compare rental agreement objects
        assertEquals(expectedRentalAgreement, test6.generateRentalAgreement());
    }

    @org.junit.Test
    public void Test5(){
        // initialize expected rental agreement
        RentalAgreement expectedRentalAgreement =
                new RentalAgreement(
                        "JAKR",
                        "Jackhammer",
                        "Ridgid",
                        9,
                        "7/2/15",
                        "7/11/15",
                        "$2.99",
                        5,
                        "$14.95",
                        "0%",
                        "$0.00",
                        "$14.95");

        // initialize checkout values
        Checkout test5 = new Checkout();
        test5.selectTool(Tool.JAKR);
        test5.setRentalDayCount(9);
        test5.setCheckoutDate(2015, 07, 02);
        test5.setDiscount(0);

        // compare rental agreement objects
        assertEquals(expectedRentalAgreement, test5.generateRentalAgreement());
    }

    @org.junit.Test
    public void Test4(){
        // initialize expected rental agreement
        RentalAgreement expectedRentalAgreement =
                new RentalAgreement(
                        "JAKD",
                        "Jackhammer",
                        "Ridgid",
                        6,
                        "9/3/15",
                        "9/9/15",
                        "$2.99",
                        3,
                        "$8.97",
                        "0%",
                        "$0.00",
                        "$8.97");

        // initialize checkout values
        Checkout test4 = new Checkout();
        test4.selectTool(Tool.JAKD);
        test4.setRentalDayCount(6);
        test4.setCheckoutDate(2015, 9, 03);
        test4.setDiscount(0);

        // compare rental agreement objects
        assertEquals(expectedRentalAgreement, test4.generateRentalAgreement());
    }

    @org.junit.Test
    public void Test3(){
        // initialize expected rental agreement
        RentalAgreement expectedRentalAgreement =
                new RentalAgreement(
                        "CHNS",
                        "Chainsaw",
                        "Stihl",
                        5,
                        "7/2/15",
                        "7/7/15",
                        "$1.49",
                        3,
                        "$4.47",
                        "25%",
                        "$1.12",
                        "$3.35");

        // initialize checkout values
        Checkout test3 = new Checkout();
        test3.selectTool(Tool.CHNS);
        test3.setRentalDayCount(5);
        test3.setCheckoutDate(2015, 7, 02);
        test3.setDiscount(25);

        // compare rental agreement objects
        assertEquals(expectedRentalAgreement, test3.generateRentalAgreement());
    }
    @org.junit.Test
    public void Test2(){
        // initialize expected rental agreement
        RentalAgreement expectedRentalAgreement =
                new RentalAgreement(
                        "LADW",
                        "Ladder",
                        "Werner",
                        3,
                        "7/2/20",
                        "7/5/20",
                        "$1.99",
                        2,
                        "$3.98",
                        "10%",
                        "$0.40",
                        "$3.58");

        // initialize checkout values
        Checkout test2 = new Checkout();
        test2.selectTool(Tool.LADW);
        test2.setRentalDayCount(3);
        test2.setCheckoutDate(2020, 7, 02);
        test2.setDiscount(10);

        // compare rental agreement objects
        assertEquals(expectedRentalAgreement, test2.generateRentalAgreement());
    }

    @org.junit.Test(expected=IllegalArgumentException.class)
    public void Test1(){
        // initialize checkout values
        Checkout test1 = new Checkout();
        test1.selectTool(Tool.JAKR);
        test1.setRentalDayCount(5);
        test1.setCheckoutDate(2015, 9, 03);
        test1.setDiscount(101);

        // test rental agreement expected values
        assertEquals("Exception", test1.generateRentalAgreement());
    }
}

