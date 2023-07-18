package iplProject;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Ipl_Pro3 {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("matches.csv"));
            String l = "";
            boolean isfirst = true;
            int start = 0;
            int end = 0;
            int idlevel = 0;
            int slevel = 0;
            boolean b = true;
            while ((l = br.readLine()) != null) {
                if (isfirst) {
                    String arr[] = l.split(",");
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i].equals("id")) {
                            idlevel = i;
                        }
                        if (arr[i].equals("season")) {
                            slevel = i;
                        }
                    }
                    isfirst = false;
                    continue;
                }
                String arr[] = l.split(",");
                if (arr[slevel].equals("2016") && b == true) {
                    start = Integer.parseInt(arr[idlevel]);
                    b = false;
                }
                if (arr[slevel].equals("2016")) {
                    end = Integer.parseInt(arr[idlevel]);
                }
            }

            BufferedReader br1 = new BufferedReader(new FileReader("deliveries.csv"));
            String l1 = "";
            int elevel = 0;
            boolean isfirst2 = true;
            HashMap<String, Integer> mp2 = new HashMap<>();
            while ((l1 = br1.readLine()) != null) {
                if (isfirst2) {
                    String arr[] = l1.split(",");
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i].equals("extra_runs")) {
                            elevel = i;
                            break;
                        }
                    }
                    isfirst2 = false;
                    continue;
                }
                String arr[] = l1.split(",");
                if (start <= (Integer.parseInt(arr[idlevel])) && (Integer.parseInt(arr[idlevel])) <= end) {
                    if (mp2.containsKey(arr[2])) {
                        int count = mp2.get(arr[2]);
                        mp2.put(arr[2], (Integer.parseInt(arr[elevel])) + count);
                    } else {
                        mp2.put(arr[2], Integer.parseInt(arr[elevel]));
                    }
                }
            }
            System.out.println(mp2);
        }
        catch (IOException e) {
            System.out.println("IO Exception");
            e.printStackTrace();
        }
    }
}

