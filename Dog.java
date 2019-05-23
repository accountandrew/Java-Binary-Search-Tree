package dogtree;

public class Dog implements Comparable<Dog>{
    private final String name;
    private final Double weight;
    public Dog(String name,Double weight){
        this.name = name;
        this.weight = weight;
    }
    
    public String getName(){
        return name;
    }
    public Double getWeight(){
        return weight;
    }
    
    @Override
    public String toString(){
        return "Dog name: " + name + ", Dog weight: " + weight + ".";
    }

    @Override
    public int compareTo(Dog theDog) {
        int compare = this.name.compareTo(theDog.getName());
        return compare;
    }

}
