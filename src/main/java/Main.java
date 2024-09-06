import java.util.List;

public class Main {
    public static void main(String[] args){

        OrderRepo orderRepo = new OrderMapRepo();
        ProductRepo productRepo = new ProductRepo();
        IdService idService = new IdService();
        OrderListRepo orderListRepo = new OrderListRepo();

        productRepo.addProduct(new Product("1", "Birne"));
        productRepo.addProduct(new Product("2", "Banane"));
        productRepo.addProduct(new Product("3", "Kiwi"));
        productRepo.addProduct(new Product("4", "Nektarine"));

        ShopService myShop = new ShopService(productRepo, orderRepo, idService);

        myShop.addOrder(List.of("1"));
        myShop.addOrder(List.of("1", "2", "3"));
        myShop.addOrder(List.of("2", "4"));

        //System.out.println("ProductRepo: " + productRepo);
        //System.out.println("All Orders: " + orderRepo);
        System.out.println(productRepo);



    }



}
