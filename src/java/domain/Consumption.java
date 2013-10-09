/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Jeff
 */
public class Consumption {
    //Dit kan beter uit xml worden getrokken!
    private long id;
    private String name;
    private String description;
    //ENUM DAT CRAP!
    private ConsumptionType consumType;
    private double price;
    private boolean empty;
    
    public enum ConsumptionType {
    DRINK, ENTREE, MAIN, DESSERT, SNACK;
    }       
    public Consumption(){
        
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEmpty() {
        return empty;
    }
            
    public double getPrice(){
        return price;
    }
}
