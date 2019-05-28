package tui;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a description of class TempUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TempUI
{
    private HashMap<String,String> example= new HashMap<String,String>();
    private ArrayList list = new ArrayList();
    
    
    /**
     * Constructor for objects of class TempUI
     */
    public TempUI()
    {
        example.put("start","Jens");
        example.put("text","no");
        list.add(example); 
        
        for (String name: this.example.keySet())
        {
            String key = name.toString();
            String value = example.get(name).toString();  
            System.out.println(key + " " + value);  
        } 

        /*
        
        System.out.println(list.);
        */
        /*for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }*/
    }

}
