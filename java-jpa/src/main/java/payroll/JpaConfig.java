package payroll;

import org.hibernate.dialect.PostgreSQL10Dialect;
import org.springframework.context.annotation.Configuration;
import org.springframework.nativex.extension.NativeImageHint;
import org.springframework.nativex.extension.TypeInfo;
import org.springframework.nativex.type.AccessBits;

@NativeImageHint(typeInfos = {@TypeInfo(types = {PostgreSQL10Dialect.class}, access = AccessBits.ALL)})
@Configuration
class JpaConfig {
}
