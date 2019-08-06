 package com.bassiuz.meubel.parsers;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import com.bassiuz.meubel.domain.Shop;
import com.bassiuz.meubel.responses.MeubelResponse;
import com.bassiuz.meubel.util.StringParserUtil;

import org.jsoup.Jsoup;

public class LeenBakkerParser implements MeubelParser {

    @Override
    public List<MeubelResponse> parseMeubelsForName(String name) {
        List<MeubelResponse> response = new ArrayList<MeubelResponse>();

        try {
            String HTML = Jsoup.connect(
                    "https://www.leenbakker.nl/SearchDisplay?categoryId=&storeId=10151&catalogId=10051&langId=-100&sType=SimpleSearch&resultCatEntryType=2&showResultsPage=true&searchSource=Q&pageView=&beginIndex=0&pageSize=12&searchTerm="
                            + name)
                    .get().html();
                
        System.out.println(HTML);
            if (HTML.contains("updateSearchTermHistoryCookieAndRedirect")) {
                // contains only one product.
                String redirectUrl = StringParserUtil.getStringBetweenTwoStrings(HTML, name + "\", \"", "\");");

                HTML = Jsoup.connect(
                        redirectUrl)
                        .get().html();

                MeubelResponse meubel = new MeubelResponse();
                meubel.setShop(Shop.LEENBAKKER);
                meubel.setShopUrl(redirectUrl);

                String productName = StringParserUtil.getStringBetweenTwoStrings(HTML, "itemprop=\"name\"",
                        "<input type=\"hidden\"");
                productName = StringParserUtil.getStringBetweenTwoStrings(productName, ">", "</h1>");
                meubel.setName(productName);
/*
                String productDescription = StringParserUtil.getStringBetweenTwoStrings(HTML,
                        "<p class=\"product_shortdescription\"", "p>");
                productDescription = StringParserUtil.getStringBetweenTwoStrings(productDescription, "\">", "</");
                meubel.setDescription(productDescription);
                

              String imageURL = HTML.substring(HTML.indexOf("<img alt=\"\" src=\"//") + 17,
                        HTML.indexOf("\" onerror="));
                meubel.setImageUrl(imageURL);

*/
                if (productName.toUpperCase().contains(name.toUpperCase())) {
                    response.add(meubel);
                }

            } else {
                // result returns multiple products.
                String[] parts = HTML.split("<div class=\"product\">");

                for (String productPart : parts) {

                    if (productPart.contains("<img alt=\"\" src=\"//")) {
                        MeubelResponse meubel = new MeubelResponse();
                        meubel.setShop(Shop.LEENBAKKER);

                        String imageURL = productPart.substring(productPart.indexOf("<img alt=\"\" src=\"//") + 17,
                                productPart.indexOf("\" onerror="));
                        meubel.setImageUrl(imageURL);

                        String productName = StringParserUtil.getStringBetweenTwoStrings(productPart,
                                "<div class=\"product_name\">", "<div class=\"product_description\">");
                        productName = StringParserUtil.getStringBetweenTwoStrings(productName, ";\">", "</a>");
                        meubel.setName(productName);

                        String productUrl = StringParserUtil.getStringBetweenTwoStrings(productPart,
                                "<a id=\"catalogEntry", "<img alt=\"\" src=\"");
                        productUrl = StringParserUtil.getStringBetweenTwoStrings(productUrl, "\" href=\"", "\" title=");
                        meubel.setShopUrl(productUrl);

                        String productDescription = StringParserUtil.getStringBetweenTwoStrings(productPart,
                                "<p class=\"product_shortdescription\"", "p>");
                        productDescription = StringParserUtil.getStringBetweenTwoStrings(productDescription, "\">",
                                "</");
                        meubel.setDescription(productDescription);

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
