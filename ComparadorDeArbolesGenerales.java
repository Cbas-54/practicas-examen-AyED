/*
Defina una clase ComparadorDeArbolesGenerales que contenga el siguiente metodo:
public boolean esInferiorProfundo(GeneralTree<Integer> arbol1, GeneralTree<Integer> arbol2).
Un arbol general arbol1 se considera "profundamente inferior" a otro arbol general arbol 2 cuando se cumplen las siguientes dos reglas en todos los nodos coincidentes en posición de ambos arboles:
1. el valor de cada nodo en arbol1 debe ser menor que el valor del nodo correspondiente en arbol2.
2.a) Si ambos nodos tienen hijos la suma de los valores de los hijos de cada nodo en arbol1 debe ser menor que la suma de los valores de los hijos del nodo correspondiente en arbol2.
b) si ambos nodos son hojas: la condición se considera cumplida para esos nodos si se cumple la condición 1.
si uno de los nodos es hoja y el otro no:
c1) Si un nodo de un arbol1 es hoja y el nodo correspondiente en arbol 2 no es hoja entonces la condición se considera cumplida para ese nodo si se cumple la condición 1.
c2) Si un nodo de arbol 1 no es hoja y el nodo correspondiente en arbol2 es hoja, entonces arbol1 no es profundamente inferior y el método debe devolver false.
*/

package GeneralTree;

import Queue.Queue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RecorridosAG {
    
    public List<Integer> numerosImparesMayoresQue(GeneralTree<Integer> a, Integer n){
        if(a.isEmpty())
            return null;
        
        List<Integer> Lista = new LinkedList<Integer>();
        
        PreOrden(a, n, Lista);
        return Lista;
    }
    
    private void PreOrden(GeneralTree<Integer> a, Integer n, List<Integer> lista){
        if((a.getDato() % 2 != 0) && (a.getDato() > n))
            lista.add(a.getDato());
        List<GeneralTree<Integer>> children = a.getChildren();
        for(GeneralTree<Integer> child: children){
            PreOrden(child, n, lista);
        }
    }
    
    private void InOrden(GeneralTree<Integer> a, Integer n, List<Integer> lista){
        List<GeneralTree<Integer>> children = a.getChildren();
        Iterator<GeneralTree<Integer>> it = children.iterator();
        
        if(it.hasNext())
            InOrden(it.next(), n, lista);
        
        if((a.getDato() > n) && (a.getDato() % 2 != 0))
            lista.add(a.getDato());
        
        while(it.hasNext())
            InOrden(it.next(), n, lista);
    }
    
    private void PostOrden(GeneralTree<Integer> a, Integer n, List<Integer> lista){
        List<GeneralTree<Integer>> children = a.getChildren();
        for(GeneralTree<Integer> child: children)
            PreOrden(child, n, lista);
        if((a.getDato() % 2 != 0) && (a.getDato() > n))
            lista.add(a.getDato());
    }
    
    private void PorNivel(GeneralTree<Integer> a, Integer n, List<Integer> lista){
        Queue<GeneralTree<Integer>> cola = new Queue<GeneralTree<Integer>>();
        GeneralTree<Integer> aux = new GeneralTree<Integer>();
        cola.enqueue(a);
        
        while(!cola.isEmpty()){
            aux = cola.dequeue();
            if((aux.getDato() > n) && (aux.getDato() % 2 != 0))
                lista.add(aux.getDato());
            List<GeneralTree<Integer>> children = aux.getChildren();
            for(GeneralTree<Integer> child: children)
                cola.enqueue(child);
        }
    }
}
