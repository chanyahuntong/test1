import java.util.ArrayList;

public class ShoppingCartManualTest {

    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return 0.0");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) {
            System.out.println("PASSED: Empty cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2));     // 50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1));      // 15
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) {
            System.out.println("PASSED: Simple cart total is correct (65.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + total3);
            failedCount++;
        }

        // Test 4: สิ้นค้าติดลบ หรือ ราคาติดลบ
        ArrayList<CartItem> invalidCart = new ArrayList<>();
        invalidCart.add(new CartItem("NORMAL", "Candy", -15.0, 2)); // -30
        invalidCart.add(new CartItem("NORMAL", "Snack", 30.0, -5));    // -150
        double total4 = ShoppingCartCalculator.calculateTotalPrice(invalidCart);
        if (total4 == 0.0) {
            System.out.println("PASSED: Negative prices or quantities are ignored .");
            passedCount++;
        } else {
            System.out.println("FAILED: Expected 0.0 but got " + total4);
            failedCount++;
        }

        // Test 5: ใช้คูปองส่วนลด ซื้อ 1 แถม 1
        ArrayList<CartItem> BOGOCart = new ArrayList<>();
        BOGOCart.add(new CartItem("BOGO", "Colored pencils", 55.0, 4)); // 220/2 = 110
        BOGOCart.add(new CartItem("BOGO", "Pencils", 10.0, 2));        // 20/2 = 10
        double total5 = ShoppingCartCalculator.calculateTotalPrice(BOGOCart);
        if (total5 == 120.0) {
            System.out.println("PASSED: BOGO cart total is correct (120.0).");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO cart total expected 120.0 but got " + total5);
            failedCount++;
        }

         // Test 6: ใช้คูปองส่วนลด BULK ซื้อ 6 ชิ้นขึ้นไป ลด10%
        ArrayList<CartItem> BULKCart = new ArrayList<>();
        BULKCart.add(new CartItem("BULK", "Book", 190.0, 7)); // 1330-10% = 1197
        double total6 = ShoppingCartCalculator.calculateTotalPrice(BULKCart);
        if (total6 == 1197.0) {
            System.out.println("PASSED: BULK cart total is correct (1197.0).");
            passedCount++;
        } else {
            System.out.println("FAILED: BULK cart total expected 1197.0 but got " + total6);
            failedCount++;
        }

        // Test 7: การใช้ร่วมกันของส่วนลด
        ArrayList<CartItem> MixedCart = new ArrayList<>();
        MixedCart.add(new CartItem("BOGO", "Plate", 250.0, 4)); // 1000/2 = 500
        MixedCart.add(new CartItem("BULK", "Glass", 120.0, 9)); // 1080-10% = 972
        double total7 = ShoppingCartCalculator.calculateTotalPrice(MixedCart);
        if (total7 == 1472.0) {
            System.out.println("PASSED: Mixed cart total is correct (1472.0).");
            passedCount++;
        } else {
            System.out.println("FAILED: Mixed cart total expected 1472.0 but got " + total7);
            failedCount++;
        }

        // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}