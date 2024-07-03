/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ramsoware.classes;

/**
 *
 * @author jhoan
 */
public class clsData {
    
    String name;
    String id;
    String adress;
    String data;
    String code;

    public clsData() {
    }

    public clsData(String name, String id, String adress, String data, String code) {
        this.name = name;
        this.id = id;
        this.adress = adress;
        this.data = data;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
    
}
