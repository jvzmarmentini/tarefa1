import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Analise {
  public static void main(String[] args) {
    List<Integer> list = fileReader("v8");

    long iniTime = System.currentTimeMillis();
    hasDups(list);
    System.out.println(format("ss.SSS", System.currentTimeMillis() - iniTime));
  }

  public static void hasDups2(List<Integer> list){
    // como hashMap usa uma chave parar um valor, e ele é unico
    // podemos ver se cada elemento da list existe no map, senao
    // adicionalo
    HashMap<Integer, Boolean> hashMap = new HashMap<>();
    for (int i = 0; i < list.size(); i++) {
      if (hashMap.containsKey(list.get(i))) {
        // duplicate
        System.out.println("elemento duplicado: " + list.get(i));
      } else {
        // unico ou primeira vez do elemento
        hashMap.put(list.get(i), true);
      }
    }
  }

  public static void hasDups(List<Integer> list) {
    Set<Integer> repeated = new HashSet<>();

    if (!list.isEmpty()) {
      Collections.sort(list);

      Integer curr = null;
      for (Integer next : list) {
        if (curr != null) {
          if (curr.equals(next)) {
            repeated.add(curr);
          }
        }
        curr = next;
      }
    }

    for (Integer integer : repeated) {
      System.out.println(integer);
    }
  }

  public static String format(String shape, long currentTimeMillis) {
    return new SimpleDateFormat(shape, Locale.getDefault()).format(currentTimeMillis);
}

  public static List<Integer> fileReader(String file_name) {
    List<Integer> list = new ArrayList<>();

    try {
      FileReader read = new FileReader("vetores/" + file_name);
      BufferedReader reader = new BufferedReader(read);
      String line;

      while ((line = reader.readLine()) != null) {
        list.add(Integer.parseInt(line));
      }

      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return list;
  }
}