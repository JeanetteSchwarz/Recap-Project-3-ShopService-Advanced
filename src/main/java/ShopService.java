import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(productId));
            products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING, ZonedDateTime.now());

        return orderRepo.addOrder(newOrder);
    }


        public List<Order> getOrderByStatus(OrderStatus orderStatus){
        return orderRepo.getOrders().stream()
                .filter(order -> order.orderStatus() == orderStatus)
                .toList();

        }
        public void updateOrder(String orderId, OrderStatus orderStatus) {
            Order toUpdate = Optional.ofNullable(orderRepo.getOrderById(orderId)).orElseThrow(() -> new OrderNotFoundException(orderId));
            orderRepo.removeOrder(orderId);
            orderRepo.addOrder(toUpdate.withOrderStatus(orderStatus));
    }

}
