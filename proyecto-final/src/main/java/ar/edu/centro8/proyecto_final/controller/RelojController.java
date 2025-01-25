package ar.edu.centro8.proyecto_final.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class RelojController {
    @GetMapping("/reloj")
public String mostrarReloj(Model model) {
    return "reloj";
}

}
