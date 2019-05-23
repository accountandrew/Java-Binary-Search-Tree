package dogtree;

public class BinarySearchDogTree {

    class Node {

        Dog data;
        Node leftChild;
        Node rightChild;

        Node(Dog data) {
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }
    }
    private Node root;
    private Node tempRoot;
    private String sortBy;
    
    public BinarySearchDogTree() {
        root = null;
        tempRoot = null;
        sortBy = "name";
    }

    public boolean addDog(String name, Double weight) {
        Dog data = new Dog(name, weight);
        if(sortBy.equals("name")){
            root = addRecursive(root, data);
        }else{
            root = addWeightRecursive(root,data);
        }
        return containsRecursive(root,data);
    }

    private Node addRecursive(Node current, Dog data) {
        if (current == null) {
            return new Node(data);
        } else if (data.compareTo(current.data) < 0) {
            current.leftChild = addRecursive(current.leftChild, data);
            return current;
        } else if (data.compareTo(current.data) > 0) {
            current.rightChild = addRecursive(current.rightChild, data);
            return current;
        } else {
            return current;
        }
    }

    private Node addWeightRecursive(Node current, Dog data) {
        if (current == null) {
            return new Node(data);
        } else if (data.getWeight() < current.data.getWeight()) {
            current.leftChild = addWeightRecursive(current.leftChild, data);
            return current;
        } else if (data.getWeight() > current.data.getWeight()) {
            current.rightChild = addWeightRecursive(current.rightChild, data);
            return current;
        } else {
            return current;
        }
    }
    //make a new tree by looking thorugh each emelent of the first tree
    //tempSwapRoot. make it equal to root, them root in now the new tree. 

    public boolean containsDog(String name) {
        Dog data = new Dog(name, 0.0);
        return containsRecursive(root, data);
    }

    private boolean containsRecursive(Node current, Dog data) {
        if (current == null) {
            return false;
        } else if (data.compareTo(current.data) == 0) {
            return true;
        } else if (data.compareTo(current.data) < 0) {
            return containsRecursive(current.leftChild, data);
        } else {
            return containsRecursive(current.rightChild, data);
        }
    }

    public void displayInOrder() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.leftChild);
            System.out.println(node.data);
            inOrderTraversal(node.rightChild);
        }
    }

    private void inOrderTraversalSwitch(Node node) {
        if (node != null) {
            inOrderTraversalSwitch(node.leftChild);
            inOrderTraversalSwitch(node.rightChild);
            if(sortBy.equals("weight")){
                root = addWeightRecursive(root,node.data);
            }else{
                root = addRecursive(root,node.data);
            }
        }
    }
    
    private Dog getSmallest(Node root) {
        if (root.leftChild == null) {
            return root.data;
        } else {
            return getSmallest(root.leftChild);
        }
    }

    public Double getDogWeight(String name) {
        Dog data = new Dog(name, 0.0);
        return getDataRecursive(root, data);
    }

    private Double getDataRecursive(Node current, Dog data) {
        if (current == null) {
            return -1.0;
        } else if (data.compareTo(current.data) == 0) {
            return current.data.getWeight();
        } else if (data.compareTo(current.data) < 0) {
            return getDataRecursive(current.leftChild, data);
        } else {
            return getDataRecursive(current.rightChild, data);
        }
    }

    public boolean removeDog(String name) {
        Dog data = new Dog(name, 0.0);
        deleteRecursive(root, data);
        return containsRecursive(root,data);
    }

    private Node deleteRecursive(Node current, Dog data) {
        if (current == null) {
            return null;
        } else if (data.compareTo(current.data) == 0) {
            if (current.leftChild == null && current.rightChild == null) {
                return null;
            } else if (current.rightChild == null) {
                return current.leftChild;
            } else if (current.leftChild == null) {
                return current.rightChild;
            } else {
                Dog smallestVal = getSmallest(current.rightChild);
                current.data = smallestVal;
                current.rightChild = deleteRecursive(current.rightChild, smallestVal);
                return current;
            }
        } else if (data.compareTo(current.data) < 0) {
            current.leftChild = deleteRecursive(current.leftChild, data);
            return current;
        } else {
            current.rightChild = deleteRecursive(current.rightChild, data);
            return current;
        }
    }

    public void switchSortCriteria(String change) {
        if(change.equals("weight")){
            sortBy = "weight";
        }else if(change.equals("name")){
            sortBy = "name";
        }else{
            System.out.println("Wrong Parameter");
        }
        tempRoot = root;
        root = null;
        inOrderTraversalSwitch(tempRoot);
    }

}
