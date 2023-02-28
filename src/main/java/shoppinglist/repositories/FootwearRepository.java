package shoppinglist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import shoppinglist.models.Footwear;

import java.awt.print.Pageable;
import java.util.List;

public interface FootwearRepository extends PagingAndSortingRepository<Footwear, Integer> {

    @Query("SELECT f FROM Footwear f WHERE f.brand LIKE %?1% OR f.model LIKE %?1% OR f.size LIKE %?1% OR f.color LIKE %?1%")
    List<Footwear> searchALl(String keyword);

    //    public List<Footwear> searchAll(String brand, String model, int size, String color);

}
