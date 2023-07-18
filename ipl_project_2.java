package iplProject;

import java.io.*;
import java.util.HashMap;
public class Ipl_Pro_2q {
    public static void main(String[] args){
        try{
            File file=new File("matches.csv");
            BufferedReader br=new BufferedReader(new FileReader("matches.csv"));
            HashMap<String,Integer> mp=new HashMap<String,Integer>();
            int wlevel=0;
            boolean isfirst=true;
            String l="";
            while((l=br.readLine())!=null){
                if(isfirst){
                    String arr[]=l.split(",");
                    for(int i=0;i<arr.length;i++){
                        if(arr[i].equals("winner")) {
                            wlevel = i;
                            break;
                        }
                    }
                    isfirst=false;
                    continue;
                }
                String arr[]=l.split(",");
                String val=arr[wlevel];
                if(mp.containsKey(val)){
                    int count=mp.get(val);
                    mp.put(val,++count);
                }
                else{
                    mp.put(val,1);
                }
            }
            System.out.println(mp);
            br.close();
        }
        catch (IOException e) {
            System.out.println("IOException occur");
            e.printStackTrace();
        }
    }
}

