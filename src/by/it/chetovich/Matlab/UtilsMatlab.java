package by.it.chetovich.Matlab;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 * Matlab utils
 */
public class UtilsMatlab {


    /**
     *
     * @param s line to convert to array
     * @return array of 2 elements
     */
    public static String[] convertLineToArray(String s){

        Pattern pat = Pattern.compile(Patterns.operationType); //разбиваем на 2 переменных и  заносим их в массив
        String[] array = pat.split(s);
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].trim();
        }
        return array;
    }


    /**
     *
     * @param map map with variables, that should be saved in txt file
     */
    public static void saveVarsInFile (Map <String, Var> map, File file){

        try (PrintWriter print = new PrintWriter(new FileWriter(file))){
            for (Map.Entry<String, Var> entry : map.entrySet()) {
                print.println(entry.getKey()+" = "+entry.getValue());
            }
        }
        catch (IOException e){
            System.out.println("Writing to file "+file+" failed.");
        }
    }


    /**
     *
     * @return map with variables saved in earlier sessions
     */
    public static Map <String, Var> putVarsFromFileIntoMap(){

        Map<String,Var> map = new TreeMap<>();
        String src = System.getProperty("user.dir") + "/src/by/it/chetovich/Matlab/vars.txt";
        File file = new File(src);
        try(BufferedReader r = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = r.readLine())!=null){
                String[] array = UtilsMatlab.convertLineToArray(line);
                String a = array[0].trim();// имя переменной
                Var b = DefineVariable.defineVar(array[1]);  //второй операнд
                map.put(a, b);
            }
        }
        catch (IOException e){
            System.out.println(file+" file not read.");
        }
        return map;
    }


    /**
     *
     * @param var instance which field should be printed
     */
    public static void print (Var var) {

        if (var!=null) System.out.println(" = " + var+var.getType());
    }


    /**
     *
     * @param line line that has been entered by user (sortvar or printvar)
     * @param map from which the variables should be printed
     * @throws IOException
     */
    public static void printVarList (String line, Map <String, Var> map) throws IOException {

        if (!line.isEmpty()) {
            if ("printvar".equals(line)) UtilsMatlab.ifPrintvar(map);
            else{
                if ("sortvar".equals(line)) UtilsMatlab.ifSortvar(map);
                else {
                    System.out.println("Что-то вы не то ввели, может попробуете ещё раз.");
                    printVarList(enterLine(), map);
                }
            }
        }
    }


    /**
     *
     * @param map map with variables
     */
    public static void ifSortvar(Map<String, Var> map) throws IOException {

        if (!map.isEmpty()) {
            for (String s : map.keySet()) {
                System.out.println(s);
            }
        } else System.out.println("Список пуст.");
    }


    /**
     *
     * @param map map with variables
     */
    public static void ifPrintvar(Map<String, Var> map) {

        if (!map.isEmpty()) {
            for (Map.Entry<String, Var> entry : map.entrySet()) {
                System.out.println(entry.getKey()+" = "+entry.getValue());
            }
        } else System.out.println("Список пуст.");

    }


    /**
     *
     * @return line that has been entered by user
     * @throws IOException
     */
    public static String enterLine() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }



}
