package br.com.newtec.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
public class Usuario implements UserDetails {


	private static final long serialVersionUID = 1L;

	@Id
	private String email;
	private String senha;
	private String nome;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST) // já traz ele carregado usando o cascate ele já pesssiste as roles quando ele pessistir o usuario tb
	private List<Role> roles = new ArrayList<Role>();
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		String salt = BCrypt.gensalt(); //Vai gerar uma chave única de encriptação com 29 caracteres
		this.senha = BCrypt.hashpw(senha, salt);
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}
	public String getPassword() {
		return this.senha;
	}
	public String getUsername() {
		return this.email;
	}
	public boolean isAccountNonExpired() {
		return true;
	}
	public boolean isAccountNonLocked() {
		return true;
	}
	public boolean isCredentialsNonExpired() {
		return true;
	}
	public boolean isEnabled() {
		return true;
	}
	
	
	
}
