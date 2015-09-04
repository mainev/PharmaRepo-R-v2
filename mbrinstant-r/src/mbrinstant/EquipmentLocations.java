/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maine
 */
public class EquipmentLocations {

    public static enum MANUFACTURING_PROCEDURE {

        COMPOUNDING, ENCAPSULATION, CODING, PACKG_PROCEDURE, POWDER_FILLING
    }

    public static List<String> getEquipmentLocationsForPowderArea() {
        List<String> list = new ArrayList();

        list.add(MANUFACTURING_PROCEDURE.COMPOUNDING.toString());
        list.add(MANUFACTURING_PROCEDURE.POWDER_FILLING.toString());
        list.add(MANUFACTURING_PROCEDURE.CODING.toString());
        return list;
    }

    public static List<String> getEquipmentLocationsForPowderVet() {
        List<String> list = new ArrayList();

        list.add(MANUFACTURING_PROCEDURE.COMPOUNDING.toString());
        list.add(MANUFACTURING_PROCEDURE.CODING.toString());
        list.add(MANUFACTURING_PROCEDURE.POWDER_FILLING.toString());
        return list;
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
