import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Compress {

    public static List<Integer> compress(String input){
        int dictSize = 118;
        Map<String, Integer> dictionary = new HashMap<String, Integer>();

        dictionary.put(" ", 0);

        for(int i=2433; i< 2550; i++){
            dictionary.put(""+(char) i , i);
        }

        String w = "";
        List<Integer> result = new ArrayList<Integer>();

        for(char c: input.toCharArray()){
            String wc = w + c;
            if(dictionary.containsKey(wc)){
                w = wc;
            }else{
                int code = dictionary.get(w);
                if(code > 0)
                    code -= 2432;
                result.add(code);
                dictionary.put(wc, dictSize++);
                w = "" + c;
            }
        }

        if(!w.equals("")){
            int code = dictionary.get(w);
            if(code > 0)
                code -= 2432;
            result.add(code);
        }
        return result;
    }



    public static void main(String[] args) {

        String text = "অআকখ আমার সোনার বাংলা";
        List<Integer> compressed = compress(text);
        System.out.println(compressed);
        String decompressed = Decompress.decompress(compressed);
        System.out.println(decompressed);
    }
}
