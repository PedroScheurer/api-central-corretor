package br.edu.atitus.api_example.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record VendaDTO(UUID idCliente, UUID idImovel, BigDecimal valor, BigDecimal entrada, BigDecimal desconto,
		BigDecimal acrescimo, BigDecimal comissao, String formaPagamento, int numeroParcelas) {

}
