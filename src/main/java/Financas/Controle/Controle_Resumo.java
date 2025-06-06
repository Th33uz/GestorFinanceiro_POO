package Financas.Controle;

import Financas.Estrutura.Estrutura_Categoria;
import Financas.Estrutura.Estrutura_Transacao;
import Financas.Util.FormatadorData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Controle_Resumo implements Interface_Controle_Resumo{

    private Controle_Categoria controleCategoria = new Controle_Categoria();
    private Controle_Transacao controleTransacao = new Controle_Transacao();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public List<Estrutura_Categoria> obterCategorias(int idUsuario){
        return controleCategoria.obterCategoriasPorUsuario(idUsuario);
    }

    public List<Estrutura_Transacao> obterTransacoes(int idUsuario){
        return controleTransacao.listarTransacoesPorUsuario(idUsuario);
    }

    public List<Estrutura_Transacao> filtrarTransacoes(List<Estrutura_Transacao> transacoes, Estrutura_Categoria categoriaSelecionada, String tipoSelecionado, String dataInicioStr, String dataFimStr){

        return transacoes.stream().filter(t -> {

            if(categoriaSelecionada != null && t.getCategoria() != null){
                if(t.getCategoria().getId() != categoriaSelecionada.getId()){
                    return false;
                }
            }

            if(!"Todos".equalsIgnoreCase(tipoSelecionado)){
                if(!t.getTipo().equalsIgnoreCase(tipoSelecionado)){
                    return false;
                }
            }

            if(!dataInicioStr.isEmpty()){
                if(!FormatadorData.validarData(dataInicioStr)){
                    return false;
                }
                try{
                    LocalDate dataInicio = LocalDate.parse(dataInicioStr, formatter);
                    LocalDate dataTransacao = LocalDate.parse(t.getDataTransacao(), formatter);
                    if(dataTransacao.isBefore(dataInicio)){
                        return false;
                    }
                }
                catch(Exception e){
                    return false;
                }
            }

            if(!dataFimStr.isEmpty()){
                if(!FormatadorData.validarData(dataFimStr)){
                    return false;
                }
                try{
                    LocalDate dataFim = LocalDate.parse(dataFimStr, formatter);
                    LocalDate dataTransacao = LocalDate.parse(t.getDataTransacao(), formatter);
                    if(dataTransacao.isAfter(dataFim)){
                        return false;
                    }
                }
                catch(Exception e){
                    return false;
                }
            }

            return true;
        })
        .collect(Collectors.toList());
    }

    public double calcularTotal(List<Estrutura_Transacao> transacoes, String tipo){
        return transacoes.stream()
                .filter(t -> t.getTipo().equalsIgnoreCase(tipo))
                .mapToDouble(Estrutura_Transacao::getValor)
                .sum();
    }
}
