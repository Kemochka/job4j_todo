package todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import todo.model.task.Task;
import todo.model.user.User;
import todo.service.category.CategoryService;
import todo.service.priority.PriorityService;
import todo.service.task.TaskService;

import java.util.Set;

@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final PriorityService priorityService;
    private final CategoryService categoryService;

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("priorities", priorityService.findAll());
        return "tasks/create";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var taskOptional = taskService.getById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Task by id not found");
            return "errors/404";
        }
        model.addAttribute("categories", categoryService.findAll());
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
        model.addAttribute("priorities", priorityService.findAll());
        model.addAttribute("categories", categoryService.findAll());
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
    public String create(@ModelAttribute Task task,  @SessionAttribute("user") User user,
                         @RequestParam(required = false) Set<Integer> idCategories) {
        if (!idCategories.isEmpty()) {
            task.setCategories(categoryService.findCategoriesById(idCategories));
        }
        task.setUser(user);
        if (taskService.save(task).isPresent()) {
            return "redirect:/";
        }
        return "errors/404";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task, Model model, @SessionAttribute("user") User user,
                         @RequestParam(required = false) Set<Integer> idCategories) {
        task.setUser(user);
        if (!idCategories.isEmpty()) {
            task.setCategories(categoryService.findCategoriesById(idCategories));
        }
        if (!taskService.update(task)) {
            model.addAttribute("message", "Failed to update task");
            return "errors/404";
        }
        return "redirect:/";
    }
}
