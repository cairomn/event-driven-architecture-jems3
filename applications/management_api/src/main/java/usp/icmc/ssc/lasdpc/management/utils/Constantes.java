package usp.icmc.ssc.lasdpc.management.utils;

public class Constantes {

    // GERAIS
    public static final int HTTP_CODE_OK = 200;
    public static final int HTTP_CODE_CREATED = 201;
    public static final int HTTP_CODE_NO_CONTENT = 204;
    public static final int HTTP_CODE_NOT_FOUND = 404;
    public static final int HTTP_CODE_UNPROCESSABLE_ENTITY = 422;
    public static final String MSG_RESP_CONSULTA = "Consulta realizada com sucesso.";
    public static final String MSG_RESP_CADASTRO = "Cadastro realizada com sucesso.";
    public static final String MSG_RESP_ALTERACAO = "Alteração realizada com sucesso.";
    public static final String MSG_RESP_REMOVIDO = "Remoção realizada com sucesso.";
    public static final String MSG_NAO_ENCONTRADO = "Registro não encontrado.";
    public static final String MSG_RESP_PUBLICADO = "Ação publicada.";

    // TOPICS
    public static final String PUBLISH_MICROCONTROLADOR_SALVO_TOPIC = "microcontrolador-salvo";

    public static final String PUBLISH_ON_OFF_AIR_COND_ACTION_TOPIC = "dispositivo-acao";
    public static final String PUBLISH_CHANGE_TEMP_AIR_COND_ACTION_TOPIC = "dispositivo-acao";
    public static final String PUBLISH_CHANGE_MODE_AIR_COND_ACTION_TOPIC = "dispositivo-acao";

    // ORDENACAO
    public static final String NUMERO_PAGINA_PADRAO = "0";
    public static final String TAMANHO_PAGINA_PADRAO = "10";
    public static final String ORDENACAO_PADRAO = "desc";
    public static final String COLUNA_ORDENACAO_PADRAO = "id";

    // ATUADOR
    public static final String MSG_ATUADOR_CAMPO_OBRIGATORIO_APELIDO = "Apelido é um campo obrigatório.";
    public static final String MSG_ATUADOR_CAMPO_OBRIGATORIO_TIPO_ATUADOR = "Tipo do Atuador é um campo obrigatório.";
    public static final String MSG_ATUADOR_CAMPO_OBRIGATORIO_MICROCONTROLADOR = "Microcontrolador é um campo obrigatório.";

    // BLOCO
    public static final String MSG_BLOCO_CAMPO_OBRIGATORIO_NOME = "Nome é um campo obrigatório.";
    public static final String MSG_BLOCO_CAMPO_OBRIGATORIO_iNSTITUICAO = "Instituição é um campo obrigatório.";
    public static final String MSG_BLOCO_NOME_CADASTRADO_INSTITUICAO = "Nome do bloco já está cadastrado na instituição.";

    // INSTITUICAO
    public static final String MSG_INSTITUICAO_CAMPO_OBRIGATORIO_NOME = "Nome não informado.";
    public static final String MSG_INSTITUICAO_CAMPO_OBRIGATORIO_CNPJ = "CNPJ não informado.";
    public static final String MSG_INSTITUICAO_CAMPO_VALIDO_CNPJ = "CNPJ apresentado com valor incorreto.";
    public static final String MSG_INSTITUICAO_NOME_CADASTRADO_INSTITUICAO = "Nome da Instituicao já cadastrado no sistema.";

    // MICROCONTROLADOR
    public static final String MSG_MICROCONTROLADOR_INFORME_ATUADOR_OU_SENSOR = "É necessário informar ao menos sensor ou atuador.";

    // PISO
    public static final String MSG_PISO_CAMPO_OBRIGATORIO_NOME = "Nome não informado.";
    public static final String MSG_PISO_CAMPO_OBRIGATORIO_BLOCO = "Bloco não informado.";
    public static final String MSG_PISO_CAMPO_OBRIGATORIO_NOME_CADASTRADO_BLOCO = "Nome do piso já está cadastrado no bloco.";

    // SALA
    public static final String MSG_SALA_CAMPO_OBRIGATORIO_NOME = "Nome não informado.";
    public static final String MSG_SALA_CAMPO_OBRIGATORIO_PISO = "Piso não informado.";
    public static final String MSG_SALA_CAMPO_OBRIGATORIO_NOME_CADASTRADO_PISO = "Nome do sala já está cadastrado no piso.";

    // SENSOR
    public static final String MSG_SENSOR_CAMPO_OBRIGATORIO_APELIDO = "Apelido é um campo obrigatório.";
    public static final String MSG_SENSOR_CAMPO_OBRIGATORIO_TIPO_ATUADOR = "Tipo do Sensor é um campo obrigatório.";
    public static final String MSG_SENSOR_CAMPO_OBRIGATORIO_MICROCONTROLADOR = "Microcontrolador é um campo obrigatório.";
    
}
