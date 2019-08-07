package com.bassiuz.meubel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bassiuz.meubel.domain.Meubel;
import com.bassiuz.meubel.parsers.IkeaParser;
import com.bassiuz.meubel.parsers.LeenBakkerParser;
import com.bassiuz.meubel.parsers.MeubelParser;
import com.bassiuz.meubel.responses.MeubelResponse;
import com.bassiuz.meubel.util.RandomNameGenerator;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MeubelRepository meubelRepository;

    @GetMapping("/")
    public String index() {
        return "This will get a meubel result.";
    }
    
    public List<MeubelParser> getMeubelParsers()
    {
        ArrayList<MeubelParser> parsers = new ArrayList<>();
        parsers.add(new LeenBakkerParser());
        parsers.add(new IkeaParser());
        return parsers;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
    public List<MeubelResponse> getMeubel( @RequestParam("name") String name) {
        List<MeubelResponse> responseList  = new ArrayList<>();
        List<Meubel> meubelsFromDatabase = meubelRepository.fetchMeubelsByName(name);
        responseList.addAll(MeubelResponse.fromMeubelList(meubelsFromDatabase));

        for (MeubelParser meubelParser : getMeubelParsers())
        {
            List<MeubelResponse> responsesFromParser =  meubelParser.parseMeubelsForName(name);

            for(MeubelResponse response : responsesFromParser)
            {
                if (!responseList.contains(response))
                {
                    responseList.add(response);
                }
            }
        }

        return responseList;
    }

    @RequestMapping(value = "/getSomeRandom", method = RequestMethod.GET, produces = "application/json")
    public List<MeubelResponse> getSomeRandom() {
        List<MeubelResponse> totalResponse = new ArrayList<>();
        final int amountOfRandomResults = 5;
        
        while(totalResponse.size() < amountOfRandomResults) {
            String name = RandomNameGenerator.getRandomName();
            List<MeubelResponse> response = getMeubel(name);
            if (response.size() > 0)
            {
                totalResponse.add(response.get(0));
            }
        }
      
        return totalResponse;
    }

      @RequestMapping(value = "/getTop5", method = RequestMethod.GET, produces = "application/json")
    public List<MeubelResponse> getTop5() {
        List<MeubelResponse> responseList  = new ArrayList<>();
        List<Meubel> meubelsFromDatabase = meubelRepository.fetchTop5Meubels();
        responseList.addAll(MeubelResponse.fromMeubelList(meubelsFromDatabase));
      
        return responseList;
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