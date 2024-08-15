package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.entity.Cliente;
import model.entity.Contato;
import model.entity.transferobject.TOCliente;
import model.entity.transferobject.TOContato;
import repository.BancoDB;
import repository.factory.Factory;
import utils.TOtoEntityConverter;

public class ClienteSBean {

	private BancoDB repository;

	public ClienteSBean() {
		repository = BancoDB.getInstance();
	}

	public void salvarCliente(TOCliente tocliente) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Cliente cliente = new Cliente();
		cliente.setNomeCompleto(tocliente.getNomeCompleto());
		cliente.setTelefone1(tocliente.getTelefone1());
		cliente.setTelefone2(tocliente.getTelefone2());
		cliente.setDataRegistro(tocliente.getDataRegistroCliente());
		List<Contato> contatos = new ArrayList<>();
		for (TOContato toContato : tocliente.getContatos()) {
			Contato contato = new Contato();
			contato.setId(toContato.getId());
			contato.setNomeCompleto(toContato.getNomeCompleto());
			contatos.add(contato);
		}
		cliente.setContatos(new HashSet<Contato>(contatos));
		cliente.setEmails(tocliente.getEmails());
		entityManager.merge(cliente);
		entityTransaction.commit();
		entityManager.close();
	}

	/*
	 * Encontra clientes por nome completo e chave de acesso
	 */
	public TOCliente encontrarCliente(String nomeCompleto, String chaveAcesso) {
		return (TOCliente) repository.getClientes().stream()
				.filter(c -> c.getNomeCompleto().equals(nomeCompleto) && c.getTelefone1().equals(chaveAcesso));
	}

	public void atualizarCliente(TOCliente tocliente) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Cliente clienteExistente = entityManager.find(Cliente.class, tocliente.getId());
		clienteExistente.setNomeCompleto(tocliente.getNomeCompleto());
		clienteExistente.setTelefone1(tocliente.getTelefone1());
		clienteExistente.setTelefone2(tocliente.getTelefone2());
		clienteExistente.setDataRegistro(tocliente.getDataRegistroCliente());
		List<Contato> contatos = new ArrayList<>();
		for (TOContato toContato : tocliente.getContatos()) {
			Contato contato = new Contato();
			contato.setId(toContato.getId());
			contato.setNomeCompleto(toContato.getNomeCompleto());
			contatos.add(contato);
		}
		clienteExistente.setContatos(new HashSet<Contato>(contatos));
		clienteExistente.setEmails(tocliente.getEmails());
		entityManager.merge(clienteExistente);
        transaction.commit();
        entityManager.close();
	}

	/*
	 * Recebe cliente e verifica se existe ou não antes de efetivar remoção
	 */
	public void remover(TOCliente tocliente) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		Cliente cliente = entityManager.find(Cliente.class, tocliente.getId());
		transaction.begin();
		if (cliente != null) {
			entityManager.remove(cliente);
		}
		transaction.commit();
		entityManager.close();
	}

	/**
	 * @return Lista com todos os Clientes
	 */
	public List<TOCliente> listarClientes() {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		String jpql = "from Cliente";

		TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
		List<Cliente> clientes = query.getResultList();
		TOtoEntityConverter converter = new TOtoEntityConverter();
		entityTransaction.commit();
		entityManager.close();
		List<TOCliente> toClientes = new ArrayList<TOCliente>();
		for (Cliente cliente : clientes) {
			toClientes.add(new TOCliente(cliente.getId(), cliente.getNomeCompleto(), cliente.getTelefone1(),
					cliente.getTelefone2(), cliente.getDataRegistro(), cliente.getEmails(),
					converter.convertEntityToTO(cliente.getContatos())));
		}
		return toClientes;
	}

}
