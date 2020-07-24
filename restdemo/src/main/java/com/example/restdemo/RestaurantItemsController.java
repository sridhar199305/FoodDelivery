
package com.example.restdemo;

import com.example.restdemo.Dao.MenuItemRespository;
import com.example.restdemo.Dao.RestaurantRepository;
import com.example.restdemo.Entity.MenuItem;
import com.example.restdemo.Entity.Restaurant;
import com.example.restdemo.model.menu;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.Optional;

@RestController
public class RestaurantItemsController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRespository menuItemRespository;


    @PostMapping(value="/AddMenu" , consumes= MediaType.APPLICATION_XML_VALUE)
    public String createMenu(@RequestBody String requestBody) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(menu.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StringReader a = new StringReader(requestBody);
        menu que= (menu) (jaxbUnmarshaller).unmarshal(a);
        if(que!=null) {
            MenuItem menus= new MenuItem();
            menus.setMenus(que.getMenus());
            menus.setPrice(que.getPrice());
             menuItemRespository.save(menus);
        }
        return "Menu Added";
    }

    @GetMapping(value="/restaurantMenuByName")
    public Optional<MenuItem> getRestaurantItemsByName(@PathVariable String ResName) {

        Restaurant restaurant=restaurantRepository.findByName(ResName);
        Optional<MenuItem> res = null;
        if(restaurant!=null){
            res=menuItemRespository.findById(restaurant.getMenuId());
        }
        return res;
    }
}

