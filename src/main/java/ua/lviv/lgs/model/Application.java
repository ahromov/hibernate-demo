package ua.lviv.lgs.model;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class Application {

	static Session sessionObj;

	public static void main(String[] args) {
		Configuration configObj = new Configuration().configure();
		sessionObj = configObj.buildSessionFactory().openSession();
		
		sessionObj.beginTransaction();

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

			sessionObj.save(cartObj);
		}

		System.out.println("\n.......Records Saved Successfully To The Database.......\n");

		sessionObj.getTransaction().commit();

		sessionObj.close();
	}

}
