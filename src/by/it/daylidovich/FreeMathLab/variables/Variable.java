package by.it.daylidovich.FreeMathLab.variables;

import by.it.daylidovich.FreeMathLab.interfaces.IOperation;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public abstract class Variable implements IOperation {

    private static Map<String, Variable> base = new HashMap<>();

    public static void setBase(Map<String, Variable> base) {
        Variable.base = base;
    }

    public static Map<String, Variable> getBase() {
        return base;
    }

    @Override
    public Variable add(Variable variable) {
        System.out.println("Сложение невозможно.");
        return null;
    }

    @Override
    public Variable sub(Variable variable) {
        System.out.println("Вычитание невозможно.");
        return null;
    }

    @Override
    public Variable mult(Variable variable) {
        System.out.println("Умножение невозможно.");
        return null;
    }

    @Override
    public Variable div(Variable variable) throws ArithmeticException{
        throw new ArithmeticException("Деление невозможно.");
    }

    @Override
    public void save(String name) {
        if (base.containsKey(name))
            System.out.println("Измените имя переменной.");
        else
            base.put(name, this);

    }

    public static void printVariables(){
        for (Map.Entry<String,Variable> pair: base.entrySet())
            System.out.println(pair.getKey() + "=" + pair.getValue());
    }

    public static void sortVariable(){
        Map<String, Variable> sortBase = new TreeMap<>(base);
        for (Map.Entry<String,Variable> pair: sortBase.entrySet())
            System.out.println(pair.getKey() + "=" + pair.getValue());
    }


}
