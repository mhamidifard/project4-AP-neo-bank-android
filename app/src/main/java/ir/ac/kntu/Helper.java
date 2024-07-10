package ir.ac.kntu;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static long pow(long firstNum, long secondNum) {
        long ans = 1;
        for (int i = 0; i < secondNum; i++) {
            ans *= firstNum;
        }
        return ans;
    }

    public static boolean isNumber(String text){
        return true;
    }

    public static boolean isLetter(String temp) {
        Pattern pLetter = Pattern.compile("[a-z ]+");
        Matcher mLetter = pLetter.matcher(temp);
        return mLetter.matches();
    }

    public static Long convertToPhone(String temp) throws RuntimeException{
        if(!isNumber(temp) || temp.length()!=10 || temp.charAt(0)!='9'){
            throw new RuntimeException();
        }
        return Long.parseLong(temp);
    }

    public static boolean isStrongPass(String temp) {
        Pattern pSpace = Pattern.compile("\\S+");
        Pattern pNUmber = Pattern.compile("\\d");
        Pattern pLowLetter = Pattern.compile("[a-z]");
        Pattern pUpLetter = Pattern.compile("[A-Z]");
        Pattern pSpecial = Pattern.compile("[~!@#$%^&*()_+|`}{\"':;?/>.<\\\\,\\-]");

        Matcher matcher = pSpace.matcher(temp);
        if (!matcher.matches()) {
            return false;
        }
        if (temp.length() < 8) {
            return false;
        }
        matcher = pNUmber.matcher(temp);
        if (!matcher.find()) {
            return false;
        }
        matcher = pLowLetter.matcher(temp);
        if (!matcher.find()) {
            return false;
        }
        matcher = pUpLetter.matcher(temp);
        if (!matcher.find()) {
            return false;
        }
        matcher = pSpecial.matcher(temp);
        return matcher.find();
    }

}
