package br.ada.sayajins.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.ada.sayajins.model.Pagamentos;
import br.ada.sayajins.model.TipoPagamentoEnum;

public class PagamentoDao {

    public List<Pagamentos> lerPagamentos(){
        List<Pagamentos> pagamentos = new ArrayList<>();

        File pagamentosFile = new File("src/main/resources/pagamentos.csv");

        if (pagamentosFile.exists()) {
            try (var reader = new BufferedReader(new FileReader(pagamentosFile))) {
                String line = null;
                reader.readLine();
                while ((line = reader.readLine()) != null) {
                    List<String> info = List.of(line.split(";"));
                    String nome = info.get(0);
                    DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyyMMdd");
                    LocalDate DataVenc = LocalDate.parse(info.get(2), formatoData);
                    Double valor = Double.parseDouble(info.get(3));
                    TipoPagamentoEnum tipo = TipoPagamentoEnum.valueOf(info.get(1));
                    Pagamentos novoPagamento = new Pagamentos(nome, DataVenc, valor, tipo);
                    pagamentos.add(novoPagamento);
                }
            }catch (IOException e){
                
            }
        }
        
        return pagamentos;
    }

    public void escreverPagamento(Pagamentos novoPagamento){
        String caminho = String.format("src/main/resources/PAGAMENTOS_%s_%s.csv",
        novoPagamento.getTipoPagamentoEnum(), LocalDate.now());

        File arquivo = new File(caminho);

        try (var writer = new PrintWriter(new FileWriter(arquivo, true))){
            writer.println(String.format("%s;%s;%s;%s", novoPagamento.getNome(),
            novoPagamento.getTipoPagamentoEnum(), novoPagamento.getDtVencto(),
            novoPagamento.getValor()));
        }catch (IOException e){
            
        }
    }

}
