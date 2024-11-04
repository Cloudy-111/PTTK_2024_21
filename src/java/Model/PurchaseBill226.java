/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Tuan Diep
 */
public class PurchaseBill226 extends Bill226{
    private int idSupplier;
    private int idWarehouser;

    public PurchaseBill226() {
    }

    public PurchaseBill226(int idSupplier, int idWarehouser) {
        this.idSupplier = idSupplier;
        this.idWarehouser = idWarehouser;
    }


    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public int getIdWarehouser() {
        return idWarehouser;
    }

    public void setIdWarehouser(int idWarehouser) {
        this.idWarehouser = idWarehouser;
    }
    
    
}
