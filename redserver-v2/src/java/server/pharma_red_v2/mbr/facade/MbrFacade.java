/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.pharma_red_v2.mbr.entity.Mbr;
import server.pharma_red_v2.mbr.entity.MbrStatus;
import server.pharma_red_v2.transaction.entity.StockCardTxn;

/**
 *
 * @author maine
 */
@Stateless
public class MbrFacade {

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public List<Mbr> findAll() {
        return em.createQuery("SELECT m from Mbr m order by m.mfgDate asc").getResultList();
    }

    public List<Mbr> findByStatus(String status) {

        return em.createQuery("SELECT m from Mbr m where m.status = :stat order by m.mfgDate asc")
                .setParameter("stat", status)
                .getResultList();
    }

    public List<Mbr> findByBatchNo(String batchNo) {

        return em.createQuery("SELECT m from Mbr m where m.batchNo = :batchNo order by m.mfgDate asc")
                .setParameter("batchNo", batchNo)
                .getResultList();
    }

    public List<Mbr> findByProductCode(String productCode) {
        return em.createQuery("SELECT m from Mbr m where m.productId.code = :productCode order by m.mfgDate asc")
                .setParameter("productCode", productCode)
                .getResultList();
    }

    public List<Mbr> findByProductArea(String productArea) {
        return em.createQuery("SELECT m from Mbr m where m.productId.areaId.name = :productArea order by m.mfgDate asc")
                .setParameter("productArea", productArea)
                .getResultList();
    }

    public Mbr create(Mbr mbr) {
        em.persist(mbr);
        em.flush();
        return em.find(Mbr.class, mbr.getId());
    }

    public Mbr findById(int id) {
        return em.find(Mbr.class, id);
    }

    public void reserveMbr(int mbrId) {
        Mbr mbr = this.findById(mbrId);
        mbr.setStatus(MbrStatus.RESERVED.toString());
    }

    public void cancelReservation(int mbrId) {
        Mbr mbr = findById(mbrId);
        if (mbr != null) {
            try {
                mbr.setStatus(MbrStatus.PENDING.toString());
                for (StockCardTxn txn : mbr.getStockCardTxnList()) {
                    txn.getStockCardId().setStockStatus("AVAILABLE");
                    em.remove(txn);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void releaseMbr(int mbrId) {
        Mbr mbr = findById(mbrId);
        if (mbr != null) {
            mbr.setStatus(MbrStatus.PRINTED.toString());
        }
    }

    public void dispenseMbrMaterials(int mbrId) {
        Mbr mbr = findById(mbrId);
        if (mbr != null) {
            mbr.setStatus(MbrStatus.DISPENSED.toString());
        }
    }
}
