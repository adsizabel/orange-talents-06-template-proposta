package br.com.zup.ot6.izabel.proposta.health;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class ApplicationHealthCheck implements HealthIndicator{

	@Override
	public Health health() {

		Map<String, Object> detalhes = new HashMap<>();
		detalhes.put("versão", "0.0.1-SNAPSHOT");
		detalhes.put("descrição", "Meu primeiro Health Check customizado!");
		detalhes.put("endereço", "127.0.0.1");
        
        return Health.status(Status.UP).withDetails(detalhes).build();
		
	}

}
