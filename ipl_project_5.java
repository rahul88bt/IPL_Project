package iplProject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class Ipl_Pro_OwnScenario {
    public static void main(String[] args){
        try{
            BufferedReader br=new BufferedReader(new FileReader("matches.csv"));
            HashMap<String,Integer> mp=new HashMap<>();
            String l="";
            boolean isfirst=true;
            int sindex=0;
            int pindex=0;
            while((l=br.readLine())!=null){
                if(isfirst){
                    String arr[]=l.split(",");
                    for(int i=0;i<arr.length;i++){
                        if(arr[i].equals("season")){
                            sindex=i;
                        }
                        if(arr[i].equals("player_of_match")){
                            pindex=i;
                        }
                    }
                    isfirst=false;
                    continue;
                }
                String arr[]=l.split(",");
                if(arr[sindex].equals("2017") || arr[sindex].equals("2016") || arr[sindex].equals("2015")){
                    if(mp.containsKey(arr[pindex])){
                        int count=mp.get(arr[pindex]);
                        mp.put(arr[pindex],++count);
                    }
                    else{
                        mp.put(arr[pindex],1);
                    }
                }
            }
            System.out.println(mp);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

