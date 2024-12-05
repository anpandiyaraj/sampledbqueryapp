package pan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class QueryController {

    @Autowired
    private QueryService queryService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/execute")
    public String executeQuery(@RequestParam("query") String query, Model model) {
        try {
            List<Map<String, Object>> results = queryService.executeQuery(query);
            Set<String> columnNames = results.isEmpty() ? Set.of() : results.get(0).keySet();
            model.addAttribute("results", results);
            model.addAttribute("columnNames", columnNames);
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while executing the query: " + e.getMessage());
        }
        return "index";
    }
}
