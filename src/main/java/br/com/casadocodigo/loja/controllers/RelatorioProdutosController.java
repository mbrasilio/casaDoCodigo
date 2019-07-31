package br.com.casadocodigo.loja.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@RestController
public class RelatorioProdutosController {

	@Autowired
	private ProdutoDAO dao;

	@RequestMapping(value = "/relatorio-produtos", method = RequestMethod.GET)
	@ResponseBody
	public List<Produto> relatorioProdutosDataJSON(@RequestParam(value = "data", required = false) String dataString)
			throws ParseException {

		if (dataString != null) {
			SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd");
			Date dataFormatada = formataData.parse(dataString);
			Calendar data = Calendar.getInstance();
			data.setTime(dataFormatada);

			return dao.listaPorData(data);
		} else {
			return dao.listaPorData(null);
		}
	}

}
