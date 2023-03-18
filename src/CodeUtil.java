
import java.util.Random;
public class CodeUtil {
    public static String getCode(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890123456789123456789012345678901234567890";
        Random random = new Random();
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(102);
            s.append(str.charAt(number));
        }
        return s.toString();
    }
}
