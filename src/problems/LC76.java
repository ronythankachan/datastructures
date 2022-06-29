package problems;

import java.util.HashMap;
import java.util.Map;

public class LC76 {
    public static  String minWindow(String str, String pattern) {
        if(pattern.length()> str.length() || pattern.length()==0) return "";
        int left=0, minLen = Integer.MAX_VALUE ,matched=0;
        String substr = "";
        Map<Character,Integer> patternFrequency = new HashMap<>();
        for(char c:pattern.toCharArray()){
            patternFrequency.put(c,patternFrequency.getOrDefault(c,0)+1);
        }
        Map<Character,Integer> count = new HashMap<>();
        for(int right=0;right<str.length();right++){
            char rightChar= str.charAt(right);
            if(patternFrequency.containsKey(rightChar)){
                count.put(rightChar,count.getOrDefault(rightChar,0)+1);
                if(count.get(rightChar) == patternFrequency.get(rightChar)) matched++;
            }
            while(matched == patternFrequency.size()){
                if(right-left+1 < minLen){
                    minLen = Math.min(minLen,right-left+1);
                    substr = str.substring(left,right+1);
                }
                // pop the left character until no longer a match
                char leftChar = str.charAt(left);
                if(count.containsKey(leftChar)){
                    count.put(leftChar,count.get(leftChar)-1);
                    if(count.get(leftChar) < patternFrequency.get(leftChar)) matched--;
                }
                left++;
            }

        }
        return substr;
    }

    public static void main(String[] args) {
        System.out.println(LC76.minWindow("asdfjlkajsdf","kla"));
    }
}
