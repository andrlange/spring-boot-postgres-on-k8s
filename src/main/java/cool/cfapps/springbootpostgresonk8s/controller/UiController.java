package cool.cfapps.springbootpostgresonk8s.controller;

import cool.cfapps.springbootpostgresonk8s.entity.Value;
import cool.cfapps.springbootpostgresonk8s.repository.ValueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
public class UiController {

    final private ValueRepository valueRepository;

    public UiController(ValueRepository valueRepository) {
        this.valueRepository = valueRepository;
    }

    @GetMapping
    public ModelAndView showValue() {
        List<Value> values = valueRepository.findAll();
        log.info("values:"+values);
        String description = values.isEmpty()? null : values.get(0).getDescription();
        return new ModelAndView("index", "description", description);
    }

    @PostMapping("save")
    public ModelAndView save(@RequestParam("description") String description,
                             RedirectAttributes redirect) {
        List<Value> values = valueRepository.findAll();
        if (!values.isEmpty()) {
            valueRepository.delete(values.get(0));
        }
        valueRepository.save(new Value(description));
        return new ModelAndView("redirect:/");
    }
}
