package com.example.restdemo;

import com.example.restdemo.Dao.RestaurantRepository;
import com.example.restdemo.Entity.Restaurant;
import com.example.restdemo.model.Restaurent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @GetMapping(value="/restaurants", produces= MediaType.APPLICATION_XML_VALUE)
    public List<Restaurent> getAllRestaurants() {
        List<Restaurant> restaurant=restaurantRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
        List<Restaurent> res=new ArrayList<>();
        for(Restaurant r:restaurant){
            Restaurent a =new Restaurent();
            a.setAvgPrice(r.getAvgPrice());
            a.setName(r.getName());
            a.setLocation(r.getLocation());
            res.add(a);
        }
        return res;
    }

    @GetMapping(value="/restaurantByLoc",produces= MediaType.APPLICATION_XML_VALUE)
    public List<Restaurent> getAllRestaurantByDestination(@PathVariable String Location) {
        List<Restaurant> restaurant=restaurantRepository.findByLocation(Location);
        List<Restaurent> res=new ArrayList<>();
        for(Restaurant r:restaurant){
            Restaurent a =new Restaurent();
            a.setAvgPrice(r.getAvgPrice());
            a.setName(r.getName());
            a.setLocation(r.getLocation());
            res.add(a);
        }
        return res;
    }

    @PostMapping(value="/restaurant",consumes= MediaType.APPLICATION_XML_VALUE)
    public String createRestaurant(@RequestBody String requestBody) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Restaurent.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StringReader a = new StringReader(requestBody);
        Restaurent res =(Restaurent) (jaxbUnmarshaller).unmarshal(a);
        if(res!=null) {
            Restaurant restaurant=new Restaurant();
            restaurant.setAvgPrice(res.getAvgPrice());
            restaurant.setLocation(res.getLocation());
            restaurant.setMenuId(res.getMenuId());
            restaurant.setName(res.getName());
            restaurant.setRating(res.getRating());
            Restaurant restaurant2 = restaurantRepository.save(restaurant);
            restaurantRepository.save(restaurant2);
        }
        return "Restaurent Added";
    }

}
