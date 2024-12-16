package gestiondedatos;

/**
 *
 * @author LUISA LUCIO && VALENTINA RUBIO
 */
public class Elemento {
    double precio;

    public Elemento(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Elemento{" + "precio=" + precio + '}';
    }
}
