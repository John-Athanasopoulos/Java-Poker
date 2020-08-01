import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Hands {

    static void split(ArrayList<String> l , String str) {
        String[] s = str.split("");
        if(s.length == 3){
            s[1] = s[1] + s[2];
        }
        l.add(s[0]);
        l.add(s[1]);
    }

    static int fourOfAKind(ArrayList<String> l){
        ArrayList<String> lc = new ArrayList<String>();
        int counter;
        String intToString;
        int num = 0;
        for(String s : l){
            split(lc,s);
        }
        for(int i = 1; i <=13 ;i++){
            counter = 0;
            intToString = String.valueOf(i);
            for(String s : lc){
                if(s.equals(intToString)){
                    counter++;
                }
            }
            if(counter >= 4){
                num = i;
                break;
            }
        }
        if(num == 1){
            return 14;
        }
        else {
            return num;
        }
    }

    static int threeOfAKind(ArrayList<String> l){
        ArrayList<String> lc = new ArrayList<String>();
        int counter;
        String intToString;
        int num = 0;
        for(String s : l){
            split(lc,s);
        }
        for(int i = 1; i <=13 ;i++){
            counter = 0;
            intToString = String.valueOf(i);
            for(String s : lc){
                if(s.equals(intToString)){
                    counter++;
                }
            }
            if(counter >= 3 && i == 1){
                num = 14;
                break;
            }
            else if(counter >= 3){
                num = i;
            }
        }
        return num;
    }

    static ArrayList<Integer> findPair(ArrayList<String> l){
        ArrayList<String> lc = new ArrayList<String>();
        ArrayList<Integer> allPairs = new ArrayList<Integer>();
        int counter;
        String intToString;
        int num = 0;
        for(String s : l){
            split(lc,s);
        }
        for(int i = 1; i <=13 ;i++){
            counter = 0;
            intToString = String.valueOf(i);
            for(String s : lc){
                if(s.equals(intToString)){
                    counter++;
                }
            }
            if(counter >= 2){
                allPairs.add(i);
            }
        }
        return allPairs;
    }

    static int twoPair(ArrayList<String> l){
        ArrayList<Integer> allPairs = findPair(l);

        if(allPairs.size() >= 2 && allPairs.contains(1)){
            return 14;
        }

        if(allPairs.size() > 2){
            if(allPairs.get(0) < allPairs.get(1) && allPairs.get(1) > allPairs.get(2)){
                return allPairs.get(1);
            }
            else if(allPairs.get(0) > allPairs.get(1) && allPairs.get(0) > allPairs.get(2)){
                return allPairs.get(0);
            }
            else{
                return allPairs.get(2);
            }
        }
        else if(allPairs.size() == 2){
            if(allPairs.get(0) < allPairs.get(1)){
                return allPairs.get(1);
            }
            else{
                return allPairs.get(0);
            }
        }
        return 0;
    }

    static int pair(ArrayList<String> l){
        ArrayList<Integer> allPairs = findPair(l);

        if(allPairs.contains(1)){
            return 14;
        }

        if(allPairs.size() == 1){
            return allPairs.get(0);
        }
        else if(allPairs.size() == 2){
            if(allPairs.get(0) < allPairs.get(1)){
                return allPairs.get(1);
            }
            else{
                return allPairs.get(0);
            }
        }
        else if(allPairs.size() == 3){
            if(allPairs.get(0) < allPairs.get(1) && allPairs.get(1) > allPairs.get(2)){
                return allPairs.get(1);
            }
            else if(allPairs.get(0) > allPairs.get(1) && allPairs.get(0) > allPairs.get(2)){
                return allPairs.get(0);
            }
            else{
                return allPairs.get(2);
            }
        }
        return 0;
    }

    static int flush(ArrayList<String> l){
        ArrayList<String> lc = new ArrayList<String>();
        int num = 0;
        for(String s : l){
            split(lc,s);
        }
        String[] tempArr = {"","","","","","",""};
        for(int i=0; i<14;i++){
            if(lc.indexOf(lc.get(i)) % 2 == 0){
                tempArr[i/2] = lc.get(i);
            }
        }
        Arrays.sort(tempArr);
        for(int k=0; k < 3; k++) {
            if (tempArr[k].equals(tempArr[k+1]) && tempArr[k].equals(tempArr[k+2]) && tempArr[k].equals(tempArr[k+3]) && tempArr[k].equals(tempArr[k+4])){
                num = 1;
                break;
            }
        }

        if(num == 1){ //Call the high card method, so that in case of conflict there is the declaration of the winner
            num = highCard(l);
        }
        return num;
    }

    static int straight(ArrayList<String> l){
        ArrayList<String> lc1 = new ArrayList<String>();  //final values
        ArrayList<String> lc = new ArrayList<String>();   //temporary values
        for(String s : l){
            split(lc,s);
        }
        int[] arr = {0,0,0,0,0,0,0};   //array for sorting
        for(String s : lc){
            if (lc.indexOf(s) % 2 == 1){    //As the numbers are elements with odd indices, we want to work with them
                lc1.add(s);
            }
        }
        for(int i = 0; i <=6; i++){
            arr[i] = Integer.parseInt(lc1.get(i));
        }
        Arrays.sort(arr);   //sort the array
        lc1 = new ArrayList<String>();
        for(int i =0; i <=6; i++){
            lc1.add(String.valueOf(arr[i]));   //After the sorting, enter to the final list
        }
        for(int i = 0; i <= 6; i++){
            if(arr[i] == 1){
                lc1.add("14");     //Checking for the case of 10,11,12,13,14 (10,J,Q,K,A). If 1 exists, add 14
            }
        }

        int counter = 0;   //counts for consecutive numbers
        String max = "0";   // the max of the consecutive numbers

        for(int i = 0; i <= lc1.size()-2; i++) {     //We want to stop at the second number from the end, due to the if statement
            if (Integer.parseInt(lc1.get(i)) == Integer.parseInt(lc1.get(1 + i)) - 1){
                counter++;
                if(counter == 4){   //4 consecutive pairs of consecutive numbers
                    max = lc1.get(i+1);   //max is the next value of the last
                    counter--;
                }
            }
            else{    //If at least a pair is not consecutive numbers, then counter = 0 (Or else in case of 1,2,3,4,13(,14) we would get counter = 4 although the numbers aren't consecutive)
                counter = 0;
            }
        }

        return Integer.parseInt(max);
    }

    static int straightFlush(ArrayList<String> l){
        int i = straight(l);
        int a = flush(l);
        int max = 0;
        if(i!=0 && a!=0){
            max = i;
        }
        return max;
    }

    static int fullHouse(ArrayList<String> l){
        ArrayList<Integer> i = findPair(l);
        int k = threeOfAKind(l);
        boolean b = false;
        for(int in : i){
            if(in != k){
                b = true;
                break;
            }
        }
        if(i.size()!=0 && k!=0 && b){
            return k;
        }
        return 0;
    }

    static int highCard(ArrayList<String> l){
        ArrayList<String> lc = new ArrayList<String>();
        int max = 0;
        for(String s : l){
            split(lc,s);
        }

        if(lc.contains("1")){
            return 14;
        }

        for(String s : lc){
            if(lc.indexOf(s) % 2 == 1 && Integer.parseInt(s) > max){
                max = Integer.parseInt(s);
            }
        }
        return max;
    }
}
