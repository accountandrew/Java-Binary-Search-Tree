package dogtree;

public class DogTree {

    public static void main(String[] args) {
        BinarySearchDogTree t1 = new BinarySearchDogTree();
        t1.switchSortCriteria("name");
        t1.addDog("Shifty", 45.0);
        t1.addDog("Blake", 30.0);
        t1.addDog("Freddy", 99.0);
        t1.addDog("Verision", 103.0);
        t1.addDog("Holden", 12.0);
        t1.displayInOrder();
        t1.switchSortCriteria("weight");
        t1.displayInOrder();

    }
    
}
