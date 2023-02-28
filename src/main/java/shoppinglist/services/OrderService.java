package shoppinglist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppinglist.models.Footwear;
import shoppinglist.models.Order;
import shoppinglist.repositories.FootwearRepository;
import shoppinglist.repositories.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void calculateTotalAmount(Order order){
        List<Footwear> avaibleFootwears = order.getFootwears();
        double total =0;
        for (Footwear foot :avaibleFootwears ) {
          total += foot.getPrice();
        }
        order.setTotal(total);
    }
    public void calculateDeliveryFee(Order order){

        if(order.getCity().toUpperCase().equals("SOFIA")){
            order.setFee(0);
        }else if(order.getCity().toUpperCase().equals("VRATSA") || order.getCity().toUpperCase().equals("PERNIK")){
            order.setFee(5);
        }else{
            order.setFee(10);
        }
    }
    }
