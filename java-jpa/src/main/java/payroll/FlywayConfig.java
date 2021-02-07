package payroll;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.nativex.extension.NativeImageHint;
import org.springframework.nativex.extension.ProxyInfo;
import org.springframework.nativex.extension.ResourcesInfo;

@NativeImageHint(
    proxyInfos = {@ProxyInfo(types = {Qualifier.class, SynthesizedAnnotation.class})},
    resourcesInfos = {@ResourcesInfo(patterns = "db/migration/.*$")}
)
@Configuration
class FlywayConfig {
}
