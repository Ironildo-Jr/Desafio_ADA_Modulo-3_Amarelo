package br.ada.sayajins;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ada.sayajins.dao.PagamentoDao;
import br.ada.sayajins.model.Pagamentos;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        List<Pagamentos> pagamentos = new ArrayList<>();

        pagamentos = new PagamentoDao().lerPagamentos();

        pagamentos.stream().forEach(System.out::println);
        pagamentos.stream().forEach((pg) -> new PagamentoDao().escreverPagamento(pg));
    }
}
