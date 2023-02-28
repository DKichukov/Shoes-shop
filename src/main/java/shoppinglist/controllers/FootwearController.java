package shoppinglist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import shoppinglist.models.Footwear;
import shoppinglist.repositories.FootwearRepository;
import shoppinglist.services.FootwearService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class FootwearController {
    @Autowired
    private FootwearRepository footwearRepository;

    @Autowired
    private FootwearService footwearService;

@GetMapping("/footwears")
private String listAllFootwears(Model model, @Param("keyword")String keyword) {
    List<Footwear> listFootwears = footwearService.findAll(keyword);
    model.addAttribute("listFootwears", listFootwears);
    model.addAttribute("keyword",keyword);
    return "footwear/footwears";
}

    @GetMapping("/footwears/new")
    private String createNewProduct(Footwear footwear, Model model) {
        List<Footwear> listFootwears = (List<Footwear>) footwearRepository.findAll();
        model.addAttribute("footwear", footwear);
        model.addAttribute("listFootwears", listFootwears);
        return "footwear/footwear-form";
    }

    @PostMapping("/footwears/submit")
    private ModelAndView saveProduct(@Valid Footwear footwear, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("error");
            return new ModelAndView("footwear/footwears/new");
        } else {
            System.out.println("save");
            footwearRepository.save(footwear);
            return new ModelAndView("redirect:/footwears/");
        }
    }

    @GetMapping("/footwears/edit/{id}")
    private String editProduct(@PathVariable("id") Integer id, Model model) {
        Footwear footwear = footwearRepository.findById(id).get();
        List<Footwear> listFootwears = (List<Footwear>) footwearRepository.findAll();
        model.addAttribute("footwear", footwear);
        model.addAttribute("listFootwears", listFootwears);
        return "footwear/footwear-form";

    }

    @GetMapping("/footwears/delete/{id}")
    private ModelAndView deleteProduct(@PathVariable("id") Integer id, Model model) {
        footwearRepository.deleteById(id);
        return new ModelAndView("redirect:/footwear/footwears");
    }

}
