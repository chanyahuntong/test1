import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * เขียน Javadoc ที่นี่เพื่ออธิบายกฎการทำงานและกรณีพิเศษ:
     * - จะทำอย่างไรถ้า items เป็น null หรือ empty?
     * - จะทำอย่างไรถ้า CartItem มี price หรือ quantity ติดลบ?
     * - กฎส่วนลด BOGO (ซื้อ 1 แถม 1)
     * - กฎส่วนลด BULK (ซื้อ >= 6 ชิ้น ลด 10%)
     * @param Arraylist<Cartitem>
     * @return
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {

        if (items == null || items.isEmpty() || items.size() < 0 ){
            return 0.0;    
        }
        
       double total = 0.0 ;

       for (CartItem item : items) {
        String sku = item.sku() ;
        double price = item.price() ;
        Integer quantity = item.quantity() ;

        if (quantity <= 0 || price <= 0) {
            continue;
        }
        if (sku.equals("NORMAL")) {
            total = total + (price * quantity) ;
        }
        if (sku.equals("BOGO")) {
            quantity = quantity - (quantity / 2 )  ;
            total = total + (price * quantity) ;
        }
        if (sku.equals("BULK")) {
             if (item.quantity() >= 6) {
                total = total + ( quantity * price * 0.9 ) ;
            } else {
                total = total + (price * quantity) ;
            }
        } 
    }

       return total ;
    }
}
