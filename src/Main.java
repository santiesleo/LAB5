import java.util.Arrays;
import java.util.Scanner;
import java.lang.Object;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        BST tree = new BST(); //Creo el árbol
        String names = sc.nextLine(); //Leo los nombres
        String[] array = names.split(" "); //Asigno los nombres a un arreglo de String
        System.out.println(Arrays.toString(array));
        int n = ((array.length-1)/2); //Divido la longitud del arreglo y le resto 1
        n = (int) Math.ceil(n);       //Aplico función techo al n y lo casteo
        System.out.println(array[n]);
        tree.add(new Node(array[n])); //Añado el primer nombre y se asigna como raíz
        String[] izq = new String[n];
        String[] der = new String[n];

        boolean flag = false;
        while (array.length > 0 && !flag){
            if(array.length > 3){
                izq = divisionIzq(array);
                der = divisionDer(array);
                array = new String[izq.length + der.length];
                n = ((array.length-1)/2); //Divido la longitud del arreglo y le resto 1
                n = (int) Math.ceil(n);       //Aplico función techo al n y lo casteo
                System.arraycopy(izq, 0, array, 0, izq.length);
                System.arraycopy(der, 0, array, izq.length, der.length);
                System.out.println(array[n]);
                tree.add(new Node(array[n]));
            }else {
                izq = divisionIzq(array);
                der = divisionDer(array);
                array = new String[izq.length + der.length];
                System.arraycopy(izq, 0, array, 0, izq.length);
                System.arraycopy(der, 0, array, izq.length, der.length);
                for(int i = 0; i < array.length; i++){
                    System.out.println(Arrays.toString(array));
                    System.out.println(array[i]);
                    tree.add(new Node(array[i]));
                }
                flag = true;
            }
            //System.out.println(tree.getMaxProdundidad());
        }

        tree.inOrderReverse();
        System.out.println(tree.maxDepth() - 1);
    }



    public static String[] divisionIzq(String[] array){
        int n = ((array.length-1)/2);
        n = (int) Math.ceil(n);
        String[] izq = new String[n];
        for(int i = 0; i<izq.length; i++){
            izq[i] = array[i];
        }
        return izq;
    }

    public static String[] divisionDer(String[] array){
        int n = ((array.length-1)/2);
        n = (int) Math.ceil(n);
        String[] der = new String[array.length - (n +1)];
        for(int i = n + 1; i < array.length; i++){
            der[i-n-1] = array[i];
        }
        return der;
    }
}
