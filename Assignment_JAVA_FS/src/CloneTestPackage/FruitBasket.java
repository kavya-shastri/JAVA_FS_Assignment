package CloneTestPackage;
import java.util.ArrayList;
import java.util.List;

public class FruitBasket implements Cloneable{
    private final List<String> fruitsList;

    public FruitBasket(List<String> fruitsList) {
        List<String> tempList = new ArrayList<>(fruitsList);
        this.fruitsList  = tempList;
    }

    public String toString() {
        return this.fruitsList.toString();
    }

    public List<String> getFruitsList() {
        return fruitsList;
    }

    @Override
    public Object clone() {
        //Creating a copy of the fruitsList instead of using the reference of the original object
        List<String> tempList = new ArrayList<>(this.fruitsList);
        return new FruitBasket(tempList);
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Orange");
        list.add("Pineapple");
        FruitBasket ob1 = new FruitBasket(list);
        FruitBasket ob2 = (FruitBasket)ob1.clone();
        //Adding a new fruit to the cloned object to see that the original one does not get updated.
        ob2.fruitsList.add("Berries");
        System.out.println(ob1.toString());
        System.out.println(ob2.toString());
    }


}