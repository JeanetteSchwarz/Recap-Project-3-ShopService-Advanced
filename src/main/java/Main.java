import java.util.List;

public class Main {
    public static void main(String[] args){

        OrderRepo orderRepo = new OrderMapRepo();
        ProductRepo myproductRepo = new ProductRepo();
        IdService idService = new IdService();
        OrderListRepo orderListRepo = new OrderListRepo();

        myproductRepo.addProduct(new Product("2", "Birne"));
        myproductRepo.addProduct(new Product("3", "Banane"));
        myproductRepo.addProduct(new Product("4", "Kiwi"));
        myproductRepo.addProduct(new Product("5", "Nektarine"));

        ShopService myShop = new ShopService(myproductRepo, orderRepo, idService);

        myShop.addOrder(List.of("1"));
        myShop.addOrder(List.of("1", "2", "3"));
        myShop.addOrder(List.of("2", "4"));

        //System.out.println("ProductRepo: " + productRepo);
        //System.out.println("All Orders: " + orderRepo);
        System.out.println(myproductRepo.getProducts());
        System.out.println(orderRepo.getOrders());



    }



}
