package iplProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Ipl_Pro4 {
    public static void main(String[] args){
        try{
            BufferedReader br=new BufferedReader(new FileReader("matches.csv"));
            String l="";
            int start=0;
            int end=0;
            boolean isfirst=true;
            boolean b=true;
            int idlevel=0;
            int slevel=0;
            while((l=br.readLine())!=null){
                if(isfirst){
                    String arr[]=l.split(",");
                    for(int i=0;i<arr.length;i++){
                        if(arr[i].equals("id")){
                            idlevel=i;
                        }
                        if(arr[i].equals("season")){
                            slevel=i;
                        }
                    }
                    isfirst=false;
                    continue;
                }
                String arr[]=l.split(",");
                if(arr[slevel].equals("2015") && b==true){
                    start=Integer.parseInt(arr[idlevel]);
                    b=false;
                }
                if(arr[slevel].equals("2015")){
                    end=Integer.parseInt(arr[idlevel]);
                }
            }
            BufferedReader br2=new BufferedReader(new FileReader("deliveries.csv"));
            HashMap<String,Integer> mp=new HashMap<String,Integer>();
            boolean isfirst2=true;
            String l2="";
            int bindex=0;
            int ballindex=0;
            int trun=0;
            while((l2=br2.readLine())!=null){
                if(isfirst2){
                    String arr[]=l2.split(",");
                    for(int i=0;i<arr.length;i++){
                        if(arr[i].equals("ball")){
                            ballindex=i;
                        }
                        if(arr[i].equals("bowler")){
                            bindex=i;
                        }
                        if(arr[i].equals("total_runs")){
                            trun=i;
                        }
                    }
                    isfirst2=false;
                    continue;
                }
                String arr[]=l2.split(",");
                if(start<=Integer.parseInt(arr[idlevel]) && Integer.parseInt(arr[idlevel])<=end) {
                    if (mp.containsKey(arr[bindex])) {
                        int count = mp.get(arr[bindex]);
                        mp.put(arr[bindex],count+(Integer.parseInt(arr[trun])));
                    }
                    else{
                        mp.put(arr[bindex],Integer.parseInt(arr[trun]));
                    }
                }
            }
            BufferedReader br3=new BufferedReader(new FileReader("deliveries.csv"));
            HashMap<String,Integer> mp2=new HashMap<>();
            String l3="";
            boolean isfirst3=true;
            while((l3=br3.readLine())!=null){
                if(isfirst3){
                    isfirst3=false;
                    continue;
                }
                String arr[]=l3.split(",");
                if(start<=Integer.parseInt(arr[idlevel]) && Integer.parseInt(arr[idlevel])<=end) {
                    if (mp2.containsKey(arr[bindex])) {
                        int count = mp2.get(arr[bindex]);
                        if(Integer.parseInt(arr[ballindex])<=6) {
                            mp2.put(arr[bindex], ++count);
                        }
                    }
                    else{
                        mp2.put(arr[bindex],1);
                    }
                }
            }
            float a[]=new float[mp.size()];
            int len=0;
            HashMap<String,Float> mp3=new HashMap<>();
            for(String str:mp2.keySet()){
                    int val=mp2.get(str);
                    float f=val;
                    f/=6;
                    if(mp.containsKey(str)){
                        int count=mp.get(str);
                        mp3.put(str,count/f);
                        a[len++]=count/f;
                    }
            }
            Arrays.sort(a);
            for(int i=0;i<10;i++){
                for(Map.Entry<String, Float> entry:mp3.entrySet()) {
                    if (entry.getValue() == a[i]) {
                        System.out.println(entry.getKey() + " " + a[i]);
                        break;
                    }
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

