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
			Cart cartObj = new Cart();
			cartObj.setName("Editor " + i);
			cartObj.setTotal(i);

			Set<Item> items = cartObj.getItems();

			for (int j = 0; j < 4; j++) {
				Item item = new Item();
				item.setItemId(j);
				item.setItemTotal(j);
				item.setQuantity(j);
				items.add(item);
			}

			cartObj.setItems(items);

			session.save(cartObj);
		}

		System.out.println("\n.......Records Saved Successfully To The Database.......\n");

		session.getTransaction().commit();

		session.close();
	}

}
