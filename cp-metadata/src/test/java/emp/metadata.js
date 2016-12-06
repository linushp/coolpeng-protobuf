/**
 * Docker
 *
 *
 *
 */

/**
 * 登录时加载的数据:
 *     1.用户功能权限   ...
 *     2.用户基本信息
 *     3.环境基本信息
 *     4.实例元数据     ...
 */


/**
 * 模块划分:
 *
 * service:(模块之间互相不依赖,不能互相调用)
 *
 *      1.用户数据: 用户登录/会话管理.
 *      2.核心数据: 员工/组织/职务/岗位/权限/
 *      3.绩效数据
 *      4.考勤数据
 *
 *
 * comp:(公共接口,模块之间互相不依赖)
 *      1.通用数据库访问
 *      2.service api
 *      3.通用Redis缓存
 *      4.通用消息队列
 *      5.通用任务框架
 *      6.ElasticSearch
 *      7.元数据管理 (组件)
 *
 * app:(模块之间互相不依赖)
 *
 *     1.员工信息查看 (基本信息,我的考勤,我的绩效,我的审批)
 *     2.权限管理设置
 *     3.绩效模块
 *     4.考勤模块
 *     5.人事审批
 *
 */

var mdList = [
    {
        name: 'employee_info',
        collection:'TS00001_employee_info',
        text: '员工信息',
        group: 'employee',
        instanceId:'TS00001',
        items: [
            {
                name: 'empNumber',
                text: '员工工号',
                dbType: 'string',//bool,number,date,string
                format: '${value}',
                input: 'generator:CommonNumberGenerator("empNumber","YG",10000001)',
                validator:[]
            },
            {
                name: 'empName',
                text: '员工姓名',
                dbType: 'string',//bool,number,date,string
                format: '${value}',
                input: 'text',
                validator: ["required", "length:1-10"]
            },
            {
                name: 'empBirthday',
                text: '出生日期',
                dbType: 'date',
                format: 'CommonDateFormat(${value},"yyyy-mm-dd")',
                input: 'datePicker',
                validator: ["DateValidator:yyyy-mm-dd"]
            },
            {
                name: 'empAge',
                text: '年龄',
                dbType: null,//不存储,
                format: 'CommonAgeFormat(${entity},"empBirthday")',
                input: 'readonly',
                validator: []
            },
            {
                name: 'empBelongOrg',
                text: '所属组织',
                dbType: 'string',
                format: 'OrgFormat(${value})',
                input: 'selector:OrgSelector(${value},${entity}):target.id',
                validator: []
            },
            {
                name: 'empSalaryBase',
                text: '基本工资',
                dbType: 'number',
                format: '${value} 元',
                input: 'text',
                validator: ['number']
            },
            {
                name: 'empNation',
                text: '民族',
                dbType: 'string',
                format: '${value}',
                input: 'select:SelectEnum("Nation"):target',
                validator: ['number']
            },
            {
                name: 'empPosition',
                text: '担任岗位',
                dbType: 'string',
                format: '${value}',
                input: 'select:SelectDataSource("Position"):target.id',
                validator: ['number']
            }
        ]
    },


    {
        name: 'employee_family',
        collection:'employee_family',
        text: '家庭成员',
        group: 'employee',
        items: [
            {
                name: 'familyName',
                text: '姓名',
                dbType: 'string',//bool,number,date,string
                format: '${value}',
                input: 'text',
                validator: ["required", "length:1-10"]
            },
            {
                name: 'familyBirthday',
                text: '出生日期',
                dbType: 'date',
                format: 'CommonDateFormat(${value},"yyyy-mm-dd")',
                input: 'datePicker',
                validator: ["DateValidator:yyyy-mm-dd"]
            }
        ]
    }




];