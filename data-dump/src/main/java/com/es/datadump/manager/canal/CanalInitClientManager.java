package com.es.datadump.manager.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetSocketAddress;

@Slf4j
public class CanalInitClientManager {

    @Value("${canal.client.servers}")
    private String servers;

    @Value("${canal.client.cluser}")
    private boolean cluser;

    @Value("${canal.client.destination}")
    private String destination;

    private CanalConnector canalConnector = null;

    public final CanalConnector getCanalConnector() {
        log.info("--------------canalServer:" + servers);
        log.info("--------------destination:" + destination);
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
