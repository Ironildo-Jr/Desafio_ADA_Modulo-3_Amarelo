package br.ada.sayajins.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Pagamentos {
    private String nome;

    private LocalDate dtVencto;

    public TipoPagamentoEnum getTipoPagamentoEnum() {
        return tipoPagamentoEnum;
    }

    public void setTipoPagamentoEnum(TipoPagamentoEnum tipoPagamentoEnum) {
        this.tipoPagamentoEnum = tipoPagamentoEnum;
    }

    private BigDecimal valor;

    private TipoPagamentoEnum tipoPagamentoEnum;

    public LocalDate getDtVencto() {
        return dtVencto;
    }

    public void setDtVencto(LocalDate dtVencto) {
        this.dtVencto = dtVencto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String v) {
        this.nome = v;
    }

    public Pagamentos(String s) {
        setNome(s);
    }

    public BigDecimal getValorTotal() {

        // Duration duracao = Duration.between(this.dtVencto,LocalDateTime.now());

        if (this.dtVencto.isBefore(LocalDate.now())) {

            Long monthsBetween = ChronoUnit.MONTHS.between(
                    YearMonth.from(this.dtVencto),
                    YearMonth.from(LocalDateTime.now()));

            System.out.println(monthsBetween); // 3

            if (tipoPagamentoEnum.equals(TipoPagamentoEnum.CREDITO)) {
                return this.valor.multiply((new BigDecimal("1"))
                        .add(new BigDecimal("0.03").multiply(new BigDecimal(monthsBetween.toString()))));
            }
        }
        return this.valor;
    }

    public Pagamentos(String s, LocalDate dateVencto, double valor, TipoPagamentoEnum tipoPagamentoEnum) {
        this.valor = BigDecimal.valueOf(valor);
        this.nome = s;
        this.dtVencto = dateVencto;
        this.tipoPagamentoEnum = tipoPagamentoEnum;
    }

    public String getValorFormatado() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatter.format(this.valor.doubleValue());
    }

    public String getValorTotalFormatado() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatter.format(this.getValorTotal().doubleValue());
    }

    @Override
    public String toString() {
        return this.nome
                .concat("   |   ")
                .concat(this.dtVencto.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")))
                .concat("   |   ")
                .concat(this.getValorFormatado())
                .concat("   |   ")
                .concat(this.getValorTotalFormatado())
                .concat("   |   ")
                .concat(this.getTipoPagamentoEnum().name());
    }
}
