package br.edu.uniaeso.uniaeso_ramais.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.uniaeso.uniaeso_ramais.model.Ramais;
import br.edu.uniaeso.uniaeso_ramais.repository.RamaisRepository;


@Controller
public class RamaisController {

    @Autowired
    private RamaisRepository ramaisRepo;

    @GetMapping("/")
    public String home() {
        return "hello";
    }

    @PostMapping("/save")
    public String saveRamal(Ramais ramais, Model model) {
        ramaisRepo.save(ramais);
        return "hello";
    }    

}
