/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.utils;

/**
 *
 * @author maine
 */
 public class Quantity {

        double value;
        String unit;

        public Quantity() {
        }
        public Quantity(Quantity q){
            super();
            this.unit = q.getUnit();
            this.value = q.getValue();
        }
        public Quantity(double value, String unit) {
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
        
        @Override
        public String toString(){
            return value + " " + unit;
        }

    }