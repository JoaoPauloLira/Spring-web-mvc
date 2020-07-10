package br.com.newtec.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.newtec.model.Produto;


public class ProdutoValidation implements Validator {
	
	public boolean supports(Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		//ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "observacao", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "precos[0].valor", "field.required");
		
//		Produto produto = (Produto) target;
//		if(produto.getDescricao().length() <= 0) {
//			errors.rejectValue("descricao", "field.required");
//		}		
	}

}
