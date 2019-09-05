package pv.portafolioverde.ftsapp;

public class Gastos {

    String urlImagenGasto,proveedorGasto,razonSocial,conceptoGasto,fechaGasto,totalGasto;
    public Gastos(){    }

    public Gastos(String urlImagenGasto, String proveedorGasto, String razonSocial, String conceptoGasto, String fechaGasto, String totalGasto) {
        this.urlImagenGasto = urlImagenGasto;
        this.proveedorGasto = proveedorGasto;
        this.razonSocial = razonSocial;
        this.conceptoGasto = conceptoGasto;
        this.fechaGasto = fechaGasto;
        this.totalGasto = totalGasto;
    }

    public String getUrlImagenGasto() {
        return urlImagenGasto;
    }

    public void setUrlImagenGasto(String urlImagenGasto) {
        this.urlImagenGasto = urlImagenGasto;
    }

    public String getProveedorGasto() {
        return proveedorGasto;
    }

    public void setProveedorGasto(String proveedorGasto) {
        this.proveedorGasto = proveedorGasto;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getConceptoGasto() {
        return conceptoGasto;
    }

    public void setConceptoGasto(String conceptoGasto) {
        this.conceptoGasto = conceptoGasto;
    }

    public String getFechaGasto() {
        return fechaGasto;
    }

    public void setFechaGasto(String fechaGasto) {
        this.fechaGasto = fechaGasto;
    }

    public String getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(String totalGasto) {
        this.totalGasto = totalGasto;
    }
}
