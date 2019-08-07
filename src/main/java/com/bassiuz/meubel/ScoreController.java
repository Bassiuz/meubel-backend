package com.bassiuz.meubel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.bassiuz.meubel.domain.Meubel;
import com.bassiuz.meubel.parsers.IkeaParser;
import com.bassiuz.meubel.parsers.LeenBakkerParser;
import com.bassiuz.meubel.responses.MeubelResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/Score")
public class ScoreController {

@Autowired
private MeubelController meubelController;

@Autowired
private MeubelRepository meubelRepository;

    @RequestMapping(value = "/scoreByNameAndIndex", method = RequestMethod.GET, produces = "application/json")
    public MeubelResponse scoreByNameAndIndex( @RequestParam("name") String name, @RequestParam("meubelName") String meubelName ) {
        List<MeubelResponse> meubelList = meubelController.getMeubel(name);

        for(MeubelResponse meubelResponse : meubelList)
        {
            if (meubelResponse.getName().equals(meubelName))
            {

                if (meubelResponse.getId() > 0)
                {
                    // this is already in the database.
                    Optional<Meubel> meubelOptional = meubelRepository.findById(meubelResponse.getId());
                    if(meubelOptional.isPresent())
                    {
                        Meubel meubel = meubelOptional.get();
                        meubel.scoreMeubel();
                        meubelRepository.save(meubel);
                    }
                }
                else
                {
                    Meubel meubel = new Meubel(meubelResponse);
                    meubel.setMatchingPersonName(name);
                    meubelRepository.save(meubel);
                }
               
                // score this meubel.
                return meubelResponse;
            }
        }
        
        return null;
    }

}
