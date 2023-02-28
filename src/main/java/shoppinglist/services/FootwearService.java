package shoppinglist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppinglist.models.Footwear;
import shoppinglist.repositories.FootwearRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FootwearService {

    @Autowired
    private FootwearRepository footwearRepository;

    public List<Footwear> findAll(String keyword) {
        List<Footwear> footwearList = new ArrayList<>();
        if (keyword != null) {
            footwearList = footwearRepository.searchALl(keyword);
        } else {
            footwearList = (List<Footwear>) footwearRepository.findAll();
        }
        return footwearList;
    }
}
