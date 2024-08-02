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
    public String index() {
        return "index";
    }

    @GetMapping("/create")
    public String createRamal(Model model) {
        model.addAttribute("ramal", new Ramais());
        return "create";
    }

    @PostMapping("/save")
    public String saveRamal(@ModelAttribute Ramais ramais) {
        ramaisRepo.save(ramais);
        return "redirect:/list";
    }

    @GetMapping("/search")
public String searchRamal(@RequestParam(value = "descricao", required = false) String descricao, Model model) {
    List<Ramais> listRamais = (descricao != null && !descricao.isEmpty())
        ? ramaisRepo.findByDescricaoContainingIgnoreCase(descricao)
        : (List<Ramais>) ramaisRepo.findAll();
    model.addAttribute("listRamais", listRamais);
    return "search";
}


    @GetMapping("/list")
    public String listRamais(Model model) {
        List<Ramais> listRamais = (List<Ramais>) ramaisRepo.findAll();
        model.addAttribute("listRamais", listRamais);
        return "list";
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
        return "redirect:/list";
    }
}
