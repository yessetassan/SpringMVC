package project.controllers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.dao.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project.models.Player;

import java.util.List;

@Controller
@RequestMapping("/all")
public class FootballController {

    private final PlayerDao playerDao;

    @Autowired
    public FootballController(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @GetMapping("")
    public String all(Model model) {
        model.addAttribute("list", playerDao.all());
        return "/all";
    }

    @PostMapping("")
    public String cr(@ModelAttribute("player") Player player) {
        playerDao.add(player);
        return "redirect:/all";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("player") Player player){
        return "/create";
    }

    @GetMapping("/{id}")
    public String all(@PathVariable("id") int id, Model model) {
        model.addAttribute("player", playerDao.show(id));
        return "/show";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("player") Player player) {
        System.out.println("Patch !");
        playerDao.save(id, player);
        return "redirect:/all/{id}";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("player", playerDao.show(id));
        return "/edit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        playerDao.delete(id);
        return "redirect:/all";
    }


}
