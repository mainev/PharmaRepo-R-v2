/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.utils;

/**
 *
 * @author Admin
 */
public abstract class MetricConverter {
  
    
    //weight conversion
    public static double convertMicrogramToMilligram(double weight){
        return (weight / (double)1e3);
    }
    public static double convertMicrogramToGram(double weight){
        return (weight / (double)1e6);
    }
    public static double convertMicrogramToKilogram(double weight){
        return (weight / (double)1e9);
    }
    public static double convertMilligramToGram(double weight){
        return (weight / (double)1e3);
    }
    public static double convertMilligramToKilogram(double weight){
        return (weight / (double)1e6);
    }
    public static double convertGramToKilogram(double weight){
        return (weight / (double)1e3);
    }
    public static double convertKilogramToGram(double weight){
        return (weight * (double)1e3);
    }
    public static double convertKilogramToMilligram(double weight){
        return (weight * (double)1e6);
    }
    public static double convertKilogramToMicrogram(double weight){
        return (weight * (double)1e9);
    }
    public static double convertGramToMilligram(double weight){
        return (weight * (double)1e3);
    }
    public static double convertGramToMicrogram(double weight){
        return (weight * (double)1e6);
    }
    public static double convertMilligramToMicrogram(double weight){
        return (weight * (double)1e3);
    }
    
    //volume conversion
    public static double convertMicrolitreToMillilitre(double volume){
        return (volume / (double)1e3);
    }
    public static double convertMicrolitreToLitre(double volume){
        return (volume / (double)1e6);
    }
    public static double convertMillilitreToLitre(double volume){
        return (volume / (double)1e3);
    }
    public static double convertLitreToMillilitre(double volume){
        return (volume * (double)1e3);
    }
    public static double convertLitreToMicrolitre(double volume){
        return (volume * (double)1e6);
    }
    public static double convertMillilitreToMicrolitre(double volume){
        return (volume * (double)1e3);
    }
    
}
