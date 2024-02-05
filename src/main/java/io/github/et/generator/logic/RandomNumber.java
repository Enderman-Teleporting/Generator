package io.github.et.generator.logic;

import java.util.ArrayList;
import java.util.Random;


public class RandomNumber {
    public int max;
    public int up;
    public ArrayList<Integer> al= new ArrayList<>();
    public ArrayList<Integer> purple= new ArrayList<>();
    public ArrayList<Integer> gold= new ArrayList<>();
    public ArrayList<Integer> overPurple= new ArrayList<>();
    public static int lastOverPurple=1;
    public static int lastGold=1;
    public static boolean lastUp=true;

    public RandomNumber(int max){
        this.max=max;
        Random random=new Random();
        int a;
        up=random.nextInt(1,max+1);
        for (int i=1;i<=3;i++){
            do{
                a=random.nextInt(1,max+1);
            }while(up==a||al.contains(a));
            al.add(a);
        }
        for (int i=1;i<=3;i++){
            do{
                a=random.nextInt(1,max+1);
            }while(up==a||al.contains(a)||purple.contains(a));
            purple.add(a);
        }
        gold.add(up);
        gold.addAll(al);
        overPurple.addAll(gold);
        overPurple.addAll(purple);
    }
    public int getUp() {return up;}

    public ArrayList<Integer> getAl() {return al;}
    public ArrayList<Integer> getPurple() {return purple;}
    public ArrayList<Integer> getGold() {return gold;}

    public int randomBetween() {
        Random random=new Random();
        if(random.nextInt(1,1001)<=getGoldPossibility()){
            if(!lastUp){
                lastUp=true;
                lastGold=1;
                lastOverPurple=1;
                return up;
            }else{
                int a=random.nextInt(0,2);
                if(a==0){
                    lastUp=true;
                    lastGold=1;
                    lastOverPurple=1;
                    return up;
                }else{
                    a=random.nextInt(0,3);
                    lastUp=false;
                    lastGold=1;
                    lastOverPurple=1;
                    return al.get(a);
                }
            }
        } else if (random.nextInt(1,1001)<=getPurplePossibility()) {
            int a=random.nextInt(0,3);
            lastGold++;
            lastOverPurple=1;
            return purple.get(a);

        } else {
            int a;
            do{
                a=random.nextInt(1,max+1);
            } while(overPurple.contains(a));
            lastGold++;
            lastOverPurple++;
            return a;
        }
    }
    public ArrayList<Integer> RandBetween10(){
        ArrayList<Integer> a = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            a.add(this.randomBetween());
        }
        return a;
    }

    public boolean containsGold(ArrayList<Integer> integers){
        for (int i:integers){
            if(gold.contains(i)){
                return true;
            }
        }
        return false;
    }
    public ArrayList<Integer> sort(ArrayList<Integer> original){
        boolean swap=false;
        ArrayList<Integer> latest=original;
        while(true) {
            for (int i =1; i < original.size(); i++) {
                if((gold.contains(original.get(i))&&(!gold.contains(original.get(i-1))))||(purple.contains(original.get(i))&&(!overPurple.contains(original.get(i-1))))){
                    int a=latest.get(i);
                    latest.set(i,original.get(i-1));
                    latest.set(i-1,a);
                    swap=true;
                }
            }
            if(!swap){
                break;
            }else{
                swap=false;
            }
        }
        return  latest;
    }

    private int getGoldPossibility(){
        if(lastGold<=72){
            return 6;
        }else{
            return 6+60*(lastGold-72);
        }
    }

    private int getPurplePossibility(){
        if(lastOverPurple<10){
            return 51;
        }else{
            return 1000;
        }
    }


}
