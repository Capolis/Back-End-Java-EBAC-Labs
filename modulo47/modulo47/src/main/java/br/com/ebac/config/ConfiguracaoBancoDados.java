package br.com.ebac.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.ebac.util.DatabaseCryptoUtil;

@Configuration
public class ConfiguracaoBancoDados {

    @Value("${spring.datasource.password}")
    private String senhaCriptografada;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String usuario;

    @Bean
    public DataSource dataSource() throws Exception {
        // Chamada do método de criptografia AES
        String senhaDescriptografada = DatabaseCryptoUtil.decryptString(senhaCriptografada);

        return DataSourceBuilder.create()
                .url(url)
                .username(usuario)
                .password(senhaDescriptografada)
                .build();
    }
}