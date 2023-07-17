package Modelo;

public class DefineTable {
    public DefineTable() {}

    public static abstract class Ventas {
        public static final String TABLE_NAME = "Ventas";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NUM_BOMBA = "num_bomba";
        public static final String COLUMN_NAME_TIPO_GASOLINA = "tipo_gasolina";
        public static final String COLUMN_NAME_PRECIO = "precio";
        public static final String COLUMN_NAME_CANTIDAD = "cantidad";

        public static String[] REGISTRO = new String[]{
                Ventas.COLUMN_NAME_ID,
                Ventas.COLUMN_NAME_NUM_BOMBA,
                Ventas.COLUMN_NAME_TIPO_GASOLINA,
                Ventas.COLUMN_NAME_PRECIO,
                Ventas.COLUMN_NAME_CANTIDAD
        };
    }
}
