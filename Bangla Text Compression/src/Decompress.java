import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Decompress {

    public static String decompress(List<Integer> compressed){
        int dictSize = 118;
        Map<Integer, String> dictionary = new HashMap<Integer, String>();
        dictionary.put(0, " ");
        for(int i= 2433; i< 2550; i++){
            dictionary.put( i,""+ (char) i);
        }

        String w = "" + (char)((int)compressed.remove(0) + 2432);
        StringBuffer  result = new StringBuffer(w);

        for(int k: compressed){
            if(k != 0)
                k += 2432;
            String entry;
            if(dictionary.containsKey(k))
                entry = dictionary.get(k);
            else if(k == dictSize)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);
            result.append(entry);

            dictionary.put(dictSize++, w + entry.charAt(0));

            w = entry;
        }

        return result.toString();

    }
}
