package cn.ubibi.metadata.gapi;

import com.mongodb.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by luanhaipeng on 16/12/6.
 */
public class MetaDataService {

    @Autowired
    private MongoTemplate mongoTemplate;


    public WriteResult createEntity(String instanceId, String mdEntityName, Map<String, Object> data) {
        DBCollection collection = mongoTemplate.getCollection(getCollectionName(instanceId, mdEntityName));
        DBObject dbObject = new BasicDBObject(data);
        return collection.insert(dbObject);
    }


    public WriteResult createEntityBatch(String instanceId, String mdEntityName, List<Map<String, Object>> dataList) {
        DBCollection collection = mongoTemplate.getCollection(getCollectionName(instanceId, mdEntityName));
        List<DBObject> dbObjectList = new ArrayList<DBObject>(dataList.size() + 1);
        for (Map<String, Object> map : dataList) {
            DBObject dbObject = new BasicDBObject(map);
            dbObjectList.add(dbObject);
        }
        return collection.insert(dbObjectList);
    }


    public WriteResult updateEntityAttribute(String instanceId, String mdEntityName, String entityId, Map<String, Object> data) {
        DBCollection collection = mongoTemplate.getCollection(getCollectionName(instanceId, mdEntityName));
        DBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(entityId));
        DBObject update = new BasicDBObject(data);
        return collection.update(query, update);
    }


    public List<DBObject> findAll(String instanceId, String mdEntityName){
        DBCollection collection = mongoTemplate.getCollection(getCollectionName(instanceId, mdEntityName));
        return collection.find().toArray();
    }


    private String getCollectionName(String instanceId, String mdEntityName) {
        String collectionName = instanceId + "_" + mdEntityName;
        return collectionName;
    }
}
