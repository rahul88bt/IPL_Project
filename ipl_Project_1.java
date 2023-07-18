package iplProject;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Ipl_Pro {
    public static void main(String[] args){
        try{
            BufferedReader br=new BufferedReader(new FileReader("matches.csv"));
            boolean isfirst=true;
            String l="";
            HashMap<String,Integer> mp=new HashMap<>();
            int slevel=0;
            while((l=br.readLine())!=null){
                if(isfirst) {
                    String arr[]=l.split(",");
                    for(int i=0;i<arr.length;i++){
                        if(arr[i].equals("season")){
                            slevel=i;
                            break;
                        }
                    }
                    isfirst = false;
                    continue;
                }
                String arr[]=l.split(",");
                String val=arr[slevel];
                if(mp.containsKey(val)){
                    int count=mp.get(val);
                    mp.put(val,++count);
                }
                else {
                    mp.put(val, 1);
                }
            }
            System.out.println(mp);
            br.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

