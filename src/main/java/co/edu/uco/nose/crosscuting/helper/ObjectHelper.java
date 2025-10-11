package co.edu.uco.nose.crosscuting.helper;

public final class ObjectHelper {

    private ObjectHelper() {
    }

    //Generalizamos con 'O' para decir que el tipo de dato que le mande, va a tomar dicho objeto, lo que hace que lo usemos en muchas partes
    public static <O> boolean isNull(O object) {
        return object == null;
    }

    //A esto se le llama operador ternario, me ahorra mucha logica en vez de usar if y else
    //Si este objeto es nulo, SI retorne el valor por defecto, NO retorne el objecto
    public static <O> O getDefault(O object, O defaultValue) {
        return (isNull(object) ? defaultValue : object);
    }

}
