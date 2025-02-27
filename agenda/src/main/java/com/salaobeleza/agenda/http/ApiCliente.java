package com.salaobeleza.agenda.http;

import com.salaobeleza.agenda.dtos.ClienteResponse;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("cliente")
public interface ApiCliente {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/{id}")
    ClienteResponse getCliente(@PathVariable String id);
}
