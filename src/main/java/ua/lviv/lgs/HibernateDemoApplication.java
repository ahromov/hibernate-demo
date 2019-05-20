package ua.lviv.lgs;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import ua.lviv.lgs.model.Cart;
import ua.lviv.lgs.model.Item;

public class HibernateDemoApplication {

	private static Session session;

	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure();
		session = configuration.buildSessionFactory().openSession();

		session.beginTransaction();
		Cart cart1 = new Cart("New Cart");
		session.save(cart1);
		session.save(new Item(2, cart1));
		session.save(new Item(2, cart1));
		session.getTransaction().commit();

		session.beginTransaction();
		Cart cart2 = new Cart("New Cart");
		session.save(cart2);
		session.save(new Item(2, cart2));
		session.save(new Item(2, cart2));
		session.getTransaction().commit();

		session.beginTransaction();
		List<Cart> carts = loadAllData(Cart.class, session);
		session.getTransaction().commit();
		carts.forEach(cart -> System.out.println(cart.getName()));

		cart1.setName("NEW NAME");
		
		session.beginTransaction();
		session.saveOrUpdate(cart1);
		List<Cart> carts1 = loadAllData(Cart.class, session);
		session.getTransaction().commit();
		
		carts1.forEach(cart -> System.out.println(cart.getName()));

		session.beginTransaction();
		session.delete(cart1);
		session.getTransaction().commit();
		
		List<Cart> carts2 = loadAllData(Cart.class, session);
		carts2.forEach(cart -> System.out.println(cart.getName()));

		session.close();
	}

	private static <T> List<T> loadAllData(Class<T> type, Session session) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(type);
		criteria.from(type);
		List<T> data = session.createQuery(criteria).getResultList();

		return data;
	}

}
