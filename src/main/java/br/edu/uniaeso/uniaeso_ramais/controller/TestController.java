package br.edu.uniaeso.uniaeso_ramais.controller;

import br.edu.uniaeso.uniaeso_ramais.model.Ramais;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @GetMapping("/test")
    public String test(Model model) {
        // Certifique-se de que Ramal é a classe correta
        Ramais ramal = new Ramais();
        model.addAttribute("ramal", ramal);

        // Adicionando uma lista fictícia de ramais para o exemplo
        List<Ramais> listRamais = new ArrayList<>();
        listRamais.add(new Ramais(1L, 1234, "Descrição Exemplo 1"));
        listRamais.add(new Ramais(2L, 5678, "Descrição Exemplo 2"));
        model.addAttribute("listRamais", listRamais);

        return "hello";
    }
}


