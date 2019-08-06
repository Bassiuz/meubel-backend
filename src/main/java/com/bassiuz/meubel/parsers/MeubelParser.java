package com.bassiuz.meubel.parsers;

import java.util.List;

import com.bassiuz.meubel.responses.MeubelResponse;

public interface MeubelParser {
    public List<MeubelResponse> parseMeubelsForName(String name);
}