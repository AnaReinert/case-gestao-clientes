package service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.entity.Contato;
import model.entity.transferobject.TOContato;
import repository.BancoDB;
import repository.factory.Factory;

public class ContatoSBean {

	private BancoDB repository;


	public ContatoSBean() {
		repository = BancoDB.getInstance();
	}

	public void salvarContato(TOContato toContato) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Contato contato = new Contato();
		contato.setNomeCompleto(toContato.getNomeCompleto());
		contato.setTelefone1(toContato.getTelefone1());
		contato.setTelefone2(toContato.getTelefone2());
		contato.setEmails(toContato.getEmails());
		entityManager.persist(contato);
		entityTransaction.commit();
		entityManager.close();
	}

	/*
	 * Encontra Contatos por nome completo e chave de acesso
	 */
	public TOContato encontrarContato(String nomeCompleto, String chaveAcesso) {
		return (TOContato) repository.getContatos().stream()
				.filter(c -> c.getNomeCompleto().equals(nomeCompleto) && c.getTelefone1().equals(chaveAcesso));
	}

	/*
	 * Recebe Contato e verifica se existe ou não antes de efetivar remoção
	 */
	public void remover(TOContato tocontato) {
		EntityManager entityManager = Factory.getEntityManager();
	    EntityTransaction transaction = entityManager.getTransaction();
		Contato contato = entityManager.find(Contato.class, tocontato.getId());
		transaction.begin();
		if(contato != null) {
			entityManager.remove(contato);
		}
		transaction.commit();
		entityManager.close();
	}
	
	/*
	 * Atualiza o Contato com as novas informações informadas
	 */
	public void atualizarContatos(TOContato tocontato) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		Contato contatoExistente = entityManager.find(Contato.class, tocontato.getId());
		contatoExistente.setNomeCompleto(tocontato.getNomeCompleto());
		contatoExistente.setTelefone1(tocontato.getTelefone1());
		contatoExistente.setTelefone2(tocontato.getTelefone2());
		contatoExistente.setEmails(tocontato.getEmails());
		transaction.begin();
		entityManager.merge(contatoExistente);
        transaction.commit();
        entityManager.close();
	}

	/**
	 * @return Lista com todos os Contatos
	 */
	public List<TOContato> listarContatos() {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		String jpql = "from Contato";

		TypedQuery<Contato> query = entityManager.createQuery(jpql, Contato.class);
		List<Contato> contatos = query.getResultList();
		entityTransaction.commit();
		entityManager.close();
		List<TOContato> toContatos = new ArrayList<TOContato>();
		for (Contato contato : contatos) {
			toContatos.add(new TOContato(contato.getId(), contato.getNomeCompleto(), contato.getTelefone1(),
					contato.getTelefone2(), contato.getEmails()));
		}
		return toContatos;
	}

}
