package service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.entity.Contato;
import model.entity.transferobject.TOContato;
import repository.factory.Factory;

public class ContatoSBean {

	public ContatoSBean() {
	}

	/*
	 * Salva contato
	 */
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
	 * Recebe Contato e verifica se existe ou não antes de efetivar remoção
	 */
	public void remover(TOContato tocontato) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		Contato contato = entityManager.find(Contato.class, tocontato.getId());
		if (contato != null) {
			transaction.begin();
			entityManager.remove(contato);
			transaction.commit();
		}

		entityManager.close();
	}
	
	public boolean verificaContatoVinculado(TOContato tocontato) {
		EntityManager entityManager = Factory.getEntityManager();
		Contato contato = entityManager.find(Contato.class, tocontato.getId());
		TypedQuery<Long> query = entityManager.createQuery(
				"SELECT COUNT(c) FROM Cliente c JOIN c.contatos ct WHERE ct = :contatoAVerificar", Long.class);
		query.setParameter("contatoAVerificar", contato);
		return query.getSingleResult() > 0;
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
