package kr.co.hist.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

@Profile("local")
@Configuration
public class H2ServerConfig {
    /**
     * H2 TCP Server start : Default Port 9092
     * @return
     * @throws SQLException
     */
    @Bean
    public Server h2TcpServer() throws SQLException {
        return Server.createTcpServer().start();
    }
}
