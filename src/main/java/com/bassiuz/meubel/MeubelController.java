package com.bassiuz.meubel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bassiuz.meubel.parsers.IkeaParser;
import com.bassiuz.meubel.parsers.LeenBakkerParser;
import com.bassiuz.meubel.responses.MeubelResponse;
import com.bassiuz.meubel.util.RandomNameGenerator;

import org.jsoup.Jsoup;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/Meubel")
public class MeubelController {

    
    @GetMapping("/")
    public String index() {
        return "This will get a meubel result.";
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
    public List<MeubelResponse> getMeubel( @RequestParam("name") String name) {
        List<MeubelResponse> response  = new ArrayList<>();
        List<MeubelResponse> leenBakkerResponses = new LeenBakkerParser().parseMeubelsForName(name);
        List<MeubelResponse> ikeaResponses =  new IkeaParser().parseMeubelsForName(name);

        response.addAll(leenBakkerResponses);
        response.addAll(ikeaResponses);

        return response;
    }

    @RequestMapping(value = "/getSomeRandom", method = RequestMethod.GET, produces = "application/json")
    public List<MeubelResponse> getSomeRandom() {
        List<MeubelResponse> totalResponse  = new ArrayList<>();
        final int amountOfRandomResults = 5;
        
        for (int i = 0; i < amountOfRandomResults; i++) {
            String name = RandomNameGenerator.getRandomName();
            List<MeubelResponse> response  = new ArrayList<>();
            List<MeubelResponse> leenBakkerResponses = new LeenBakkerParser().parseMeubelsForName(name);
            List<MeubelResponse> ikeaResponses =  new IkeaParser().parseMeubelsForName(name);
    
            response.addAll(leenBakkerResponses);
            response.addAll(ikeaResponses);
            if (response.size() > 0)
            {
                totalResponse.add(response.get(0));
            }
            else
            {
                // if a name has no responses, try another name.
                i--;
            }
        }
      
        return totalResponse;
    }

    @RequestMapping(value = "/getRandomName", method = RequestMethod.GET, produces = "application/json")
    public String getRandomName() {
        return RandomNameGenerator.getRandomName();
    }

    @RequestMapping(value = "/getAllFromShop", method = RequestMethod.GET, produces = "application/json")
    public List<MeubelResponse> getMeubelFromShop(@RequestParam("name") String name, @RequestParam("store") String store) {
        if(store.equals("IKEA"))
        {
            return new IkeaParser().parseMeubelsForName(name);
        }
        else if(store.equals("LEEN BAKKER"))
        {
            return new LeenBakkerParser().parseMeubelsForName(name);
        }
        return null;
    }
}