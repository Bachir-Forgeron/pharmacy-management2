package fr.sup.galilee.pharmacy;

import fr.sup.galilee.pharmacy.entities.*;
import fr.sup.galilee.pharmacy.entities.enums.UserProfile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.Scanner;

import static org.hibernate.cfg.PersistenceSettings.PERSISTENCE_UNIT_NAME;

@SpringBootApplication

public class PharmacyManagementApplication {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Entrez votre prénom : ");
		String firstName = scanner.nextLine();
		System.out.println("Entrez votre nom de famille : ");
		String lastName = scanner.nextLine();
		System.out.println("Entrez votre adresse e-mail : ");
		String email = scanner.nextLine();
		System.out.println("Entrez votre mot de passe : ");
		String password = scanner.nextLine();

		User user = new User();
		user.setFirstname(firstName);
		user.setLastname(lastName);
		user.setEmail(email);
		user.setPassword(password);
		user.setActive(true);
		user.setProfile(UserProfile.USER);

		Cart cart = new Cart();
		cart.setUser(user);

		try {
			tx.begin();
			em.persist(user);
			em.persist(cart);

			System.out.println("Bienvenue dans notre magasin !");
			boolean continueShopping = true;
			while (continueShopping) {
				System.out.println("Veuillez sélectionner un produit par son ID : ");
				long productId = scanner.nextLong();
				scanner.nextLine();
				System.out.println("Entrez la quantité : ");
				int quantity = scanner.nextInt();
				scanner.nextLine();
				Product product = em.find(Product.class, productId);
				if (product != null) {

					CartProduct cartProduct = new CartProduct();
					cartProduct.setProduct(product);
					cartProduct.setQuantity(quantity);
					cartProduct.setCart(cart);
					em.persist(cartProduct);
				} else {
					System.out.println("Produit introuvable !");
				}

				System.out.println("Souhaitez-vous continuer vos achats ? (Oui/Non) : ");
				String choice = scanner.nextLine();
				continueShopping = choice.equalsIgnoreCase("Oui");
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
			scanner.close();
		}
	}
}

