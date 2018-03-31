package br.alvesfred.spring.boot.ignite;

import java.util.Arrays;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.configuration.BinaryConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.ConnectorConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.configuration.PersistentStoreConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.alvesfred.spring.boot.ignite.entry.ItemsConfigEntry;
import br.alvesfred.spring.boot.ignite.entry.ItemsEntry;

/**
 * Apache Ignite Node Configuration
 *
 * @author alvesfred
 *
 */
@Configuration
public class ApacheIgniteConfiguration {

	static final String ADDRESS_LIST = "127.0.0.1:47500..47509";

	@Value("${igniteConnectorPort}")
	private int igniteConnectorPort;

	@Value("${igniteServerPortRange}")
	private String igniteServerPortRange;

	@Value("${enableFilePersistence}")
	private boolean enableFilePersistence;

	@Value("${ignitePersistenceFilePath}")
	private String ignitePersistenceFilePath;

	@Bean
	IgniteConfiguration igniteConfiguration() {

		IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
		igniteConfiguration.setClientMode(false);

		// durable file memory persistence
		if (enableFilePersistence) {
			PersistentStoreConfiguration persistentStoreConfiguration = new PersistentStoreConfiguration();
			persistentStoreConfiguration.setPersistentStorePath("./data/store");
			persistentStoreConfiguration.setWalArchivePath("./data/walArchive");
			persistentStoreConfiguration.setWalStorePath("./data/walStore");

			igniteConfiguration.setPersistentStoreConfiguration(persistentStoreConfiguration);
		}

		// connector configuration
		ConnectorConfiguration connectorConfiguration = new ConnectorConfiguration();
		connectorConfiguration.setPort(igniteConnectorPort);

		// common ignite configuration
		igniteConfiguration.setMetricsLogFrequency(0);
		igniteConfiguration.setQueryThreadPoolSize(2);
		igniteConfiguration.setDataStreamerThreadPoolSize(1);
		igniteConfiguration.setManagementThreadPoolSize(2);
		igniteConfiguration.setPublicThreadPoolSize(2);
		igniteConfiguration.setSystemThreadPoolSize(2);
		igniteConfiguration.setRebalanceThreadPoolSize(1);
		igniteConfiguration.setAsyncCallbackPoolSize(2);
		igniteConfiguration.setPeerClassLoadingEnabled(false);
		igniteConfiguration.setIgniteInstanceName("alertsGrid");

		BinaryConfiguration binaryConfiguration = new BinaryConfiguration();
		binaryConfiguration.setCompactFooter(false);

		igniteConfiguration.setBinaryConfiguration(binaryConfiguration);

		// cluster tcp configuration
		TcpDiscoverySpi tcpDiscoverySpi = new TcpDiscoverySpi();
		TcpDiscoveryVmIpFinder tcpDiscoveryVmIpFinder = new TcpDiscoveryVmIpFinder();

		// need to be changed when it come to real cluster
		tcpDiscoveryVmIpFinder.setAddresses(Arrays.asList(ADDRESS_LIST));
		tcpDiscoverySpi.setIpFinder(tcpDiscoveryVmIpFinder);

		igniteConfiguration.setDiscoverySpi(new TcpDiscoverySpi());

		// cache configuration
		CacheConfiguration<String, Object> items = new CacheConfiguration<>();

		// as we have one node for now
		items.setCopyOnRead(false);
		items.setBackups(0);
		items.setAtomicityMode(CacheAtomicityMode.ATOMIC);
		items.setName("Items");
		items.setIndexedTypes(String.class, ItemsEntry.class);

		CacheConfiguration<String, Object> itemsConfig = new CacheConfiguration<>();
		itemsConfig.setCopyOnRead(false);
		itemsConfig.setBackups(0);
		itemsConfig.setAtomicityMode(CacheAtomicityMode.ATOMIC);
		itemsConfig.setName("ItemsConfig");
		itemsConfig.setIndexedTypes(String.class, ItemsConfigEntry.class);

		igniteConfiguration.setCacheConfiguration(items, itemsConfig);

		return igniteConfiguration;
	}

	@Bean(destroyMethod = "close")
	Ignite ignite(IgniteConfiguration igniteConfiguration) throws IgniteException {
		final Ignite start = Ignition.start(igniteConfiguration);
		start.active(true);

		return start;
	}

}
