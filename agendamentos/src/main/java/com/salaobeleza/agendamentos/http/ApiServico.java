package com.salaobeleza.agendamentos.http;

import com.salaobeleza.agendamentos.dtos.ServicoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("servico")
public interface ApiServico {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/{id}")
    ServicoResponse getServico(@PathVariable String id);
}
