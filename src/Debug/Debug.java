package Debug;

import java.util.*;

public class Debug{
    static Date d;
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";

    private static boolean isEnabled = true;

    public static boolean isEnabled(){
        return isEnabled;
        
    }

    public static void log(Object o){
        if (Debug.isEnabled()){
            updateDate();
            System.out.println(ANSI_GREEN+d.toString() + " [DEBUG_LOG] "+o.toString()+ANSI_RESET);
        }
    }

    public static void log(){
        if (Debug.isEnabled()){
            updateDate();
            System.out.println(ANSI_GREEN+d.toString()+" [DEBUG_LOG] "+ANSI_RESET);
        }
    }

    public static void warn(Object o){
        if (Debug.isEnabled()){
            updateDate();
            System.out.println(ANSI_YELLOW+d.toString() + " [DEBUG_WARN] "+o.toString()+ANSI_RESET);
        }
    }

    public static void warn(){
        if (Debug.isEnabled()){
            updateDate();
            System.out.println(ANSI_YELLOW+d.toString() + " [DEBUG_WARN] "+ANSI_RESET);
        }
    }

    public static void err(Object o){
        if (Debug.isEnabled()){
            updateDate();
            System.out.println(ANSI_RED+d.toString() + " [DEBUG_ERR] "+o.toString()+ANSI_RESET);
        }
    }

    public static void err(){
        if (Debug.isEnabled()){
            updateDate();
            System.out.println(ANSI_RED+d.toString() + " [DEBUG_ERR] "+ANSI_RESET);
        }
    }

    private static void updateDate(){
        d = new Date();
    }

    public static void setEnabled(boolean isEnabled){
        Debug.isEnabled = isEnabled;
    }




}