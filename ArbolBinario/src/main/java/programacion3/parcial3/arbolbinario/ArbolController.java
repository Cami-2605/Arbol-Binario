package programacion3.parcial3.arbolbinario;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class ArbolController {
    @FXML private TextField txtDato;
    @FXML private TextArea txtSalida;
    @FXML private Canvas canvas;

    private final ArbolBinario arbol = new ArbolBinario();

    @FXML
    public void agregar() {
        try {
            int dato = Integer.parseInt(txtDato.getText());
            arbol.agregar(dato);
            dibujar();
            txtDato.clear();
        } catch (NumberFormatException e) {
            txtSalida.setText("Ingrese un número válido");
        }
    }

    @FXML
    public void eliminar() {
        try {
            int dato = Integer.parseInt(txtDato.getText());
            arbol.eliminar(dato);
            dibujar();
            txtDato.clear();
        } catch (NumberFormatException e) {
            txtSalida.setText("Ingrese un número válido");
        }
    }

    @FXML
    public void buscar() {
        try {
            int dato = Integer.parseInt(txtDato.getText());
            boolean existe = arbol.existe(dato);
            txtSalida.setText(existe ? "El dato existe." : "El dato NO existe.");
        } catch (NumberFormatException e) {
            txtSalida.setText("Ingrese un número válido");
        }
    }

    @FXML
    public void limpiar() {
        arbol.borrar();
        dibujar();
        txtSalida.clear();
    }

    @FXML
    public void mostrarInorden() {
        StringBuilder sb = new StringBuilder();
        arbol.inorden(sb);
        txtSalida.setText("Inorden: " + sb.toString());
    }

    @FXML
    public void mostrarPreorden() {
        StringBuilder sb = new StringBuilder();
        arbol.preorden(sb);
        txtSalida.setText("Preorden: " + sb.toString());
    }

    @FXML
    public void mostrarPostorden() {
        StringBuilder sb = new StringBuilder();
        arbol.postorden(sb);
        txtSalida.setText("Postorden: " + sb.toString());
    }

    @FXML
    public void mostrarAltura() {
        txtSalida.setText("Altura del árbol: " + arbol.obtenerAltura());
    }

    @FXML
    public void mostrarHojas() {
        txtSalida.setText("Número de hojas: " + arbol.contarHojas());
    }

    private void dibujar() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        dibujarNodo(gc, arbol.getRaiz(), canvas.getWidth() / 2, 30, canvas.getWidth() / 4);
    }

    private void dibujarNodo(GraphicsContext gc, Nodo nodo, double x, double y, double offset) {
        if (nodo == null) return;

        gc.setFill(Color.LIGHTBLUE);
        gc.fillOval(x - 15, y - 15, 30, 30);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - 15, y - 15, 30, 30);
        gc.strokeText(Integer.toString(nodo.dato), x - 7, y + 5);

        if (nodo.izquierdo != null) {
            gc.strokeLine(x, y, x - offset, y + 50);
            dibujarNodo(gc, nodo.izquierdo, x - offset, y + 50, offset / 2);
        }
        if (nodo.derecho != null) {
            gc.strokeLine(x, y, x + offset, y + 50);
            dibujarNodo(gc, nodo.derecho, x + offset, y + 50, offset / 2);
        }
    }
}
