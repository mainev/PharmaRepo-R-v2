/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;

/**
 *
 * @author maine
 */
public class ProductLogListener {
    @PostPersist
    private void log(Object object) {
        System.out.println("object created "+object);
    }
    
    @PostLoad
    private void onLoad(Object object){
        System.out.println("loading object "+object);
    }
}
