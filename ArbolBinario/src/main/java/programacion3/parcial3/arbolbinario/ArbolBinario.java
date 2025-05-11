package programacion3.parcial3.arbolbinario;

public class ArbolBinario {
    private Nodo raiz;

    public boolean estaVacio() {
        return raiz == null;
    }

    public void agregar(int dato) {
        raiz = agregarRec(raiz, dato);
    }

    private Nodo agregarRec(Nodo actual, int dato) {
        if (actual == null) {
            return new Nodo(dato);
        }
        if (dato < actual.dato) {
            actual.izquierdo = agregarRec(actual.izquierdo, dato);
        } else if (dato > actual.dato) {
            actual.derecho = agregarRec(actual.derecho, dato);
        }
        return actual;
    }

    public boolean existe(int dato) {
        return existeRec(raiz, dato);
    }

    private boolean existeRec(Nodo actual, int dato) {
        if (actual == null) return false;
        if (dato == actual.dato) return true;
        return dato < actual.dato ? existeRec(actual.izquierdo, dato) : existeRec(actual.derecho, dato);
    }

    public void inorden(StringBuilder sb) {
        inordenRec(raiz, sb);
    }

    private void inordenRec(Nodo actual, StringBuilder sb) {
        if (actual != null) {
            inordenRec(actual.izquierdo, sb);
            sb.append(actual.dato).append(" ");
            inordenRec(actual.derecho, sb);
        }
    }

    public void preorden(StringBuilder sb) {
        preordenRec(raiz, sb);
    }

    private void preordenRec(Nodo actual, StringBuilder sb) {
        if (actual != null) {
            sb.append(actual.dato).append(" ");
            preordenRec(actual.izquierdo, sb);
            preordenRec(actual.derecho, sb);
        }
    }

    public void postorden(StringBuilder sb) {
        postordenRec(raiz, sb);
    }

    private void postordenRec(Nodo actual, StringBuilder sb) {
        if (actual != null) {
            postordenRec(actual.izquierdo, sb);
            postordenRec(actual.derecho, sb);
            sb.append(actual.dato).append(" ");
        }
    }

    public int obtenerAltura() {
        return alturaRec(raiz);
    }

    private int alturaRec(Nodo actual) {
        if (actual == null) return 0;
        return 1 + Math.max(alturaRec(actual.izquierdo), alturaRec(actual.derecho));
    }

    public int contarHojas() {
        return contarHojasRec(raiz);
    }

    private int contarHojasRec(Nodo actual) {
        if (actual == null) return 0;
        if (actual.izquierdo == null && actual.derecho == null) return 1;
        return contarHojasRec(actual.izquierdo) + contarHojasRec(actual.derecho);
    }

    public void eliminar(int dato) {
        raiz = eliminarRec(raiz, dato);
    }

    private Nodo eliminarRec(Nodo actual, int dato) {
        if (actual == null) return null;

        if (dato == actual.dato) {
            if (actual.izquierdo == null && actual.derecho == null) return null;
            if (actual.izquierdo == null) return actual.derecho;
            if (actual.derecho == null) return actual.izquierdo;
            int menor = obtenerMenor(actual.derecho);
            actual.dato = menor;
            actual.derecho = eliminarRec(actual.derecho, menor);
            return actual;
        }

        if (dato < actual.dato) {
            actual.izquierdo = eliminarRec(actual.izquierdo, dato);
            return actual;
        }
        actual.derecho = eliminarRec(actual.derecho, dato);
        return actual;
    }

    private int obtenerMenor(Nodo actual) {
        return actual.izquierdo == null ? actual.dato : obtenerMenor(actual.izquierdo);
    }

    public void borrar() {
        raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }
}