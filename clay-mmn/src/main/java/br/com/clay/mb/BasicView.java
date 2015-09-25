package br.com.clay.mb;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.com.clay.entidade.Pessoa;
 
@ManagedBean(name="treeBasicView")
@ViewScoped
public class BasicView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeNode root;
     
    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Root", null);
        TreeNode node0 = new DefaultTreeNode("Node 0", root);
        TreeNode node1 = new DefaultTreeNode("Node 1", root);
         
        TreeNode node00 = new DefaultTreeNode("Node 0.0", node0);
        TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);
         
        TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);
         
        node1.getChildren().add(new DefaultTreeNode("Node 1.1"));
        node00.getChildren().add(new DefaultTreeNode("Node 0.0.0"));
        node00.getChildren().add(new DefaultTreeNode("Node 0.0.1"));
        node01.getChildren().add(new DefaultTreeNode("Node 0.1.0"));
        node10.getChildren().add(new DefaultTreeNode("Node 1.0.0"));
//        root.getChildren().add(new DefaultTreeNode("Node 2"));
        
        testa2();
    }
 
    public TreeNode getRoot() {
        return root;
    }
    
    private void testa2() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("Hello");
		EntityManager em = emf.createEntityManager();
		try {
			Pessoa pessoa = new Pessoa();
			pessoa.setNomePessoa("Projeto 4");
			pessoa.setTipoPessoa(1);

			em.getTransaction().begin();
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