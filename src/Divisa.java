public class Divisa {
    private String codigo;
    private double tasaDeCambio;

    public Divisa (String codigo, double tasaDeCambio) {
        this.codigo = codigo;
        this.tasaDeCambio = tasaDeCambio;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getTasaDeCambio() {
        return tasaDeCambio;
    }
}
