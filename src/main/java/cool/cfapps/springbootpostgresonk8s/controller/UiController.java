package cool.cfapps.springbootpostgresonk8s.controller;

import cool.cfapps.springbootpostgresonk8s.entity.Value;
import cool.cfapps.springbootpostgresonk8s.entity.ViewEntity;
import cool.cfapps.springbootpostgresonk8s.repository.ValueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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
        String description = values.isEmpty()? null : values.get(values.size()-1).getDescription();

        StringJoiner joiner = new StringJoiner(", ");
        values.forEach(value -> joiner.add(value.getDescription()));
        ViewEntity viewEntity = new ViewEntity(description,joiner.toString());

        return new ModelAndView("index", "viewEntity", viewEntity);
    }

    @PostMapping("save")
    public ModelAndView save(@RequestParam("description") String description,
                             RedirectAttributes redirect) {
        List<Value> values = valueRepository.findAll();
        if (values.size()>4) {
            valueRepository.delete(values.get(0));
            log.info("delete value:"+values.get(0));
        }
        valueRepository.save(new Value(description));
        return new ModelAndView("redirect:/");
    }
}
