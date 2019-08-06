package com.bassiuz.meubel.parsers;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import com.bassiuz.meubel.domain.Shop;
import com.bassiuz.meubel.responses.MeubelResponse;
import com.bassiuz.meubel.util.StringParserUtil;

import org.jsoup.Jsoup;
import org.springframework.util.SocketUtils;


public class IkeaParser implements MeubelParser {

    @Override
    public List<MeubelResponse> parseMeubelsForName(String name) {
        List<MeubelResponse> response = new ArrayList<MeubelResponse>();

        try {
            String HTML = Jsoup.connect("https://www.ikea.com/nl/nl/search/?query=" + name).get().html();

            if (HTML.contains("id=\"productDisplay\"")) {
                MeubelResponse meubel = new MeubelResponse();
                meubel.setShop(Shop.IKEA);


                String productInformation = StringParserUtil.getStringBetweenTwoStrings(HTML, "<div id=\"productInfoWrapper1\">", "<div class=\"itemNumber\">");

               
                String productName = StringParserUtil.getStringBetweenTwoStrings(productInformation, "productName\"> ",
                        " </span>");
                meubel.setName(productName);

                String productDescription = StringParserUtil.getStringBetweenTwoStrings(productInformation, "productType",
                        "</h1>");
                productDescription = StringParserUtil.getStringBetweenTwoStrings(productDescription, "\">",
                        " <strong>");
                meubel.setDescription(productDescription);


                String imageURL = StringParserUtil.getStringBetweenTwoStrings(HTML, "<img id=\"productImg\" ", "<div id=\"rightNavContainer\"");
                imageURL = StringParserUtil.getStringBetweenTwoStrings(imageURL, "src=\"", "\" alt=\"");

                meubel.setImageUrl("https://www.ikea.com" + imageURL);

                
                String shopUrl = StringParserUtil.getStringBetweenTwoStrings(HTML, "<link rel=\"canonical\" href=\"",
                        "<meta name=\"");
                meubel.setShopUrl(shopUrl.replace("\">", ""));

                if (productName.toUpperCase().contains(name.toUpperCase())) {
                    response.add(meubel);
                }
            }
            else if (HTML.contains("id=\"productsTable\"")) {

                String[] parts = HTML.split("<div class=\"productPadding");

                for (String productPart : parts) {
                    if (productPart.contains("src=") && productPart.contains("border")) {

                        MeubelResponse meubel = new MeubelResponse();
                        meubel.setShop(Shop.IKEA);

                        String imageURL = StringParserUtil.getStringBetweenTwoStrings(productPart, "src=", "border");

                        meubel.setImageUrl("https://www.ikea.com" + imageURL.replace("\"", ""));

                        String productName = StringParserUtil.getStringBetweenTwoStrings(productPart, "prodNameTro\">",
                                "</span>");
                        meubel.setName(productName);

                        String productDescription = StringParserUtil.getStringBetweenTwoStrings(productPart, "c\">",
                                "</span>");
                        meubel.setDescription(productDescription.replace("&nbsp; ", ""));

                        String shopUrl = StringParserUtil.getStringBetweenTwoStrings(productPart, "<a href=\"",
                                "\" onclick=\"irwStatTopProductClicked();\"");
                        meubel.setShopUrl("https://www.ikea.com" + shopUrl);

                        if (productName.toUpperCase().contains(name.toUpperCase())) {
                            response.add(meubel);
                        }

                    }
                }

            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return response;
    }

}
