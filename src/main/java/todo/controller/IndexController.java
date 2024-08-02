package todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import todo.service.task.TaskService;

@Controller
@AllArgsConstructor
public class IndexController {
    private final TaskService taskService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

    @GetMapping({"/new"})
    public String getNew(Model model) {
        model.addAttribute("tasks", taskService.findByDone(false));
        return "index";
    }

    @GetMapping({"/done"})
    public String getDone(Model model) {
        model.addAttribute("tasks", taskService.findByDone(true));
        return "index";
    }
}
