#### 约定
* url前缀: "/api/user"

####部门用户列表
```
"url": "/list/{deptId}"

"result": 
{
    "code":0,
        "msg":null,
        "data":[
            {
                "userId":1,
                "loginId":"1",
                "nickName":"Lerbon",
                "deptId":4,
                "deptName":"湖人队现役球员",
                "flag":1,
                "ssoId":"1",
                "createTime":1551764430725,
                "modified":1551776562112
            },
            {
                "userId":2,
                "loginId":"2",
                "nickName":"Kobe",
                "deptId":5,
                "deptName":"湖人队退役球员",
                "flag":1,
                "ssoId":"2",
                "createTime":1551776553534,
                "modified":1551776642359
            }
        ]
}
```
####去往授权页面
```
"url": "/{userId}"

"result": 
{
    "code":0,
    "msg":null,
    "data":"{
            "sysUser":{
                    "createTime":1551764430725,
                    "deptId":1,
                    "deptName":"国务院政务部",
                    "flag":1,
                    "loginId":"1",
                    "modified":1551764430725,
                    "nickName":"Lerbon",
                    "ssoId":"1",
                    "userId":1
                    },
            "roleList":[
                        {
                        "createTime":1551765336683,
                        "flag":1,
                        "roleDesc":"各司其职",
                        "roleId":2,
                        "roleName":"普通管理员"
                        },
                        {
                        "createTime":1551715200000,
                        "flag":1,
                        "roleDesc":"掌控全局",
                        "roleId":3,
                        "roleName":"超级管理员"
                        }
                      ]
            }"
}

```
####授权
```
"url": "/{userId}"
"params": {
    "roleId": "1"
},
"result": 
{
       "code":0,
       "msg":null,
       "data":null
}



