package pe.com.veriinfo.model;

import java.util.Arrays;

public enum TipoDocumentoEnum {
    // @formatter:off
    DNI("DNI","LIBRETA ELECTORAL O DNI"),
    PASAPORTE("PASAPORTE","PASAPORTE"),
    RUC("CE", "CARNET DE EXTRANJERIA");
    // @@formatter:on

    private String tipo;
    private String descripcion;

    TipoDocumentoEnum(String tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoDocumentoEnum buscarXtipo(String tipo) {
        return Arrays.stream(values()).filter(t -> t.getTipo().equalsIgnoreCase(tipo)).findAny().orElse(null);
    }
}
