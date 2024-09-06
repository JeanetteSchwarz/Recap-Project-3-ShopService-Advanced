import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor

public class ShopService {

    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();
    private IdService idService = new IdService();

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo, IdService idService) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.idService = idService;
    }

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(productId));
            products.add(productToOrder);
        }

        Order newOrder;
        newOrder = new Order(IdService.generateID().toString(), products, OrderStatus.PROCESSING, ZonedDateTime.now());

        return orderRepo.addOrder(newOrder);
        }

        public List<Order> getOrderByStatus(OrderStatus orderStatus){
        return orderRepo.getOrders().stream()
                .filter(order -> order.orderStatus() == orderStatus)
                .toList();
        }

        public Order updateOrder(String orderId, OrderStatus newStatus){
            if (orderRepo.getOrderById(orderId) == null){
                throw new OrderNotFoundException(orderId);
            }
            return orderRepo.getOrderById(orderId).withOrderStatus(newStatus);
        }






}
