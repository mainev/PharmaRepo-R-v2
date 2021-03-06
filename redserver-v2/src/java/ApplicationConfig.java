/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(data_retriever.ExcelReader.class);
        resources.add(filter.AuthenticatorREST.class);
        resources.add(filter.ServerInterceptor.class);
        resources.add(server.pharma_red_v2._main.rest.AreaREST.class);
        resources.add(server.pharma_red_v2._main.rest.ClassificationREST.class);
        resources.add(server.pharma_red_v2._main.rest.ContainerREST.class);
        resources.add(server.pharma_red_v2._main.rest.EquipmentREST.class);
        resources.add(server.pharma_red_v2._main.rest.PackSizeREST.class);
        resources.add(server.pharma_red_v2._main.rest.ProductREST.class);
        resources.add(server.pharma_red_v2._main.rest.UnitREST.class);
        resources.add(server.pharma_red_v2.audit.AuditREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.BatchItemRequirementREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.BottlingProcedureREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.CompoundingProcedureREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.DosageREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.EquipmentRequirementREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.ManufacturingProcedureREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.MbrREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.PackagingMaterialRequirementREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.PackagingProcedureREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.PowderFillingREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.RawMaterialRequirementREST.class);
        resources.add(server.pharma_red_v2.mbr.rest.UdfREST.class);
        resources.add(server.pharma_red_v2.security.rest.UserREST.class);
        resources.add(server.pharma_red_v2.sqlsvr_copy.rest.CompanyREST.class);
        resources.add(server.pharma_red_v2.sqlsvr_copy.rest.ItemCategoryCREST.class);
        resources.add(server.pharma_red_v2.sqlsvr_copy.rest.ItemClassCREST.class);
        resources.add(server.pharma_red_v2.sqlsvr_copy.rest.ItemREST.class);
        resources.add(server.pharma_red_v2.sqlsvr_copy.rest.ItemTypeCREST.class);
        resources.add(server.pharma_red_v2.sqlsvr_copy.rest.StockCardCREST.class);
        resources.add(server.pharma_red_v2.sqlsvr_copy.rest.WarehouseCREST.class);
        resources.add(server.pharma_red_v2.transaction.rest.StockCardTxnREST.class);
    }

}
