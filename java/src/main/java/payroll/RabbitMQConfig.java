package payroll;

import org.springframework.amqp.rabbit.annotation.RabbitListenerAnnotationBeanPostProcessor;
import org.springframework.amqp.rabbit.connection.ChannelProxy;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.nativex.extension.NativeImageHint;
import org.springframework.nativex.extension.ProxyInfo;
import org.springframework.nativex.extension.TypeInfo;
import org.springframework.nativex.type.AccessBits;

@NativeImageHint(typeInfos = {
    @TypeInfo(types = {RabbitListenerAnnotationBeanPostProcessor.class}, access = AccessBits.ALL),
    @TypeInfo(types = {RabbitListenerEndpointRegistry.class}, access = AccessBits.ALL),
}, proxyInfos = {
    @ProxyInfo(types = {ChannelProxy.class})
})
@Configuration
public class RabbitMQConfig { }
