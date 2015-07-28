/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author maine
 */
public abstract class MetricCalculator {

    public static Quantity add(Quantity a, Quantity b) {
        Quantity q = new Quantity();

        Quantity a1 = new Quantity(a);
        Quantity b1 = new Quantity(b);
        try {
            analyzeUnit(a1, b1);
            Double sum = a1.getValue() + b1.getValue();
            q.setValue(sum);
            q.setUnit(a1.getUnit());
        } catch (Exception e) {

            if (a1.getUnit() == (null)) {

                q = b1;
            } else if (b1.getUnit() == (null)) {

                q = a1;
            } else {
                e.printStackTrace();
            }
        }

        return q;
    }

    public static Quantity subtract(Quantity a, Quantity b) {
        Quantity q = new Quantity();

        Quantity a1 = new Quantity(a);
        Quantity b1 = new Quantity(b);
        try {
            analyzeUnit(a1, b1);
            BigDecimal x1 = new BigDecimal(String.valueOf(a1.getValue()));
            BigDecimal x2 = new BigDecimal(String.valueOf(b1.getValue()));
            BigDecimal diff = x1.subtract(x2);

            q.setValue(Double.parseDouble(String.valueOf(diff)));
            q.setUnit(a1.getUnit());
        } catch (Exception e) {
            if (a1.getUnit() == (null)) {
                q = b1;
                q.setValue(-b1.getValue());
            } else if (b1.getUnit() == (null)) {
                q = a1;
            } else {
                e.printStackTrace();
            }
        }
        return q;
    }

    public static boolean isGreaterThanOrEqual(Quantity a, Quantity b) {

        Quantity a1 = new Quantity(a);
        Quantity b1 = new Quantity(b);
        try {
            analyzeUnit(a1, b1);
            return (a1.getValue() >= b1.getValue());

        } catch (IOException e) {
            System.out.println("exception occured");
            e.printStackTrace();
        }

        return false;

    }

    public static boolean isGreaterThan(Quantity a, Quantity b) {

        Quantity a1 = new Quantity(a);
        Quantity b1 = new Quantity(b);
        try {
            analyzeUnit(a1, b1);
            return (a1.getValue() > b1.getValue());

        } catch (IOException e) {
            System.out.println("exception occured");
            e.printStackTrace();
        }

        return false;

    }

    public static boolean isEqual(Quantity a, Quantity b) {

        Quantity a1 = new Quantity(a);
        Quantity b1 = new Quantity(b);
        try {
            analyzeUnit(a1, b1);
            return (a1.getValue() == b1.getValue());

        } catch (IOException e) {
            System.out.println("exception occured");
            e.printStackTrace();
        }

        return false;

    }

    private static void analyzeUnit(Quantity a, Quantity b) throws IOException {
        if (!a.getUnit().equals(b.getUnit())) {
            if (MetricConverter.isConvertable(a.getUnit(), b.getUnit())) {
                switch (a.getUnit()) {
                    case "mcg":
                        switch (b.getUnit()) {
                            case "mg":
                                a.setValue(MetricConverter.convertMicrogramToMilligram(a.getValue()));
                                a.setUnit(b.getUnit());
                                break;
                            case "g":
                                a.setValue(MetricConverter.convertMicrogramToGram(a.getValue()));
                                a.setUnit(b.getUnit());
                                break;
                            case "kg":
                                a.setValue(MetricConverter.convertMicrogramToKilogram(a.getValue()));
                                a.setUnit(b.getUnit());
                        }
                        break;

                    case "mg":
                        switch (b.getUnit()) {
                            case "mcg":
                                b.setValue(MetricConverter.convertMicrogramToMilligram(b.getValue()));
                                b.setUnit(a.getUnit());
                                break;
                            case "g":
                                a.setValue(MetricConverter.convertMilligramToGram(a.getValue()));
                                a.setUnit(b.getUnit());
                                break;
                            case "kg":
                                a.setValue(MetricConverter.convertMilligramToKilogram(a.getValue()));
                                a.setUnit(b.getUnit());
                                break;
                        }
                        break;

                    case "g":
                        switch (b.getUnit()) {
                            case "mcg":
                                b.setValue(MetricConverter.convertMicrogramToGram(b.getValue()));
                                b.setUnit(a.getUnit());
                                break;
                            case "mg":
                                b.setValue(MetricConverter.convertMilligramToGram(b.getValue()));
                                b.setUnit(a.getUnit());
                                break;
                            case "kg":
                                a.setValue(MetricConverter.convertGramToKilogram(a.getValue()));
                                a.setUnit(b.getUnit());
                                break;
                        }
                        break;

                    case "kg":
                        switch (b.getUnit()) {
                            case "mcg":
                                b.setValue(MetricConverter.convertMicrogramToKilogram(b.getValue()));
                                b.setUnit(a.getUnit());
                                break;
                            case "mg":
                                b.setValue(MetricConverter.convertMilligramToKilogram(b.getValue()));
                                b.setUnit(a.getUnit());
                                break;
                            case "g":
                                b.setValue(MetricConverter.convertGramToKilogram(b.getValue()));
                                b.setUnit(a.getUnit());
                                break;
                        }

                    case "mcL":
                        switch (b.getUnit()) {
                            case "mL":
                                a.setValue(MetricConverter.convertMicrolitreToMillilitre(a.getValue()));
                                a.setUnit(b.getUnit());
                                break;
                            case "L":
                                a.setValue(MetricConverter.convertMicrolitreToLitre(a.getValue()));
                                a.setUnit(b.getUnit());
                                break;
                        }
                        break;

                    case "mL":
                        switch (b.getUnit()) {
                            case "mcL":
                                b.setValue(MetricConverter.convertMicrolitreToMillilitre(b.getValue()));
                                b.setUnit(a.getUnit());
                                break;
                            case "L":
                                a.setValue(MetricConverter.convertMillilitreToLitre(a.getValue()));
                                a.setUnit(b.getUnit());
                                break;
                        }
                        break;
                    case "L":
                        switch (b.getUnit()) {
                            case "mcL":
                                b.setValue(MetricConverter.convertMicrolitreToLitre(b.getValue()));
                                b.setUnit(a.getUnit());
                                break;
                            case "mL":
                                b.setValue(MetricConverter.convertMillilitreToLitre(b.getValue()));
                                b.setUnit(a.getUnit());
                                break;
                        }
                        break;
                }
            } else {//unit is not convertable
                throw new IOException(a + " and " + b + " is not convertible");
            }

        }//unit are equal

    }

