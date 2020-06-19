package education.p0002.group;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupController {
    @GetMapping(path = "group")
    String group(Model model) {
        Page page = new Page();



        model.addAttribute("page", page);
        return "search";
    }

    @Getter
    @Setter
    static public class Page {
    }
}
