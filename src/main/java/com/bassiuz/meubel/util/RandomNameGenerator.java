package com.bassiuz.meubel.util;

import java.util.Random;

import com.bassiuz.meubel.database.HerokuConfigReader;

public class RandomNameGenerator {

    public static String getRandomName() {
        Random random = new Random();
        String[] name = { "John", "Bas", "Sebas", "Berend", "Marc", "Mark", "Bram", "Tim", "Daan", "Sanne", "Sem",
                "Julia", "Thomas", "Emma", "Tim", "Sophie", "Lucas", "Lisa", "Lars", "Lotte", "Milan", "Anna", "Thijs",
                "Eva", "Jesse", "Fleur", "Ruben", "Anne", "Stijn", "Isa", "Lieke", "Luuk", "Iris" };

        HerokuConfigReader.readConfig();

        return name[random.nextInt(name.length)];
    }

}
