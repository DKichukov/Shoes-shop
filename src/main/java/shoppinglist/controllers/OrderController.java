package shoppinglist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import shoppinglist.constants.Payment;
import shoppinglist.models.Footwear;
import shoppinglist.models.Order;
import shoppinglist.repositories.FootwearRepository;
import shoppinglist.repositories.OrderRepository;
import shoppinglist.services.OrderService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private FootwearRepository footwearRepository;

    @GetMapping("/")
    private String loadHomePage() {
        return "index";
    }

    @GetMapping("/orders")
    private String listAllOrders(Model model) {
        List<Order> listOrders = orderRepository.findAll();
        model.addAttribute("listOrders", listOrders);
        return "order/orders";
    }

    @GetMapping("/orders/new")
    private String createNewProduct(Order order, Model model) {
        List<Order> listOrder = orderRepository.findAll();
        List<Footwear> listFootwears = (List<Footwear>) footwearRepository.findAll();

        model.addAttribute("order", order);
        model.addAttribute("listPayments", Payment.values());
        model.addAttribute("listOrder", listOrder);
        model.addAttribute("listFootwears", listFootwears);

        return "order/order-form";
    }

    @PostMapping("/orders/submit")
    private ModelAndView saveProduct(@Valid Order order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("error");
            return new ModelAndView("/orders/new");
        } else {
            System.out.println("save");
            orderService.calculateTotalAmount(order);
            orderService.calculateDeliveryFee(order);
            orderRepository.save(order);
            return new ModelAndView("redirect:/orders/");
        }
    }
    @GetMapping("/orders/edit/{id}")
    private String editProduct(@PathVariable("id") Integer id, Model model) {
        Order order = orderRepository.findById(id).get();
        List<Order> listOrders = (List<Order>) orderRepository.findAll();
        List<Footwear> listFootwears = (List<Footwear>) footwearRepository.findAll();
        model.addAttribute("order", order);
        model.addAttribute("listOrders", listOrders);
        model.addAttribute("listFootwears", listFootwears);
        model.addAttribute("listPayments", Payment.values());
        return "order/order-form";

    }

    @GetMapping("/orders/delete/{id}")
    private ModelAndView deleteProduct(@PathVariable("id") Integer id, Model model) {
        footwearRepository.deleteById(id);
        return new ModelAndView("redirect:/order/orders");
    }

}
