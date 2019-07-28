package com.bassiuz.meubel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bassiuz.meubel.parsers.LeenBakkerParser;

import org.jsoup.Jsoup;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/LeenBakker")
public class LeenBakkerController {

    
    @GetMapping("/")
    public String index() {
        return "This will get a Leen Bakker result.";
    }

    @RequestMapping(value = "/Meubel", method = RequestMethod.GET, produces = "application/json")
    public List<MeubelResponse> getMeubel( @RequestParam("name") String name) {

        List<MeubelResponse> response = LeenBakkerParser.parseMeubelsFromLeenBakkerForName(name);


        return response;
    }
}
