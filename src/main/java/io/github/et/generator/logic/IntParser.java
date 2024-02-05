package io.github.et.generator.logic;

public class IntParser {
    public static boolean condition(String a){
        try{
            int m=Integer.parseInt(a);
            return (m>=10&&m<=999999);
        }catch (Exception e){
            return false;
        }
    }
}
