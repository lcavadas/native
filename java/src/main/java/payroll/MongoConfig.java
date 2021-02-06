package payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.GeoJsonConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.nativex.extension.NativeImageHint;
import org.springframework.nativex.extension.TypeInfo;
import org.springframework.nativex.type.AccessBits;

@NativeImageHint(typeInfos = {@TypeInfo(types = {GeoJsonConfiguration.class}, access = AccessBits.CONFIGURATION)})
@Configuration
class MongoConfig {

  @Autowired
  MongoDatabaseFactory mongoDatabaseFactory;

  @Bean
  MongoTemplate mongoTemplate() {
    //remove _class
    MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDatabaseFactory), new MongoMappingContext());
    converter.setTypeMapper(new DefaultMongoTypeMapper(null));
    return new MongoTemplate(mongoDatabaseFactory, converter);
  }
}
