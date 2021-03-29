package payroll;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.common.reflection.java.JavaXMember;
import org.springframework.context.annotation.Configuration;
import org.springframework.nativex.extension.NativeImageHint;
import org.springframework.nativex.extension.TypeInfo;
import org.springframework.nativex.type.AccessBits;

@NativeImageHint(
    typeInfos = {
        @TypeInfo(
            types = {JsonBinaryType.class, JavaXMember.class},
            access = AccessBits.ALL
        )
    }
)
@Configuration
public class HibernateTypesConfig {
}
