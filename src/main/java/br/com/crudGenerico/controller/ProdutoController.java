package br.com.crudGenerico.controller;

import br.com.crudGenerico.models.Produto;
import br.com.crudGenerico.service.ProdutoService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

/**
 *
 * @author gianlucampos
 */
@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    private static final List<Produto> DADOS = new ArrayList<>(Arrays.asList(
            new Produto(1L, "Smartphone Samsung Galaxy On 7", new BigDecimal(849.99)),
            new Produto(2L, "Geladeira Electrolux Frost Free", new BigDecimal(1947.50)),
            new Produto(3L, "Purificador de Água Electrolux", new BigDecimal(533.30)),
            new Produto(4L, "Smart Watch Relogio Bluetooth", new BigDecimal(64.99)),
            new Produto(5L, "Smart TV LED 32\" Samsung", new BigDecimal(1249.00)),
            new Produto(6L, "Kit Pneu Aro 14 Dunlop 175/65r14", new BigDecimal(759.60))));

    @GetMapping("/")
    public ModelAndView listaProdutos(Produto produto) {
        ModelAndView model = new ModelAndView("/produtos-list");
        List<Produto> lista = DADOS.stream().filter(p -> produto.getId() == null || produto.getId().equals(p.getId()))
                .filter(p -> StringUtils.isEmpty(produto.getNome()) || p.getNome().startsWith(produto.getNome()))
                .collect(Collectors.toList());
        model.addObject("produtos", lista);
        return model;
    }

    @GetMapping("/produtos")
    public ModelAndView findAll(Produto produto) {
        System.out.println(produto);
        ModelAndView model = new ModelAndView("produtos-list");
        model.addObject("produtos", service.findAll());
        return model;
    }


}
