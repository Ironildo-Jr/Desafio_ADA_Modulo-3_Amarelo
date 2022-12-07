package br.ada.sayajins;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.ArrayList;

import br.ada.sayajins.dao.PagamentoDao;
import br.ada.sayajins.model.Pagamentos;
import br.ada.sayajins.model.TipoPagamentoEnum;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        List<Pagamentos> pagamentos = new ArrayList<>();

        pagamentos = new PagamentoDao().lerPagamentos();

        pagamentos.stream().forEach(System.out::println);
        pagamentos.stream().forEach((pg) -> new PagamentoDao().escreverPagamento(pg));
    }
}
