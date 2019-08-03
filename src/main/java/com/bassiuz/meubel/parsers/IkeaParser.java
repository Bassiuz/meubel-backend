package com.bassiuz.meubel.parsers;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import com.bassiuz.meubel.MeubelResponse;
import com.bassiuz.meubel.Shop;
import com.bassiuz.meubel.util.StringParserUtil;

import org.jsoup.Jsoup;
import org.springframework.util.SocketUtils;

public class IkeaParser {

    public static List<MeubelResponse> parseMeubelsFromIkeaForName(String name) {
        List<MeubelResponse> response = new ArrayList<MeubelResponse>();

        try {
            String HTML = Jsoup.connect("https://www.ikea.com/nl/nl/search/?query=" + name).get().html();

            if (HTML.contains("id=\"productsTable\"")) {

                String[] parts = HTML.split("<div class=\"productPadding");

                String firstPart = parts[1];

                for (String productPart : parts) {


                    if (productPart.contains("src=") && productPart.contains("border")) {

                        MeubelResponse meubel = new MeubelResponse();
                        meubel.setShop(Shop.IKEA);

                        String imageURL = StringParserUtil.getStringBetweenTwoStrings(productPart, "src=", "border");

                        meubel.setImageUrl("https://www.ikea.com" + imageURL.replace("\"", ""));

                        String productName = StringParserUtil.getStringBetweenTwoStrings(productPart, "prodNameTro\">", "</span>")
                        + " - " + StringParserUtil.getStringBetweenTwoStrings(productPart, "c\">", "</span>");
                        meubel.setName(productName);

                        response.add(meubel);

                    }
                }

            }

        } catch (

        IOException e) {

            e.printStackTrace();
        }

        return response;
    }

}
