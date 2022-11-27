package ru.itis.springbootdemo.controllers;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;
        import ru.itis.springbootdemo.dto.ServiceDto;
        import ru.itis.springbootdemo.dto.ServiceForm;
        import ru.itis.springbootdemo.services.ServicesService;

        import java.util.ArrayList;
        import java.util.List;

@Controller
public class ServicesController {

    private int size = 3;

    @Autowired
    private ServicesService servicesService;

    @GetMapping("/services")
    public String getServicesPage(Model model) {
        model.addAttribute("servicesList", servicesService.getAllServices());
        return "services";
    }

    @GetMapping("/allservices")
    @ResponseBody
    public ResponseEntity<List<ServiceDto>> getAllServices() {
        return ResponseEntity.ok(servicesService.getAllServices());
    }

    @PostMapping("/services")
    @ResponseBody
    public ResponseEntity<ServiceDto> addService(@RequestBody ServiceForm serviceForm) {
        return ResponseEntity.ok(servicesService.addService(serviceForm));
    }

    @GetMapping("/papers/service/search")
    @ResponseBody
    public ResponseEntity<List<ServiceDto>> search(@RequestParam("page") Integer page,
                                                   @RequestParam(value = "q", required = false) String query,
                                                   @RequestParam(value = "sort", required = false) String sort,
                                                   @RequestParam(value = "direction", required = false) String direction) {
        return ResponseEntity.ok(servicesService.search(size, page, query, sort, direction));
    }

    @GetMapping("/list")
    public String getListPage(Model model) {
        long num = servicesService.countService()/size;
        if (servicesService.countService()%size > 0) {
            num++;
        }
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < num; i++) {
            list.add(i+1);
        }
        model.addAttribute("countList", list);
        return "service";
    }

}
