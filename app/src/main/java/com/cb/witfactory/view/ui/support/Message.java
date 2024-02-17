package com.cb.witfactory.view.ui.support;

public class Message {
    String mensaje;
    String user;
    String fecha;


    public Message(String mensaje, String user, String fecha) {
        this.mensaje = mensaje;
        this.user = user;
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
