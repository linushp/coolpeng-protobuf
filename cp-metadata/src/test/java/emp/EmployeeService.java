package emp;

import cn.ubibi.metadata.gapi.MetaDataService;

import java.util.Map;

/**
 * Created by luanhaipeng on 16/12/6.
 */
public class EmployeeService {

    public void createEmployee(Map<String,Object> employee){
        MetaDataService metaDataService = new MetaDataService();
        metaDataService.createEntity("TS00001","employee_info",employee);

    }

}
