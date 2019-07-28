package com.bassiuz.meubel.parsers;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import com.bassiuz.meubel.MeubelResponse;

import org.jsoup.Jsoup;

public class LeenBakkerParser {

    public static List<MeubelResponse> parseMeubelsFromLeenBakkerForName(String name)
    {
        List<MeubelResponse> response = new ArrayList<MeubelResponse>();

        try {
            String HTML = Jsoup.connect(
                    "https://www.leenbakker.nl/SearchDisplay?categoryId=&storeId=10151&catalogId=10051&langId=-100&sType=SimpleSearch&resultCatEntryType=2&showResultsPage=true&searchSource=Q&pageView=&beginIndex=0&pageSize=12&searchTerm=" + name)
                    .get().html();


                    String[] parts = HTML.split("<div class=\"product\">");

                    String firstPart = parts[1];
                
                    for(String productPart : parts)
                    {
                        
                        if (productPart.contains("<img alt=\"\" src=\"//"))
                        {
                            MeubelResponse meubel = new MeubelResponse();
                            String imageURL = productPart.substring(productPart.indexOf("<img alt=\"\" src=\"//") + 17, productPart.indexOf("\" onerror="));
                            meubel.setImageUrl(imageURL);

                            String productName = productPart.substring(productPart.indexOf("<div class=\"product_name\">"), productPart.indexOf("<div class=\"product_description\">"));
                            productName = productName.substring(productName.indexOf(";\">") + 3, productName.indexOf("</a>"));
                            meubel.setName(productName);

                            response.add(meubel);
                        }
                    }



        } catch (IOException e) {
           
			e.printStackTrace();
		}


        return response;
    }

    
}
