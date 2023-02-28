package shoppinglist.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import shoppinglist.models.Footwear;
import shoppinglist.models.Order;
import shoppinglist.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    private Order order;
    @InjectMocks
    private Order order1;

    @InjectMocks
    private Footwear footwear;
    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;

    @Test
    public void testCalculateDeliveryFee() {
        when(order.getCity()).thenReturn("SOFIA");
        orderService.calculateDeliveryFee(order);
        verify(order, times(1)).setFee(0);
    }

    @Test
    public void testCalculateDeliveryIsFive() {
        when(order.getCity()).thenReturn("PERNIK");
        orderService.calculateDeliveryFee(order);
        verify(order, times(1)).setFee(5);
    }
    @Test
    public void testCalculateDeliveryIsFiveCase2() {
        when(order.getCity()).thenReturn("VRATSA");
        orderService.calculateDeliveryFee(order);
        verify(order, times(1)).setFee(5);
    }

    @Test
    public void testCalculateDeliveryIsTen() {
        when(order.getCity()).thenReturn("PRENIK");
        orderService.calculateDeliveryFee(order);
        verify(order, times(1)).setFee(10);
    }

    @Test
    public void testCalculateTotalAmountIsEqualTo80() {
        List<Footwear> footwears = new ArrayList<>();
        footwears.add(new Footwear("Shoe", 50.0));
        footwears.add(new Footwear("Sandal", 30.0));
        order1.setFootwears(footwears);

        orderService.calculateTotalAmount(order1);

        assertEquals(80.0, order1.getTotal(), 0.01);
    }

}