    private static void analyzeUnitReverse(Quantity a, Quantity b) {
        if (!a.getUnit().equals(b.getUnit())) {
            if (MetricConverter.isConvertable(a.getUnit(), b.getUnit())) {
                switch (a.getUnit()) {
                    case "mcg":
                        switch (b.getUnit()) {
                            case "mg":
                                b.setValue(MetricConverter.convertMilligramToMicrogram(b.getValue()));
                                b.setUnit(a.getUnit());
                                break;
                            case "g":
                                b.setValue(MetricConverter.convertGramToMicrogram(b.getValue()));
                                b.setUnit(a.getUnit());
                                break;
                            case "kg":
                                b.setValue(MetricConverter.convertKilogramToMicrogram(b.getValue()));
                                b.setUnit(a.getUnit());
                        }
                        break;

                    case "mg":

                        switch (b.getUnit()) {
                            case "mcg":
                                a.setValue(MetricConverter.convertMilligramToMicrogram(a.getValue()));
                                a.setUnit(b.getUnit());
                                break;
                            case "g":
                                b.setValue(MetricConverter.convertGramToMilligram(b.getValue()));
                                b.setUnit(a.getUnit());
                                break;
                            case "kg":
                                b.setValue(MetricConverter.convertKilogramToMilligram(b.getValue()));
                                b.setUnit(a.getUnit());
                                break;
                        }
                        break;

                    case "g":
                        switch (b.getUnit()) {
                            case "mcg":
                                a.setValue(MetricConverter.convertGramToMicrogram(a.getValue()));
                                a.setUnit(b.getUnit());
                                break;
                            case "mg":
                                a.setValue(MetricConverter.convertGramToMilligram(a.getValue()));
                                a.setUnit(b.getUnit());
                                break;
                            case "kg":
                                b.setValue(MetricConverter.convertKilogramToGram(b.getValue()));
                                b.setUnit(a.getUnit());
                                break;
                        }
                        break;

                    case "kg":
                        switch (b.getUnit()) {
                            case "mcg":
                                a.setValue(MetricConverter.convertKilogramToMicrogram(a.getValue()));
                                a.setUnit(b.getUnit());
                                break;
                            case "mg":
                                a.setValue(MetricConverter.convertKilogramToMilligram(a.getValue()));
                                a.setUnit(b.getUnit());
                                break;
                            case "g":
                                a.setValue(MetricConverter.convertKilogramToGram(a.getValue()));
                                a.setUnit(b.getUnit());
                                break;
                        }

                    case "mcL":
                        switch (b.getUnit()) {
                            case "mL":
                                b.setValue(MetricConverter.convertMillilitreToMicrolitre(b.getValue()));
                                b.setUnit(a.getUnit());
                                break;
                            case "L":
                                b.setValue(MetricConverter.convertLitreToMicrolitre(b.getValue()));
                                b.setUnit(a.getUnit());
                                break;
                        }
                        break;

                    case "mL":
                        switch (b.getUnit()) {
                            case "mcL":
                                a.setValue(MetricConverter.convertMillilitreToMicrolitre(a.getValue()));
                                a.setUnit(b.getUnit());
                                break;
                            case "L":
                                b.setValue(MetricConverter.convertLitreToMillilitre(b.getValue()));
                                b.setUnit(a.getUnit());
                                break;
                        }
                        break;
                    case "L":
                        switch (b.getUnit()) {
                            case "mcL":
                                a.setValue(MetricConverter.convertLitreToMicrolitre(a.getValue()));
                                a.setUnit(b.getUnit());
                                break;
                            case "mL":
                                a.setValue(MetricConverter.convertLitreToMillilitre(a.getValue()));
                                a.setUnit(b.getUnit());
                                break;
                        }
                        break;
                }
            } else {

            }

        }
    }

    public static double roundThreeDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.###");
        return Double.valueOf(twoDForm.format(d));
    }

}
