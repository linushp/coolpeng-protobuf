package cn.ubibi.metadata.initdata;

import cn.ubibi.metadata.models.MetaDataAttribute;
import cn.ubibi.metadata.models.MetaDataEntity;
import cn.ubibi.metadata.repository.MetaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luanhaipeng on 16/12/5.
 */
@Service
public class InitMetaData {

    @Autowired
    private MetaDataRepository metaDataRepository;

    public void doinsert(){


        List<MetaDataEntity> mm = metaDataRepository.findAll();
        if(mm==null || mm.size()==0){
            List<MetaDataAttribute> attributes = new ArrayList<MetaDataAttribute>();
            attributes.add(new MetaDataAttribute("empNumber","员工工号","string", "${value}", "generator:CommonNumberGenerator(\"empNumber\",\"YG\",10000001)", new String[]{}));
            attributes.add(new MetaDataAttribute("empName","员工姓名","string", "${value}", "text", new String[]{"required", "length:1-10"}));
            attributes.add(new MetaDataAttribute("empBirthday","出生日期","string", "${value}", "text", new String[]{"DateValidator:yyyy-mm-dd"}));


            MetaDataEntity m = new MetaDataEntity();
            m.setName("employee_info");
            m.setGroup("employee");
            m.setAttributes(attributes);
            metaDataRepository.insert(m);
        }else {
            MetaDataEntity m2 = metaDataRepository.findByName("employee_info");
            m2.getAttributes().add(new MetaDataAttribute("empAge"+System.currentTimeMillis(), "年龄", null, "CommonAgeFormat(${entity},\"empBirthday\")", "readonly", new String[]{}));
            metaDataRepository.save(m2);
        }


    }


}
