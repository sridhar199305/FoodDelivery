
package com.example.restdemo;
import com.example.restdemo.Dao.MenuItemRespository;
import com.example.restdemo.Dao.OrderRepository;
import com.example.restdemo.Dao.RestaurantRepository;
import com.example.restdemo.Entity.MenuItem;
import com.example.restdemo.Entity.Orders;
import com.example.restdemo.Entity.Restaurant;
import com.example.restdemo.model.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuItemRespository menuItemRespository;

    @PostMapping(value="/placeOrder",consumes= MediaType.APPLICATION_XML_VALUE)
    public String placeOrder(@PathVariable String restaurentName,String Menu) {
        Orders order=new Orders();
        if(restaurentName !=null && Menu !=null){
            Restaurant restaurant=restaurantRepository.findByName(restaurentName);
            List<MenuItem> menuItem =menuItemRespository.findByMenus(Menu);
            float totalprice=0;
            if(menuItem!=null){
                for(MenuItem m:menuItem){
                    totalprice+=m.getPrice();
                }
            }
            order.setCost(totalprice);
            order.setRestaurent_id((restaurant.getId().toString()));
        }
       Orders order1=orderRepository.save(order);
        if(order1!=null){
            return "order succesfully placed";
        }
        return "Order not placed";
    }


}


