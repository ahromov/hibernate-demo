package ua.lviv.lgs;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import ua.lviv.lgs.model.Cart;
import ua.lviv.lgs.model.Item;

public class Application {

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
		
		session.close();
	}

}
