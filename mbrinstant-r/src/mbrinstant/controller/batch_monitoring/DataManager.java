/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.batch_monitoring;

import mbrinstant.exceptions.ServerException;

/**
 *
 * @author maine
 */
public interface DataManager {

    public void setData(Object object);

    public Object getData();

    /**
     * Reloads data from the server.
     *
     * @throws ServerException
     */
    public void reloadData() throws ServerException;
}
