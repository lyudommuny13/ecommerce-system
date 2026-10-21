package ctm.mptc.kh.ecommerce.order;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/nacos/config")
@RequiredArgsConstructor
public class NacosConfigController {

    private final NacosConfigProp nacosConfigProp;

    @GetMapping
    public Map<String, Object> getConfig() {
        return Map.of(
                "serviceName", nacosConfigProp.getName(),
                "serviceInfo", nacosConfigProp.getInfo(),
                "serviceVersion", nacosConfigProp.getVersion()
        );
    }

    @GetMapping("/secured")
    public Map<String, Object> getSecuredConfig(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
                "serviceName", nacosConfigProp.getName(),
                "serviceInfo", nacosConfigProp.getInfo(),
                "serviceVersion", nacosConfigProp.getVersion(),
                "accessToken", jwt.getTokenValue()
        );
    }
}
