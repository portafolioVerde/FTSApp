package pv.portafolioverde.ftsapp;

public class Actividades {
    public String idAct,estado,fechaActividad,nombreActividad,numeroProjecto,tipoActividad,usuario,valorPresupuesto;
    public Actividades(){ }
    //public Actividades(String itm) { }

    public Actividades(String idAct,String estado, String fechaActividad, String nombreActividad,String numeroProjecto, String tipoActividad, String usuario, String valorPresupuesto) {
        this.idAct = idAct;
        this.estado = estado;
        this.fechaActividad = fechaActividad;
        this.nombreActividad = nombreActividad;
        this.numeroProjecto = numeroProjecto;
        this.tipoActividad = tipoActividad;
        this.usuario = usuario;
        this.valorPresupuesto = valorPresupuesto;
    }

    public String getIdAct() {
        return idAct;
    }

    public void setIdAct(String idAct) {
        this.idAct = idAct;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaActividad() {
        return fechaActividad;
    }

    public void setFechaActividad(String fechaActividad) {
        this.fechaActividad = fechaActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getValorPresupuesto() {
        return valorPresupuesto;
    }

    public void setValorPresupuesto(String valorPresupuesto) {
        this.valorPresupuesto = valorPresupuesto;
    }


    public String getNumeroProjecto() {
        return numeroProjecto;
    }

    public void setNumeroProjecto(String numeroProjecto) {
        this.numeroProjecto = numeroProjecto;
    }
}
