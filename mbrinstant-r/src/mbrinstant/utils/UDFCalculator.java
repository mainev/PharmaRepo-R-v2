/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.utils;

import java.text.DecimalFormat;
import java.util.List;
import mbrinstant.entity.main.Product;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.mbr.PackagingMaterialRequirement;
import mbrinstant.entity.mbr.RawMaterialRequirement;

public class UDFCalculator {

    double newQty;
    String newUnit;

    public void calculateRawMaterialBatchReq(Mbr mbr) {
        double multiplier = getUdfMultiplier(mbr);
        Product product = mbr.getProductId();
        List<RawMaterialRequirement> list = product.getActiveUdf().getRawMaterialRequirementList();

        for (RawMaterialRequirement rmReq : list) {
            double oldQty = rmReq.getQuantity();
            String oldUnit = rmReq.getUnitId().getName();

            newQty = (oldQty * multiplier);
            newUnit = oldUnit;
            rawMaterialQuantityAndUnitConverter();
            rmReq.setNewQuantity(roundThreeDecimals(newQty));
            rmReq.setNewUnit(newUnit);
        }
    }

    public void calculatePackMatBatchReq(Mbr mbr) {
        double batchSize = mbr.getBatchSize();
        List<PackagingMaterialRequirement> packagingMaterialReqCollection = mbr.getProductId().getActiveUdf().getPackagingMaterialRequirementList();

        for (PackagingMaterialRequirement pmr : packagingMaterialReqCollection) {
            double quantity = pmr.getQuantity() * batchSize;
            pmr.setNewQuantity(quantity);
        }
    }

    /**
     * *
     * This multiplier is only applicable for the following units:
     * <p>
     * @Batch size unit : kg, g, L, mL
     * <p>
     * Udf content unit : mcg, mg, g, mcL, mL
     *
     * @param mbr
     * @return <code>MBR</code>
     */
    public double getUdfMultiplier(Mbr mbr) {
        Product product = mbr.getProductId();
        double batchSize = mbr.getBatchSize();
        String batchSizeUnit = mbr.getUnitId().getName();
        double udfContent = product.getActiveUdf().getContent();
        String udfContentUnit = product.getActiveUdf().getUnitId().getName();
        double multiplier = 1;

        if (!batchSizeUnit.equals(udfContentUnit)) {
            switch (batchSizeUnit) {
                case "kg":
                    if (udfContentUnit.equals("g")) {
                        multiplier = (MetricConverter.convertKilogramToGram(batchSize) / udfContent);
                    } else if (udfContentUnit.equals("mcg")) {
                        multiplier = (MetricConverter.convertKilogramToMicrogram(batchSize) / udfContent);
                    } else if (udfContentUnit.equals("mg")) {
                        multiplier = (MetricConverter.convertKilogramToMilligram(batchSize) / udfContent);
                    }
                    break;
                case "g":
                    if (udfContentUnit.equals("mg")) {
                        multiplier = (MetricConverter.convertGramToMilligram(batchSize) / udfContent);
                    } else if (udfContentUnit.equals("mcg")) {
                        multiplier = (MetricConverter.convertGramToMicrogram(batchSize) / udfContent);
                    }
                    break;
                //add another case here for weight unit

                case "L":
                    if (udfContentUnit.equals("mL")) {
                        multiplier = (MetricConverter.convertLitreToMillilitre(batchSize) / udfContent);
                    } else if (udfContentUnit.equals("mcL")) {
                        multiplier = (MetricConverter.convertLitreToMicrolitre(batchSize) / udfContent);
                    }
                    break;
                case "mL":
                    if (udfContentUnit.equals("mcL")) {
                        multiplier = (MetricConverter.convertMillilitreToMicrolitre(batchSize) / udfContent);
                    }

                default:
                    break;
            }
        } else {
            multiplier = batchSize / udfContent;
        }

        return multiplier;
    }

    public void rawMaterialQuantityAndUnitConverter() {
        if (newUnit != "L" || newUnit != "kg") {
            if (newQty >= 1e3 && newQty < 1e6) {
                switch (newUnit) {
                    case "mcL":
                        newQty = MetricConverter.convertMicrolitreToMillilitre(newQty);
                        newUnit = "mL";
                        break;
                    case "mL":
                        newQty = MetricConverter.convertMillilitreToLitre(newQty);
                        newUnit = "L";
                        break;
                    case "mcg":
                        newQty = MetricConverter.convertMicrogramToMilligram(newQty);
                        newUnit = "mg";
                        break;
                    case "mg":
                        newQty = MetricConverter.convertMilligramToGram(newQty);
                        newUnit = "g";
                        break;
                    case "g":
                        newQty = MetricConverter.convertGramToKilogram(newQty);
                        newUnit = "kg";
                        break;
                }
            } else if (newQty >= 1e6 && newQty < 1e9) {
                switch (newUnit) {
                    case "mcL":
                        newQty = MetricConverter.convertMicrolitreToLitre(newQty);
                        newUnit = "L";
                        break;
                    case "mL":
                        newQty = MetricConverter.convertMillilitreToLitre(newQty);
                        newUnit = "L";
                        break;
                    case "mcg":
                        newQty = MetricConverter.convertMicrogramToGram(newQty);
                        newUnit = "g";
                        break;
                    case "mg":
                        newQty = MetricConverter.convertMilligramToKilogram(newQty);
                        newUnit = "kg";
                        break;
                    case "g":
                        newQty = MetricConverter.convertGramToKilogram(newQty);
                        newUnit = "kg";
                        break;
                }
            } else if (newQty >= 1e9) {
                switch (newUnit) {
                    case "mcL":
                        newQty = MetricConverter.convertMicrolitreToLitre(newQty);
                        newUnit = "L";
                        break;
                    case "mL":
                        newQty = MetricConverter.convertMillilitreToLitre(newQty);
                        newUnit = "L";
                        break;
                    case "mcg":
                        newQty = MetricConverter.convertMicrogramToKilogram(newQty);
                        newUnit = "kg";
                        break;
                    case "mg":
                        newQty = MetricConverter.convertMilligramToKilogram(newQty);
                        newUnit = "kg";
                        break;
                    case "g":
                        newQty = MetricConverter.convertGramToKilogram(newQty);
                        newUnit = "kg";
                        break;
                }
            }

        }//

    }
//

    public static double roundThreeDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.###");
        return Double.valueOf(twoDForm.format(d));
    }

    public static double roundZeroDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#");
        return Double.valueOf(twoDForm.format(d));
    }
}
