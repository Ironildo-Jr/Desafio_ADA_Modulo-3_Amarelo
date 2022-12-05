package br.ada.sayajins;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.ArrayList;

import br.ada.sayajins.model.Pagamentos;
import br.ada.sayajins.model.TipoPagamentoEnum;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
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
                    pagamentos.add(new Pagamentos(nome, DataVenc, valor, tipo));
                }
            }
            pagamentos.stream().forEach(System.out::println);
        }

        
    }

}
