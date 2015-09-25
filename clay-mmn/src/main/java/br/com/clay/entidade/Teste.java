package br.com.clay.entidade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Teste {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("clay_pu");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			Pessoa pessoa = new Pessoa();
			pessoa.setNomePessoa("Projeto 2");
			pessoa.setTipoPessoa(1);
			pessoa.setNumCpfCnpj("12345678900");

			em.persist(pessoa);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			emf.close();
		}

		System.out.println("Fim");
	}

}
