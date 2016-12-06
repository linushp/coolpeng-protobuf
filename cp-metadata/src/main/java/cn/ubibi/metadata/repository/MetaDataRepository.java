package cn.ubibi.metadata.repository;

import cn.ubibi.metadata.models.MetaDataEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MetaDataRepository extends MongoRepository<MetaDataEntity, String> {
    MetaDataEntity findByName(String name);
}