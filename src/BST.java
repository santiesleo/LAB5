public class BST {
    private Node root;

    public void add(Node node){
        if(root==null){
            root=node;
            //System.out.println(root.getKey());
        }else{
            add(root, node);
        }
    }

    private void add(Node current, Node node){
        if(node.getKey().compareTo(current.getKey()) < 0){
            //Ingresar a la izquierda
            if(current.getLeft() == null){
                current.setLeft(node);
            }else{
                add(current.getLeft(), node);
            }
        }else if (node.getKey().compareTo(current.getKey()) > 0){
            //Ingresar a la derecha
            if(current.getRight() == null){
                current.setRight(node);
            }else {
                add(current.getRight(), node);
            }
        }else{
            //No hacer nada
        }
    }

    public Node getMin(){
        return getMin(root);
    }

    private Node getMin(Node current){
        if(current.getLeft() == null){
            return current;
        }else {
            return (current.getLeft());
        }
    }

    public Node getMax(){
        return getMax(root);
    }

    private Node getMax(Node current){
        if(current.getRight() == null){
            return current;
        }
        return (current.getRight());
    }


    public void inOrderReverse(){
        inOrderReverse(root);
    }


    private void inOrderReverse(Node current){
        if(current == null){
            //current.getKey();
            //System.out.println("No hay nada");
            return;
        }
        inOrderReverse(current.getRight());// Mayor a
        System.out.print(current.getKey() + " ");
        inOrderReverse(current.getLeft());// Menor
    }

    public Node search(String goal) {
        return search(root, goal);
    }

    private Node search(Node current, String goal){
        if(current == null) {
            System.out.println("Not found");
            return null;
        } else if (current.getKey().equals(goal)){
            return current;
        }

        if(goal.compareTo(current.getKey()) < 0){
            return search(current.getLeft(), goal);
        }else {
            return search(current.getRight(), goal);
        }
    }

    public void delete(String goal){
        delete(null, root, goal);
    }

    private void delete(Node parent, Node current, String goal){
        if(current == null){
            return;
        }if(goal.equals(current.getKey())){ //Encontramos al nodo
            if(current.getRight() == null && current.getLeft() == null){ //Es un nodo hoja
                if(parent == null){
                    root = null;
                }else if(parent.getLeft() == current){ //Es un nodo hoja izquierdo
                    parent.setLeft(null);
                }else if(parent.getRight() == current){ //Es un nodo hoja derecho
                    parent.setRight(null);
                }
            } else if (current.getRight() != null && current.getLeft() == null) { //Nodo a eliminar tiene un hijo derecho
                if(parent.getRight() == current){ //El nodo a eliminar tiene un hijo derecho y está a la derecha del padre
                    parent.setRight(current.getRight());
                }else {                           //El nodo a eliminar tiene un hijo derecho y está a la izquierda del padre
                    parent.setLeft(current.getRight());
                }
            } else if (current.getLeft() != null && current.getRight() == null) { //Nodo a eliminar tiene un hijo izquierdo
                if(parent.getLeft() == current){ //El nodo a eliminar tiene un hijo izquierdo y está a la izquierda del padre
                    parent.setLeft(current.getLeft());
                }else {                          //El nodo a eliminar tiene un hijo izquierdo y está a la derecha del padre
                    parent.setRight(current.getLeft());
                }
            } else if (current.getRight() != null && current.getLeft() != null){                                                               //Nodo a eliminar tiene dos hijos
                Node sucessor = getMin(current.getRight());
                //Sobreescribir la key y los valores
                current.setKey(sucessor.getKey());
                //Si tienen valores hacer current.setValue(sucessor.getValue())
                delete(current, current.getRight(), sucessor.getKey());

            }

        }else if(goal.compareTo(current.getKey()) < 0){
            delete(current, current.getLeft(), goal);
        } else if (goal.compareTo(current.getKey()) > 0) {
            delete(current, current.getRight(), goal);
        }
    }
}
