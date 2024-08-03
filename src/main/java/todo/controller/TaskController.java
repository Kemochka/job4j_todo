package todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import todo.model.task.Task;
import todo.service.task.TaskService;

@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/create")
    public String getCreationPage() {
        return "tasks/create";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var taskOptional = taskService.getById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Task by id not found");
            return "errors/404";
        }
        model.addAttribute("task", taskOptional.get());
        return "tasks/one";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @ModelAttribute Task task) {
        var isDeleted = taskService.delete(task);
        if (!isDeleted) {
            model.addAttribute("message", "Failed to delete task");
            return "errors/404";
        }
        return "redirect:/index";
    }

    @GetMapping("/change/{id}")
    public String change(Model model, @PathVariable int id) {
        var taskOptional = taskService.getById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Failed to change task");
            return "errors/404";
        }
        model.addAttribute("task", taskOptional.get());
        return "tasks/change";
    }

    @GetMapping("/done/{id}")
    public String done(Model model, @PathVariable int id) {
        if (!taskService.setDone(id)) {
            model.addAttribute("message", "Failed to update task status");
            return "errors/404";
        }
        return "redirect:/index";
    }

    @PostMapping("/create")
    public String getCreationPage(@ModelAttribute Task task, Model model) {
        try {
            taskService.save(task);
            return "redirect:/index";

        } catch (Exception e) {
            model.addAttribute("message", "Task don`t saved");
            return "errors/404";
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task, Model model) {
        try {
            var isUpdated = taskService.update(task);
            if (!isUpdated) {
                model.addAttribute("message", "Failed to update task");
                return "errors/404";
            }
            return "redirect:/index";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "errors/404";
    }
}
