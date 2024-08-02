package br.edu.uniaeso.uniaeso_ramais.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.uniaeso.uniaeso_ramais.model.Ramais;
import br.edu.uniaeso.uniaeso_ramais.repository.RamaisRepository;

@Controller
public class RamaisController {

    @Autowired
    private RamaisRepository ramaisRepo;

    @GetMapping("/")
    public String home(Model model) {
        List<Ramais> listRamais = (List<Ramais>) ramaisRepo.findAll();
        model.addAttribute("listRamais", listRamais);
        model.addAttribute("ramal", new Ramais());
        return "hello";
    }

    @PostMapping("/save")
    public String saveRamal(@ModelAttribute Ramais ramais) {
        ramaisRepo.save(ramais);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditRamalPage(@PathVariable("id") Long id, Model model) {
        Ramais ramais = ramaisRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ramal Id:" + id));
        model.addAttribute("ramal", ramais);
        return "edit_ramal";
    }

    @PostMapping("/update/{id}")
    public String updateRamal(@PathVariable("id") Long id, @ModelAttribute Ramais ramais) {
        ramais.setId(id);
        ramaisRepo.save(ramais);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteRamal(@PathVariable("id") Long id) {
        ramaisRepo.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchRamais(@RequestParam("descricao") String descricao, Model model) {
        List<Ramais> listRamais = ramaisRepo.findByDescricaoContainingIgnoreCase(descricao);
        model.addAttribute("listRamais", listRamais);
        model.addAttribute("ramal", new Ramais());
        return "hello";
    }
}
