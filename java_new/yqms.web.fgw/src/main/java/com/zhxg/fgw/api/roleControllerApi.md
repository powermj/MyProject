#### 约定
* url前缀: "/api/role"

####角色列表
```
"url": "/list"

"result": 
{
    "code":0,
    "msg":null,
    "data":[
        {
            "roleId":1,
            "roleName":"超级管理员",
            "roleDesc":"掌控一切",
            "flag":1,
            "createTime":1551699594755
        },
        {
            "roleId":2,
            "roleName":"普通管理员",
            "roleDesc":"各司其职",
            "flag":1,
            "createTime":1551765336683
        }
    ]
}
```

####修改角色回显角色详情信息
```
"url": "/{roldId}"

"result": 
{
    "code":0,
        "msg":null,
        "data":"{"createTime":1551699594755,"flag":1,"moduleIds":["1","2","3"],"roleDesc":"掌控一切","roleId":1,"roleName":"超级管理员"}"
}

```
####修改角色

```
"url": "/{roldId}"
"params": {
    "roleName": "标题",
    "roleDesc": "内容",
    "modules": "1,2,3,4"(moduleId逗号分隔)
},
"result": 
{
    "code":0,
    "msg":null,
    "data":null
}

```
####删除角色

```
"url": "/{roldId}"
"result": 
{
    "code":0,
    "msg":null,
    "data":null
}

```
####添加角色

```
"url": ""
"params": {
    "roleName": "标题",
    "roleDesc": "内容",
    "modules": "1,2,3,4"(moduleId逗号分隔)
},
"result": 
{
    "code":0,
    "msg":null,
    "data":null
}


