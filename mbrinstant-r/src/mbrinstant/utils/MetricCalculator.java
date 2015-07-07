/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.utils;

import java.text.DecimalFormat;

/**
 *
 * @author maine
 */
public class MetricCalculator {

   

    public MetricCalculator() {

    }

    public Quantity add(Quantity a, Quantity b) {
        Quantity q = new Quantity();
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
            }

        }
        
        double sum = a.getValue() + b.getValue();
        q.setValue(sum);
        q.setUnit(a.getUnit());
        return q;
    }

     public static double roundThreeDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.###");
        return Double.valueOf(twoDForm.format(d));
    }
   

}
