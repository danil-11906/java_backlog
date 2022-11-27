package ru.itis.springbootdemo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.springbootdemo.dto.ServiceDto;
import ru.itis.springbootdemo.security.details.UserDetailsImpl;
import ru.itis.springbootdemo.services.ServicesService;

import java.util.List;

@Controller
public class BagController {


    @Autowired
    private ServicesService servicesService;

    @GetMapping("/bag")
    public String getServicesPage(Model model) {
        model.addAttribute("servicesList", servicesService.getServicesName());
        return "bag";
    }

    @PostMapping("/bag")
    public String savePerson(@RequestParam("selected") String selected,
                             @AuthenticationPrincipal UserDetailsImpl user,
                             Model model) {
        String[] checked = selected.split(",");
        List<ServiceDto> listService = servicesService.getAllServices();
        Long userId = user.getId();
        for (ServiceDto serviceDto : listService) {
            for (String string : checked) {
                if (string.equals(serviceDto.getName())){
                    try {
                        servicesService.addForBagService(serviceDto, userId);
                    } catch (Exception e) {
                        return "error_page";
                    }
                }
            }
        }
        model.addAttribute("checked", selected);
        return "confirmed_bag_page";
    }

}
