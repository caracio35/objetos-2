package ejercio1.modelo;

public class Participante {

    private String nombre;
    private String numTelefono;
    private String region;

    public Participante(String nombre, String numTelefono, String region) {
        
        if (validarDatos(nombre, numTelefono, region)) {
            this.nombre = nombre;
            this.numTelefono = numTelefono;
            this.region = region;
        }
    }

    private boolean validarDatos(String nombre, String numTelefono, String region) {
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("Debe cargar un nombre");
        }
        if (numTelefono.isEmpty()) {
            throw new IllegalArgumentException("Debe cargar un teléfono");
        }
        if (!validarTelefono(numTelefono)) {
            throw new IllegalArgumentException("El teléfono debe ingresarse de la siguiente forma: NNNN-NNNNNN");
        }
        if (!region.equals("China") && !region.equals("US")
                && !region.equals("Europa")) {
            throw new IllegalArgumentException("Región desconocida. Las conocidas son: China, US, Europa");
        }
        return true;
    }

    public String cualEsTuNombre() {
        return nombre;
    }

    public String cualEsTuNumTelefono() {
        return numTelefono;
    }

    public String cualEsTuReguion() {
        return region;
    }       
    private boolean validarTelefono(String telefono) {
        String regex = "\\d{4}-\\d{6}";
        return telefono.matches(regex);
    }
}
