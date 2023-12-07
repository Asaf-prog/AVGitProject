package com.maven.test.avgitproject.dto;

import com.maven.test.avgitproject.entity.Sh1Detail;
import java.util.ArrayList;
import java.util.List;

public class Convertor {

    private static Convertor instance = null;
    private Convertor() { }

    public static Convertor getInstance() {
        if (instance == null)
            instance = new Convertor();
        return instance;
    }

    public static List<Sha1DetailDTO> convertSh1Details(List<Sh1Detail> sh1Details){
        List<Sha1DetailDTO> sha1DetailDTOS = new ArrayList<>();
        for (Sh1Detail sh1Detail: sh1Details){
            sha1DetailDTOS.add(
                    new Sha1DetailDTO(sh1Detail.getSh1(),
                    sh1Detail.getName(),
                    sh1Detail.getPath())
            );
        }
        return sha1DetailDTOS;
    }

}
