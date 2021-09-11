package br.com.pizmo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "br/com/pizmo/domain")
public class BaseConfiguration {
}
