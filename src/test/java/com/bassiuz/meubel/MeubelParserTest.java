package com.bassiuz.meubel;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.bassiuz.meubel.parsers.IkeaParser;
import com.bassiuz.meubel.parsers.LeenBakkerParser;
import com.bassiuz.meubel.responses.MeubelResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MeubelParserTest {

        @Parameters
        public static Collection<Object[]> data() {

                List<MeubelTestPojo> meubelTestPojos = new ArrayList<>();

                MeubelTestPojo ikeaTestPojo = new MeubelTestPojo(new IkeaParser(), "Billy", "Storsele", "Sebastiaan",
                                "Bas");
                meubelTestPojos.add(ikeaTestPojo);

                MeubelTestPojo leenBakkerTestPojo = new MeubelTestPojo(new LeenBakkerParser(), "Bas", "Droogrek Tim",
                                "Sebastiaan", "Berend");
                meubelTestPojos.add(leenBakkerTestPojo);

                return Arrays.asList(new Object[][] { { leenBakkerTestPojo }, { ikeaTestPojo } });
        }

        @Parameter
        public MeubelTestPojo meubelTestPojo;

        @Test
        public void testMultipleResults() {
                List<MeubelResponse> meubelResponses = meubelTestPojo.getMeubelParser()
                                .parseMeubelsForName(meubelTestPojo.getNameWithMultipleResults());
                assertTrue(meubelTestPojo.getMeubelParser().getClass().getName() + " gets multiple results.",
                                meubelResponses.size() > 1);

                testMeubels(meubelResponses);
        }

        @Test
        public void testSingleResult() {
                List<MeubelResponse> meubelResponses = meubelTestPojo.getMeubelParser()
                                .parseMeubelsForName(meubelTestPojo.getNameWithJustOneResult());
                assertTrue(meubelTestPojo.getMeubelParser().getClass().getName() + " gets exactly one result.",
                                meubelResponses.size() == 1);
                testMeubels(meubelResponses);

        }

        @Test
        public void testNoResults() {
                List<MeubelResponse> meubelResponses = meubelTestPojo.getMeubelParser()
                                .parseMeubelsForName(meubelTestPojo.getNameWithNoResults());
                assertTrue(meubelTestPojo.getMeubelParser().getClass().getName() + " gets no results.",
                                meubelResponses.size() == 0);
                testMeubels(meubelResponses);

        }

        @Test
        public void testResultsButNotContainingName() {
                List<MeubelResponse> meubelResponses = meubelTestPojo.getMeubelParser()
                                .parseMeubelsForName(meubelTestPojo.getNameWithResultsButNotContainingName());
                assertTrue(meubelTestPojo.getMeubelParser().getClass().getName()
                                + " gets results but doesn't show them because the name doesn't match the results.",
                                meubelResponses.size() == 0);
                testMeubels(meubelResponses);

        }

        private void testMeubels(List<MeubelResponse> meubelResponses) {
                for (MeubelResponse meubelResponse : meubelResponses) {
                        testMeubelResponse(meubelResponse);
                }
        }

        private void testMeubelResponse(MeubelResponse meubelResponse) {
                assertTrue("Shop of meubelresponse is set", meubelResponse.getShop() != null);
                assertTrue("Name of meubelresponse is set",
                                meubelResponse.getName() != null && meubelResponse.getName().length() > 0);
                assertTrue("ShopUrl of meubelresponse is set",
                                meubelResponse.getShopUrl() != null && meubelResponse.getShopUrl().length() > 0);
                assertTrue("Description of meubelresponse is set", meubelResponse.getDescription() != null
                                && meubelResponse.getDescription().length() > 0);
                assertTrue("ImageUrl of meubelresponse is set",
                                meubelResponse.getImageUrl() != null && meubelResponse.getImageUrl().length() > 0);
        }

}
