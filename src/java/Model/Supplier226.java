/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Tuan Diep
 */
public class Supplier226 {
    private int  id;
    private String name;
    private String phone;
    private String email;
    private int idWarehouser;

    public Supplier226(int id, String name, String phone, String email, int idWarehouser) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.idWarehouser = idWarehouser;
    }

    public Supplier226() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdWarehouser() {
        return idWarehouser;
    }

    public void setIdWarehouser(int idWarehouser) {
        this.idWarehouser = idWarehouser;
    }
    
}
