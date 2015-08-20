/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_retriever;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import server.pharma_red_v2.sqlsvr_copy.facade.ItemCategoryCFacade;
import server.pharma_red_v2.sqlsvr_copy.facade.ItemFacade;
import server.pharma_red_v2.sqlsvr_copy.facade.ItemTypeCFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("filereader")
@RequestScoped
public class ExcelReader {

    @Context
    private UriInfo context;

    @Inject
    ItemCategoryCFacade categoryFacade;

    @Inject
    ItemTypeCFacade typeFacade;

    @Inject
    ItemFacade itemFacade;

    public ExcelReader() {
    }

    @GET
    @Path("/read_rm")
    @Produces("application/json")
    public List< server.pharma_red_v2.sqlsvr_copy.entity.Item> readExcelFile() {
        // TODO code application logic here
        List<Item> listOfItems = new ArrayList();
        List< server.pharma_red_v2.sqlsvr_copy.entity.Item> items = new ArrayList();
        try {

            //Create a workbook object from the file at specified location.
            //Change the path of the file as per the location on your computer. ;
            Workbook wrk1 = Workbook.getWorkbook(new File("F:\\excel\\updated list of RM, PM (8-5-15).xls"));

            //Obtain the reference to the first sheet in the workbook
            Sheet sheet1 = wrk1.getSheet(0);

            int descriptionColumn = 1;
            int uomColumn = 2;
            int codeColumn = 3;

            for (int i = 1; i <= 343; i++) {

                String description = sheet1.getCell(descriptionColumn, i).getContents();
                String uom = sheet1.getCell(uomColumn, i).getContents();
                String code = sheet1.getCell(codeColumn, i).getContents();

                Item item = new Item();
                item.setDescs(description);
                item.setUom(uom);
                item.setItemCd(code);

                listOfItems.add(item);

            }

            for (Item i : listOfItems) {
                System.out.println(i.getItemCd() + " " + i.getDescs() + " " + i.getUom());
                server.pharma_red_v2.sqlsvr_copy.entity.Item item2 = new server.pharma_red_v2.sqlsvr_copy.entity.Item();

                item2.setDescs(i.getDescs());
                item2.setItemCd(i.getItemCd());
                item2.setItemCategoryId(categoryFacade.findByCode("RM"));
                item2.setItemTypeId(typeFacade.findByCode("DM"));
                items.add(item2);

                //for inserting new values in the database
                //itemFacade.insert(item2);
            }
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return items;
    }

    @GET
    @Path("/read_pm")
    @Produces("application/json")
    public List< server.pharma_red_v2.sqlsvr_copy.entity.Item> readPackgMaterial() {
        // TODO code application logic here
        List<Item> listOfItems = new ArrayList();
        List< server.pharma_red_v2.sqlsvr_copy.entity.Item> items = new ArrayList();
        try {

            //Create a workbook object from the file at specified location.
            //Change the path of the file as per the location on your computer. ;
            Workbook wrk1 = Workbook.getWorkbook(new File("F:\\excel\\updated list of RM, PM (8-5-15).xls"));

            //Obtain the reference to the first sheet in the workbook
            Sheet sheet1 = wrk1.getSheet(1);

            int descriptionColumn = 1;
            int uomColumn = 2;
            int codeColumn = 3;

            for (int i = 1; i < 386; i++) {

                String description = sheet1.getCell(descriptionColumn, i).getContents();
                String uom = sheet1.getCell(uomColumn, i).getContents();
                String code = sheet1.getCell(codeColumn, i).getContents();

                Item item = new Item();
                item.setDescs(description);
                item.setUom(uom);
                item.setItemCd(code);

                listOfItems.add(item);

            }

            for (Item i : listOfItems) {
                System.out.println(i.getItemCd() + " " + i.getDescs() + " " + i.getUom());
                server.pharma_red_v2.sqlsvr_copy.entity.Item item2 = new server.pharma_red_v2.sqlsvr_copy.entity.Item();

                item2.setDescs(i.getDescs());
                item2.setItemCd(i.getItemCd());
                item2.setItemCategoryId(categoryFacade.findByCode("PM"));
                item2.setItemTypeId(typeFacade.findByCode("DM"));
                items.add(item2);

                //for inserting new values in the database
                //itemFacade.insert(item2);
            }
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return items;
    }

}
