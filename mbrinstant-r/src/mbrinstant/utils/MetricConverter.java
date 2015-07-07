/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.utils;

/**
 *
 * @author Admin
 */
public abstract class MetricConverter {

    public enum WeightUnit {

        mcg(1e-6),
        mg(1e-3),
        g(1),
        kg(1e3);

        private final double level;

        WeightUnit(double level) {
            this.level = level;
        }

        public double getLevel() {
            return this.level;
        }
    }

    enum VolumeUnit {

        mcL(1e-6),
        mL(1e-3),
        L(1),
        kL(1e3);

        private final double level;

        VolumeUnit(double level) {
            this.level = level;
        }

        public double getLevel() {
            return this.level;
        }
    }

    public static Object getEnumUnit(String unit) {

        for (WeightUnit wu : WeightUnit.values()) {
            if (wu.toString().equals(unit)) {
                return wu;
            }
        }

        for (VolumeUnit vu : VolumeUnit.values()) {
            if (vu.toString().equals(unit)) {
                return vu;
            }
        }

        return null;
    }

    public static boolean isInWeight(String unit) {
        for (WeightUnit wu : WeightUnit.values()) {
            if (wu.toString().equals(unit)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInVolume(String unit) {
        for (VolumeUnit wu : VolumeUnit.values()) {
            if (wu.toString().equals(unit)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isConvertable(String unit1, String unit2) {
        if (isInWeight(unit1) && isInWeight(unit2)) {
            return true;
        } else if (isInVolume(unit1) && isInVolume(unit2)) {
            return true;
        }
        return false;//throw exception here
    }

    //weight conversion
    public static double convertMicrogramToMilligram(double weight) {
        return (weight / (double) 1e3);
    }

    public static double convertMicrogramToGram(double weight) {
        return (weight / (double) 1e6);
    }

    public static double convertMicrogramToKilogram(double weight) {
        return (weight / (double) 1e9);
    }

    public static double convertMilligramToGram(double weight) {
        return (weight / (double) 1e3);
    }

    public static double convertMilligramToKilogram(double weight) {
        return (weight / (double) 1e6);
    }

    public static double convertGramToKilogram(double weight) {
        return (weight / (double) 1e3);
    }

    public static double convertKilogramToGram(double weight) {
        return (weight * (double) 1e3);
    }

    public static double convertKilogramToMilligram(double weight) {
        return (weight * (double) 1e6);
    }

    public static double convertKilogramToMicrogram(double weight) {
        return (weight * (double) 1e9);
    }

    public static double convertGramToMilligram(double weight) {
        return (weight * (double) 1e3);
    }

    public static double convertGramToMicrogram(double weight) {
        return (weight * (double) 1e6);
    }

    public static double convertMilligramToMicrogram(double weight) {
        return (weight * (double) 1e3);
    }

    //volume conversion
    public static double convertMicrolitreToMillilitre(double volume) {
        return (volume / (double) 1e3);
    }

    public static double convertMicrolitreToLitre(double volume) {
        return (volume / (double) 1e6);
    }

    public static double convertMillilitreToLitre(double volume) {
        return (volume / (double) 1e3);
    }

    public static double convertLitreToMillilitre(double volume) {
        return (volume * (double) 1e3);
    }

    public static double convertLitreToMicrolitre(double volume) {
        return (volume * (double) 1e6);
    }

    public static double convertMillilitreToMicrolitre(double volume) {
        return (volume * (double) 1e3);
    }

}
