/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author maine
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(server.pharma_red_v2._main.rest.AreaREST.class);
        resources.add(server.pharma_red_v2._main.rest.ClassificationREST.class);
        resources.add(server.pharma_red_v2._main.rest.ClientREST.class);
        resources.add(server.pharma_red_v2._main.rest.ContainerREST.class);
        resources.add(server.pharma_red_v2._main.rest.EquipmentREST.class);
        resources.add(server.pharma_red_v2._main.rest.PackSizeREST.class);
        resources.add(server.pharma_red_v2._main.rest.PackagingMaterialREST.class);
        resources.add(server.pharma_red_v2._main.rest.ProductREST.class);
        resources.add(server.pharma_red_v2._main.rest.RawMaterialREST.class);
        resources.add(server.pharma_red_v2._main.rest.UnitREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.BottlingProcedureREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.CompoundingProcedureREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.DosageREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.EquipmentRequirementREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.ManufacturingProcedureREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.MbrREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.PackagingMaterialRequirementREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.PackagingOperationREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.PowderFillingREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.RawMaterialRequirementREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.UdfREST.class);
        resources.add(server.sample.User2REST.class);
        resources.add(server.sqlsvr.Nutratech_DB.rest.ItemREST.class);
        resources.add(server.sqlsvr.Nutratech_DB.rest.StockCardREST.class);
    }
    
}
