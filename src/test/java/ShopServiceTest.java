import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")));
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        assertNull(actual);
    }

    @Test
    void getOrderByStatusTest(){
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");
        shopService.addOrder(productsIds);
        //WHEN
        //THEN
        assertEquals(shopService.getOrderByStatus(OrderStatus.PROCESSING).size(),1);
    }

    @Test
    void updateOrderTest_setStatusToINDELIVERY(){
        //GIVEN
        ShopService shopService = new ShopService();
        Order temp = shopService.addOrder(List.of("1"));
        //WHEN
        Order tempUpdated = shopService.updateOrder(temp.id(), OrderStatus.IN_DELIVERY);
        //THEN
        assertSame(tempUpdated.orderStatus(), OrderStatus.IN_DELIVERY);

    }

}
