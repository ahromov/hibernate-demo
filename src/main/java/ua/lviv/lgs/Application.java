package ua.lviv.lgs;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import ua.lviv.lgs.model.Cart;
import ua.lviv.lgs.model.Item;

public class Application {

	static Session session;

	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure();
		session = configuration.buildSessionFactory().openSession();

		session.beginTransaction();

		for (int i = 0; i <= 4; i++) {
			Cart cart = new Cart();
			cart.setName("Editor " + i);
			cart.setTotal(i);

			Set<Item> items = cart.getItems();

			for (int j = 0; j < 4; j++) {
				Item item = new Item();
				item.setItemId(j);
				item.setItemTotal(j);
				item.setQuantity(j);
				items.add(item);
			}

			cart.setItems(items);

			session.save(cart);
		}

		System.out.println("\n.......Records Saved Successfully To The Database.......\n");

		session.getTransaction().commit();
		session.close();
	}

}
