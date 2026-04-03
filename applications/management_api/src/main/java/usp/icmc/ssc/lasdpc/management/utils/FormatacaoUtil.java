package usp.icmc.ssc.lasdpc.management.utils;

public class FormatacaoUtil {

    public static String imprimeCNPJ(String CNPJ) {
        return (
                CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "." +
                CNPJ.substring(5, 8) + "." + CNPJ.substring(8, 12) + "-" +
                CNPJ.substring(12, 14)
        );
    }

}
