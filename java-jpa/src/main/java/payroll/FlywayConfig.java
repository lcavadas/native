package payroll;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.nativex.extension.NativeImageHint;
import org.springframework.nativex.extension.ProxyInfo;
import org.springframework.nativex.extension.ResourcesInfo;
import org.springframework.nativex.extension.TypeInfo;
import org.springframework.nativex.type.AccessBits;

@NativeImageHint(
    typeInfos = {@TypeInfo(types = {Flyway.class}, access = AccessBits.ALL)},
    proxyInfos = {@ProxyInfo(types = {Qualifier.class, SynthesizedAnnotation.class})},
    resourcesInfos = {@ResourcesInfo(patterns = ".*/flywaydb/.*/version\\.txt")}
)
@Configuration
class FlywayConfig {
}
