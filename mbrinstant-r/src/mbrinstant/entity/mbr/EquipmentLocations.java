/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.mbr;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maine
 */
public class EquipmentLocations {

    static enum MANUFACTURING_PROCEDURE {

        COMPOUNDING, ENCAPSULATION, CODING, PACKG_PROCEDURE
    }

    public static List<String> getEquipmentLocationsForTabletHuman() {
        List<String> list = new ArrayList();

        list.add(MANUFACTURING_PROCEDURE.COMPOUNDING.toString());
        list.add(MANUFACTURING_PROCEDURE.ENCAPSULATION.toString());
        return list;
    }

    public static List<String> getEquipmentLocationsForAll() {
        List<String> list = new ArrayList();

        list.add(MANUFACTURING_PROCEDURE.COMPOUNDING.toString());
        list.add(MANUFACTURING_PROCEDURE.CODING.toString());
        list.add(MANUFACTURING_PROCEDURE.ENCAPSULATION.toString());
        list.add(MANUFACTURING_PROCEDURE.PACKG_PROCEDURE.toString());
        return list;
    }
}
