package br.ada.sayajins.model;

import java.math.BigDecimal;

public enum TipoPagamentoEnum {
    FIDELIDADE {

        @Override

        BigDecimal calcularJuros() {

            return new BigDecimal("0.005");

        }

    },

    CREDITO {

        @Override

        BigDecimal calcularJuros() {

            return new BigDecimal("0.03");

        }

    },

    DEBITO {

        @Override

        BigDecimal calcularJuros() {

            return new BigDecimal("0.01");

        }

    },

    BOLETO {

        @Override

        BigDecimal calcularJuros() {

            return new BigDecimal("0.05");

        }

    },

    PIX {

        @Override

        BigDecimal calcularJuros() {

            return new BigDecimal("0.005");

        }
    };

    abstract BigDecimal calcularJuros();
}
