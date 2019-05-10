package com.cart.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.cart.model.Product;

/**
 * @author vincent
 *
 */
public class HibernateUtil {
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

				Map<String, Object> settings = new HashMap<>();
				settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
				settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/cart?serverTimezone=UTC");
				settings.put("hibernate.connection.username", "root");
				settings.put("hibernate.connection.password", "root");
				settings.put("hibernate.show_sql", "true");
				settings.put("hibernate.hbm2ddl.auto", "update");

				// c3p0 configuration
				settings.put(Environment.C3P0_MIN_SIZE, 5); // Minimum size of pool
				settings.put(Environment.C3P0_MAX_SIZE, 20); // Maximum size of pool
				settings.put(Environment.C3P0_ACQUIRE_INCREMENT, 1);// Number of connections acquired at a time when
																	// pool is exhausted
				settings.put(Environment.C3P0_TIMEOUT, 1800); // Connection idle time
				settings.put(Environment.C3P0_MAX_STATEMENTS, 150); // PreparedStatement cache size

				registry = registryBuilder.applySettings(settings).build();

				MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(Product.class);

				Metadata metadata = sources.getMetadataBuilder().build();

				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				System.out.println("SessionFactory creation failed");
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
		return sessionFactory;
	}

	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}
