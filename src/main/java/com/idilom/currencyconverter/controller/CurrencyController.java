package com.idilom.currencyconverter.controller;

import com.idilom.currencyconverter.service.Services;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CurrencyController {

    @GetMapping("/convert")
    public Map<String, Object> convert(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            double rate = Services.getConversionRate(from, to);

            if (rate == -1) {
                response.put("error", "Moeda de destino inválida.");
                return response;
            }

            double result = amount * rate;

            response.put("from", from);
            response.put("to", to);
            response.put("rate", rate);
            response.put("amount", amount);
            response.put("converted", result);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", "Erro interno no servidor: " + e.getMessage());
        }

        return response;
    }


}
