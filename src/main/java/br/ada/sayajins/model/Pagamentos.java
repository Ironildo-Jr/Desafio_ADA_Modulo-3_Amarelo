package br.ada.sayajins.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.threeten.extra.LocalDateRange;

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

            LocalDateRange ldr = LocalDateRange.of(dtVencto, LocalDate.now());

            Long monthsBetween = ldr.toPeriod().toTotalMonths();

            if (tipoPagamentoEnum.equals(TipoPagamentoEnum.CREDITO)) {
                return this.valor.multiply((new BigDecimal("1"))
                        .add(new BigDecimal("0.03").multiply(new BigDecimal(monthsBetween.toString()))));
            } else if (tipoPagamentoEnum.equals(TipoPagamentoEnum.DEBITO)) {
                return this.valor.multiply((new BigDecimal("1"))
                        .add(new BigDecimal("0.01").multiply(new BigDecimal(monthsBetween.toString()))));
            } else if (tipoPagamentoEnum.equals(TipoPagamentoEnum.BOLETO)) {
                return this.valor.multiply((new BigDecimal("1"))
                        .add(new BigDecimal("0.05").multiply(new BigDecimal(monthsBetween.toString()))));
            }

        } else {

            LocalDateRange ldr = LocalDateRange.of(LocalDate.now(), dtVencto);

            Integer daysBetween = ldr.lengthInDays();

            if (tipoPagamentoEnum.equals(TipoPagamentoEnum.PIX)) {
                return this.valor.multiply((new BigDecimal("1"))
                        .subtract(new BigDecimal("0.005").multiply(new BigDecimal(daysBetween.toString()))));
            } else if (tipoPagamentoEnum.equals(TipoPagamentoEnum.FIDELIDADE)) {
                return this.valor.multiply((new BigDecimal("1"))
                        .subtract(new BigDecimal("0.005").multiply(new BigDecimal(daysBetween.toString()))));
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
