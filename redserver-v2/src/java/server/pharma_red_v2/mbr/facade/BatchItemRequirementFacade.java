/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.facade;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.pharma_red_v2.ItemReqStatusEnum;
import server.pharma_red_v2.mbr.entity.BatchItemRequirement;
import server.pharma_red_v2.mbr.entity.Mbr;

/**
 *
 * @author maine
 */
@Stateless
public class BatchItemRequirementFacade {

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    @EJB
    private MbrFacade batchFacade;

    public List<BatchItemRequirement> findByBatchId(int batchId) {
        return em.createQuery("Select b from BatchItemRequirement b where b.batchId.id = :batchId")
                .setParameter("batchId", batchId)
                .getResultList();
    }

    public BatchItemRequirement findById(int id) {

        return (BatchItemRequirement) em.createQuery("Select b from BatchItemRequirement b where b.id = :id")
                .setParameter("id", id)
                .getSingleResult();

    }

    public void updateItemReqStatus(int batchItemReqId, String stat) {
        BatchItemRequirement batchItemReq = this.findById(batchItemReqId);
        batchItemReq.setItemReqStatus(ItemReqStatusEnum.valueOf(stat));
    }

    public BatchItemRequirement insert(int batchId, BatchItemRequirement batchItemReq) {
        Mbr batch = batchFacade.findById(batchId);
        batchItemReq.setBatchId(batch);
        em.persist(batchItemReq);
        em.flush();
        return em.find(BatchItemRequirement.class, batchItemReq.getId());
    }
}
