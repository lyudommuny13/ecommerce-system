package ctm.mptc.kh.ecommerce.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "service")
@Getter
@Setter
@NoArgsConstructor
public class NacosConfigProp {

    private String name;
    private String info;
    private String version;

}
