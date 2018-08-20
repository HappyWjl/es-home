package com.es.datadump.manager.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetSocketAddress;

public class CanalInitClientManager {

    private final static Logger logger = LoggerFactory.getLogger(CanalInitClientManager.class);

    @Value("${canal.client.servers}")
    private String servers;

    @Value("${canal.client.cluser}")
    private boolean cluser;

    @Value("${canal.client.destination}")
    private String destination;

    private CanalConnector canalConnector = null;

    public final CanalConnector getCanalConnector() {
        logger.info("--------------canalServer:" + servers);
        logger.info("--------------destination:" + destination);
        if (cluser) {
            canalConnector = CanalConnectors.newClusterConnector(servers, destination, "", "");
        } else {
            if (canalConnector == null) {
                if (servers != null) {
                    InetSocketAddress inetSocketAddress = stringToAddress(servers);
                    canalConnector = CanalConnectors.newSingleConnector(inetSocketAddress, destination, "", "");
                }

            }
        }
        return canalConnector;
    }

    /**
     * 将ip:port格式装换为 InetSocketAddress对象返回
     *
     * @param server
     * @return
     */
    protected InetSocketAddress stringToAddress(String server) {
        if (server != null) {
            String[] ipAndPort = server.split(":");
            return new InetSocketAddress(ipAndPort[0], Integer.valueOf(ipAndPort[1]));
        }
        return null;
    }

    public String getDestination() {
        return destination;
    }

}
