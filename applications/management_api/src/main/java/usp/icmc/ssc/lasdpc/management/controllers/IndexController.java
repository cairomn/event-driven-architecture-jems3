package usp.icmc.ssc.lasdpc.management.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class IndexController extends AbstractController {

    @GetMapping
    public String get() {
        return "Conexão funcionando.";
    }
}
