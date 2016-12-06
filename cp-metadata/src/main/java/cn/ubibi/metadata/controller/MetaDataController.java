package cn.ubibi.metadata.controller;

import cn.ubibi.metadata.models.MetaDataEntity;
import cn.ubibi.metadata.initdata.InitMetaData;
import cn.ubibi.metadata.repository.MetaDataRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * Created by luanhaipeng on 16/12/5.
 */
@RestController
public class MetaDataController {

    @Autowired
    private InitMetaData initMetaData;

    @Autowired
    private MetaDataRepository metaDataRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/metadata")
    public List<MetaDataEntity> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

        DB db = mongoTemplate.getDb();

        Set<String> mm = db.getCollectionNames();

        DBCollection xx = db.getCollection("metaDataEntity");

        DBObject query = new BasicDBObject();

        xx.find(query);


        initMetaData.doinsert();
        return metaDataRepository.findAll();

    }

}
